package client;

import java.io.IOException;
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
		while(isConnected){
			scanner.nextLine();
			System.out.println("Cyka!");
		}
	}
}
