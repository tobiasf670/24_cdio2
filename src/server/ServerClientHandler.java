package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ServerClientHandler implements Observer{

	private Socket socket;
	private BufferedReader inputStream;
	private DataOutputStream outputStream;
	private InetAddress ipAdress;
	private Scanner scanner;
	private GUIController gc;
	private WeightDTO weightData;
	private boolean dispDidChange;
	
	public ServerClientHandler(Socket s, BufferedReader i, DataOutputStream d, Scanner scanner, GUIController gc, WeightDTO weight){
		this.gc = gc;
		socket = s;
		inputStream = i;
		outputStream = d;
		ipAdress = s.getInetAddress();
		this.scanner = scanner;
		this.weightData = weight;
		weight.addObserver(this);
	}
	
	//should be a thread and that should be started
	public void run() {
		try{
			String inline = "";
			
			printmenu();
            while (!(inline = inputStream.readLine().toUpperCase()).isEmpty()){
            	//RM20 8 command expects a reponse
            	if (inline.startsWith("RM20 8")){	
            		try{
            			//System.out.println("RM20 Text: "+inline.split(" ")[2]);
//            			System.out.print("Type answer: ");
            			gc.getServerGUI().getMainDisp().setEditable(true);
            			gc.getServerGUI().getMainDisp().setText("Type in your answer");
            			gc.getServerGUI().getSecDisp().setText((inline.substring(8, inline.length()-9)));
            			dispDidChange = false;
            			
            			while(!dispDidChange){
            				Thread.sleep(500);
            				
            			}
            			outputStream.writeBytes("RM20 B\r\n");
                    	outputStream.writeBytes("RM20 A \""+weightData.getMainDisp()+"\""+"\r\n");
                    	
                    	gc.getServerGUI().getMainDisp().setEditable(false);
                    	gc.getServerGUI().getMainDisp().setText("0");
                    	
            		} catch(Exception e){
            			outputStream.writeBytes("RM20 B\r\n");
            		}
            	}
                else if (inline.startsWith("D")){
                	//DW command clears weight display
                    if (inline.equals("DW")){
                    	weightData.setMainDisp("0");
                    	this.gc.getServerGUI().getMainDisp().setText(weightData.getMainDisp());
                    	outputStream.writeBytes("DW A"+"\r\n");
                    }

                    //D command write text in weights display
                    else{
                    	if (inline.length() > 1){
                    	weightData.setMainDisp((inline.substring(2, inline.length())));
                    	this.gc.getServerGUI().getMainDisp().setText(weightData.getMainDisp());
                    	outputStream.writeBytes("D A"+"\r\n");
                    }    
                    } 
                }
            	//T command takes current weight as tara
                else if (inline.startsWith("T")){
                    weightData.setTara(weightData.getBrutto());
                    outputStream.writeBytes("T S      " + (weightData.getTara()) + " kg"+"\r\n");
                    this.gc.getServerGUI().getTaraDisp().setText("Current Tara: " + weightData.getTara());
                    this.gc.getServerGUI().getMainDisp().setText("0");
                }
            	//S command sends stable/fixed measure           GG->stabile weighting ^^
                else if (inline.equals("S")){
                    outputStream.writeBytes("S S      " + (weightData.getBrutto()-weightData.getTara())+ " kg"  +"\r\n");
                }
            	//B command sets virtual brutto weight
                else if (inline.startsWith("B")){
                    String temp= inline.substring(2,inline.length());
                    weightData.setBrutto(Double.parseDouble(temp));
                    this.gc.getServerGUI().getMainDisp().setText(temp);
                    outputStream.writeBytes("DB"+"\r\n");
                }
            	//P111 command write in secondary display
                else if(inline.startsWith("P111")){
                	String temp = inline.substring(5, inline.length());
                	weightData.setSecDisp(temp);
                	this.gc.getServerGUI().getSecDisp().setText(temp);
                	outputStream.writeBytes("P111 A\r\n");
                }
            	//Q command quits the application(in this case leaves weight for new one)
                else if ((inline.startsWith("Q"))){
                	System.out.println("Client closed!");
                    outputStream.close();
                    inputStream.close();
                    socket.close();
                    this.gc.getServerGUI().getMainDisp().setText("Connection Terminated");
          
                    break;
                } else if((inline.startsWith("ST"))){
                	if(inline.startsWith("ST 1")){
                		outputStream.writeBytes("ST A\r\n");
                		dispDidChange = false;
                		while(!dispDidChange){
            				Thread.sleep(500);
            			}
                		outputStream.writeBytes("S S    "+(weightData.getBrutto()-weightData.getTara())+" kg\r\n");
                	}
                	else{
                		outputStream.writeBytes("ST A\r\n");
                	}
                	
                }
                else { 
                	//System.out.println(inline);
                    outputStream.writeBytes("ES"+"\r\n");
                }
            	printmenu();
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
	}
	
	public void printmenu(){
		/*try {
			for (int i=0;i<2;i++)
			outputStream.writeBytes("\r\n");
			outputStream.writeBytes("*************************************************\r\n");
			outputStream.writeBytes("Denne vægtsimulator lytter på ordrene\r\n");
			outputStream.writeBytes("S, T, D, DW, RM20 8 .... , B, Q og P111\r\n");
			outputStream.writeBytes("******\r\n");

			outputStream.writeBytes("Brutto: "+weightData.getBrutto()+" kg\r\n");
			outputStream.writeBytes("Tara: "+weightData.getTara()+" kg. .\r\n");

			if(weightData.getMainDisp().isEmpty()){
				outputStream.writeBytes("Netto tekst: "+(weightData.getBrutto()-weightData.getTara())+" kg\r\n");
				outputStream.writeBytes("Brutto tekst: "+(weightData.getBrutto())+" kg\r\n");
			}
			else{
				//outputStream.writeBytes("Primï¿½r tekst: "+weightData.getMainDisp()+".\r\n");
			}
			outputStream.writeBytes("Sekundï¿½r tekst: "+weightData.getSecDisp()+".\r\n");

			outputStream.writeBytes("******\r\n");
			outputStream.writeBytes("Tast T for tara (svarende til knaptryk på vægt)\r\n");
			outputStream.writeBytes("Tast B for ny brutto (svarende til at belastningen på vægt ændres)\r\n");
			outputStream.writeBytes("Tast Q for at afslutte program\r\n");
			outputStream.writeBytes("Indtast (T/B/Q for knaptryk / brutto ændring / quit)\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("maindisp")){
			dispDidChange = true;
		}
	}
}