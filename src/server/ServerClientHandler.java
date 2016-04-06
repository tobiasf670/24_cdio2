package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerClientHandler {

	private Socket socket;
	private BufferedReader inputStream;
	private DataOutputStream outputStream;
	private InetAddress ipAdress;
	private Scanner scanner;
	private GUIController gc;
	private WeightDTO weightData;
	
	public ServerClientHandler(Socket s, BufferedReader i, DataOutputStream d, Scanner scanner, GUIController gc, WeightDTO weight){
		this.gc = gc;
		socket = s;
		inputStream = i;
		outputStream = d;
		ipAdress = s.getInetAddress();
		this.scanner = scanner;
		this.weightData = weight;
	}
	
	//should be a thread and that should be started
	public void run() {
		try{
			String inline = "";
			weightData.setMainDisp(this.gc.getServerGUI().getMainDisp().getText());
			weightData.setSecDisp(this.gc.getServerGUI().getSecDisp().getText());
			printmenu();
            while (!(inline = inputStream.readLine().toUpperCase()).isEmpty()){
            	//RM20 8 command expects a reponse
            	if (inline.startsWith("RM20 8")){	
            		try{
            			//System.out.println("RM20 Text: "+inline.split(" ")[2]);
//            			System.out.print("Type answer: ");
            			gc.getServerGUI().getMainDisp().setEditable(true);
            			
            		//	gc.getServerGUI().getBtnEnter().
            			
                    	outputStream.writeBytes("RM20 A \""+weightData.getMainDisp()+"\""+"\r\n");
                    	
                    	gc.getServerGUI().getMainDisp().setEditable(false);
                    	
            		} catch(Exception e){
            			outputStream.writeBytes("RM20B\r\n");
            		}
            	}
                else if (inline.startsWith("D")){
                	//DW command clears weight display
                    if (inline.equals("DW")){
                    	weightData.setMainDisp("0.0");
                    	this.gc.getServerGUI().getMainDisp().setText(weightData.getMainDisp());
                    	outputStream.writeBytes("DW A"+"\r\n");
                    }
                    //D command writes to the weight display
                    //D command write text in weights display
                    //D command write text in weights display
                    //D command write text in weights display
                    //D command write text in weights display
                    else{
                    	weightData.setMainDisp((inline.substring(2, inline.length())));
                    	this.gc.getServerGUI().getMainDisp().setText(weightData.getMainDisp());
                    	outputStream.writeBytes("D A"+"\r\n");
                    }    
                    
                }
            	//T command takes current weight as tara
                else if (inline.startsWith("T")){
                    outputStream.writeBytes("T S      " + (weightData.getTara()) + " kg"+"\r\n");
                    weightData.setTara(weightData.getBrutto());
                    this.gc.getServerGUI().getTaraDisp().setText("Current Tara: " + weightData.getTara());
                    this.gc.getServerGUI().getMainDisp().setText("0.0");
                }
            	//S command sends stable/fixed measure           GG->stabile weighting ^^
                else if (inline.startsWith("S")){
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
		try {
			for (int i=0;i<2;i++)
			outputStream.writeBytes("\r\n");
			outputStream.writeBytes("*************************************************\r\n");
			outputStream.writeBytes("Denne v�gtsimulator lytter p� ordrene\r\n");
			outputStream.writeBytes("S, T, D, DW, RM20 8 .... , B og Q\r\n");
			outputStream.writeBytes("******\r\n");

			outputStream.writeBytes("Brutto: "+weightData.getBrutto()+" kg\r\n");
			outputStream.writeBytes("Tara: "+weightData.getTara()+".\r\n");

			if(weightData.getMainDisp().isEmpty()){
				outputStream.writeBytes("Netto tekst: "+(weightData.getBrutto()-weightData.getTara())+" kg\r\n");
				outputStream.writeBytes("Brutto tekst: "+(weightData.getBrutto())+" kg\r\n");
			}
			else{
				//outputStream.writeBytes("Prim�r tekst: "+weightData.getMainDisp()+".\r\n");
			}
			outputStream.writeBytes("Sekund�r tekst: "+weightData.getSecDisp()+".\r\n");

			outputStream.writeBytes("******\r\n");
			outputStream.writeBytes("Tast T for tara (svarende til knaptryk p� v�gt)\r\n");
			outputStream.writeBytes("Tast B for ny brutto (svarende til at belastningen p� v�gt �ndres)\r\n");
			outputStream.writeBytes("Tast Q for at afslutte program\r\n");
			outputStream.writeBytes("Indtast (T/B/Q for knaptryk / brutto �ndring / quit)\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}