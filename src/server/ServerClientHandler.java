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
			printmenu();
            while (!(inline = inputStream.readLine().toUpperCase()).isEmpty()){ //her ventes på input
            	if (inline.startsWith("RM20 8")){						
                	System.out.print("Type in console: ");
                	String temp = scanner.nextLine();
                	outputStream.writeBytes("RM20 A \""+temp+"\""+"\r\n");
                	indtDisp = temp;
                	
            	}
                else if (inline.startsWith("D")){
                    if (inline.equals("DW")){
                    	indtDisp="";
                    }
                    else{
                    	indtDisp=(inline.substring(2, inline.length()));//her skal anførselstegn udm.
                    }    
                    outputStream.writeBytes("DB"+"\r\n");
                }
                else if (inline.startsWith("T")){
                    outputStream.writeBytes("T S " + (tara) + " kg "+"\r\n");		//HVOR MANGE SPACE?
                    tara=brutto;
                }
                else if (inline.startsWith("S")){
                    outputStream.writeBytes("S S      " + (brutto-tara)+ " kg"  +"\r\n");//HVOR MANGE SPACE?
                }
                else if (inline.startsWith("B")){ //denne ordre findes ikke p� en fysisk v�gt
                    String temp= inline.substring(2,inline.length());
                    brutto = Double.parseDouble(temp);
                    outputStream.writeBytes("DB"+"\r\n");
                }
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
			outputStream.writeBytes("Brutto: "+(brutto)+" kg\r\n");
			outputStream.writeBytes("Tekst: "+indtDisp+".\r\n");
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
