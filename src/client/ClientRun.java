package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientRun {

	private Socket socket;
	private boolean isConnected = true;
	
	public ClientRun(InetAddress adr, int port) throws IOException{
		socket = new Socket(adr, port);
	}
	
	public ClientRun(String adr, int port) throws IOException{
		socket = new Socket(adr, port);
	}
	
	public void start(Scanner scanner){
		printmenu();
		while(isConnected){
			try {
				BufferedReader iStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream oStream = new DataOutputStream(socket.getOutputStream());
				oStream.writeBytes(scanner.next() + "\r\n");
				while(!iStream.ready()){}//nasty hax to halt
				System.out.println(iStream.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			System.out.println("Cyka!");
		}
	}
	
	public void printmenu(){
        for (int i=0;i<2;i++)
        System.out.println("                                                 ");
        System.out.println("*************************************************");
//        System.out.println("Netto: " + (brutto-tara)+ " kg"                   );
//        System.out.println("Instruktionsdisplay: " +  indtDisp    );
//        System.out.println("*************************************************");
//        System.out.println("                                                 ");
//        System.out.println("                                                 ");
//        System.out.println("Debug info:                                      ");
//        System.out.println("Hooked up to " + sock.getInetAddress()            );
//        System.out.println("Brutto: " + (brutto)+ " kg"                       );
//        System.out.println("Streng modtaget: "+inline)                         ;
//        System.out.println("                                                 ");
        System.out.println("Denne vægt simulator lytter på ordrene           ");
        System.out.println("S, T, D, DW, RM20 8 .... , B og Q         ");
        System.out.println("p�kommunikationsporten.                         ");
        System.out.println("******");
        System.out.println("Tast T for tara (svarende til knaptryk paa vegt)") ;
        System.out.println("Tast B for ny brutto (svarende til at belastningen paa vegt �ndres)");
        System.out.println("Tast Q for at afslutte program program");
        System.out.println("Indtast (T/B/Q for knaptryk / brutto �ndring / quit)");
        System.out.print  ("Tast her: ");
    }
}
