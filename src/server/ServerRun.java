package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRun implements Runnable{

	private ServerSocket serverSocket;
	private boolean isRunning = true;
	private static final int portNumber = 8000;
	private ArrayList<Client> clients;
	private Thread thread;
	
	public ServerRun(){
		try {
			serverSocket = new ServerSocket(8000);
			clients = new ArrayList<Client>();
		} catch (IOException e) {
			System.out.println("Could not start server, ending program.");
			System.exit(0);
		}
	}
	
	public void start(){
		thread = new Thread(this);
		thread.start();
		while(isRunning){
            try {
            	 System.out.println("Venter paa connection på port " + portNumber );
                 System.out.println("Indtast eventuel portnummer som 1. argument");
                 System.out.println("paa kommando linien for andet portnr");
                 Socket sock = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                 DataOutputStream output = new DataOutputStream(sock.getOutputStream());
                 Client client = new Client(sock, reader, output);
                 client.run();
                 clients.add(client);
                 System.out.println("Client connected with IP: "+sock.getLocalAddress().getHostAddress()+" and port: "+sock.getPort());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while(isRunning){
			if(clients.size() > 0){
				for(int i = 0; i < clients.size(); i++){
					if(clients.get(i).shouldRemove()){
						clients.remove(i);
						break;
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
