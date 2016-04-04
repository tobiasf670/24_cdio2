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
	private double tara = 0;
	private double brutto = 0;
	private Scanner scanner;
	private String indtDisp;
	private String sekDisp;
	
	public ServerClientHandler(Socket s, BufferedReader i, DataOutputStream d, Scanner scanner){
		socket = s;
		inputStream = i;
		outputStream = d;
		ipAdress = s.getInetAddress();
		this.scanner = scanner;
	}
	
	//should be a thread and that should be started
	public void run() {
		try{
			String inline = "";
			indtDisp = "";
			sekDisp = "";
			printmenu();
            while (!(inline = inputStream.readLine().toUpperCase()).isEmpty()){
            	//RM20 8 command expects a reponse
            	if (inline.startsWith("RM20 8")){	
            		try{
            			System.out.println("RM20 Text: "+inline.split(" ")[2]);
            			System.out.print("Type answer: ");
            			String temp = scanner.nextLine();
                    	outputStream.writeBytes("RM20 A \""+temp+"\""+"\r\n");
            		} catch(Exception e){
            			outputStream.writeBytes("RM20B\r\n");
            		}
            	}
                else if (inline.startsWith("D")){
                	//DW command clears weight display
                    if (inline.equals("DW")){
                    	indtDisp="";
                    	outputStream.writeBytes("DW A"+"\r\n");
                    }
                    //D command skriver i vægtens display
                    else{
                    	indtDisp=(inline.substring(2, inline.length()));
                    	outputStream.writeBytes("D A"+"\r\n");
                    }    
                    
                }
            	//T command takes current weight as tara
                else if (inline.startsWith("T")){
                    outputStream.writeBytes("T S      " + (tara) + " kg"+"\r\n");
                    tara=brutto;
                }
            	//S command sends stabile weighting
                else if (inline.startsWith("S")){
                    outputStream.writeBytes("S S      " + (brutto-tara)+ " kg"  +"\r\n");
                }
            	//B command sets virtual brutto weight
                else if (inline.startsWith("B")){
                    String temp= inline.substring(2,inline.length());
                    brutto = Double.parseDouble(temp);
                    outputStream.writeBytes("DB"+"\r\n");
                }
            	//P111 command write in secondary display
                else if(inline.startsWith("P111")){
                	String temp = inline.substring(5, inline.length());
                	sekDisp = temp;
                	outputStream.writeBytes("P111 A\r\n");
                }
            	//Q command quits the application(in this case leaves weight for new one)
                else if ((inline.startsWith("Q"))){
                	System.out.println("Client closed!");
                    outputStream.close();
                    inputStream.close();
                    socket.close();
                    break;
                }
                
                else { 
                	System.out.println(inline);
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
			outputStream.writeBytes("Denne vægt simulator lytter på ordrene\r\n");
			outputStream.writeBytes("S, T, D, DW, RM20 8 .... , B og Q\r\n");
			outputStream.writeBytes("******\r\n");
			if(indtDisp.isEmpty()){
				outputStream.writeBytes("Brutto tekst: "+(brutto-tara)+" kg\r\n");
			}
			else{
				outputStream.writeBytes("Primær tekst: "+indtDisp+".\r\n");
			}
			outputStream.writeBytes("Sekundær tekst: "+sekDisp+".\r\n");
			outputStream.writeBytes("******\r\n");
			outputStream.writeBytes("Tast T for tara (svarende til knaptryk på vægt)\r\n");
			outputStream.writeBytes("Tast B for ny brutto (svarende til at belastningen på vægt ændres)\r\n");
			outputStream.writeBytes("Tast Q for at afslutte program\r\n");
			outputStream.writeBytes("Indtast (T/B/Q for knaptryk / brutto ændring / quit)\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
