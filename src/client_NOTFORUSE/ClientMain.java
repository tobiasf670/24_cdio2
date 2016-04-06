package client_NOTFORUSE;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClientRun run = null;
		while(run == null){
			String adress;
			int port;
			System.out.println();
			System.out.println("------------------");
			System.out.println();
			System.out.println("Write the ip you would like to connect to");
			System.out.print("Ip adress: ");
			adress = scanner.nextLine();
			System.out.println("Write the port you would like to use");
			System.out.print("Port: ");
			if(scanner.hasNextInt()){
				port = scanner.nextInt();
				try{
					run = new ClientRun(adress, port);
					System.out.println("Connected to server: "+adress+" through port: "+port);
				} catch (IOException e){
					run = null;
				}
			}
		}
		run.start(scanner);
	}	
}
