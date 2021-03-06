package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerRun{

	private ServerSocket serverSocket;
	private boolean isRunning = true;
	private static int portNumber = 8000;
	private ServerClientHandler client;
	private Scanner scanner;

	
	public ServerRun(String port){
		
		portNumber = Integer.parseInt(port);
			
		try {
			serverSocket = new ServerSocket(portNumber);
			scanner = new Scanner(System.in);
		} catch (IOException e) {
			System.out.println("Could not start server, ending program.");
			System.exit(0);
		}
	}
	
	public void start(GUIController gc, WeightDTO weight){
		while(isRunning){
            try {
            	 System.out.println("Venter p� connection p� port " + portNumber );
                 System.out.println("Indtast eventuel portnummer som 1. argument");
                 System.out.println("på kommando linien for andet portnr");
                 Socket sock = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                 DataOutputStream output = new DataOutputStream(sock.getOutputStream());
                 client = new ServerClientHandler(sock, reader, output, scanner, gc, weight);
               
                 System.out.println("Client connected with IP: "+sock.getLocalAddress().getHostAddress()+" and port: "+sock.getLocalPort());
                 client.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
