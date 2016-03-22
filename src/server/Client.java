package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable{

	private Socket socket;
	private BufferedReader inputStream;
	private DataOutputStream outputStream;
	private InetAddress ipAdress;
	private double tara = 0;
	private double brutto = 0;
	private Thread thread;
	
	public Client(Socket s, BufferedReader i, DataOutputStream d){
		socket = s;
		inputStream = i;
		outputStream = d;
		ipAdress = s.getInetAddress();
	}
	
	//should be a thread and that should be started
	
	public void startThread(){
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		try{
			String inline = "";
			String indtDisp = "";
            while (!(inline = inputStream.readLine().toUpperCase()).isEmpty()){ //her ventes p� input
            	if (inline.startsWith("RM")){						
                	// ikke implementeret

            	}
                else if (inline.startsWith("D")){
                    if (inline.equals("DW"))
                        indtDisp="";
                    else
                        indtDisp=(inline.substring(2, inline.length()));//her skal anf�rselstegn udm.
                        outputStream.writeBytes("DB"+"\r\n");
                }
                else if (inline.startsWith("T")){
                    outputStream.writeBytes("T S " + (tara) + " kg "+"\r\n");		//HVOR MANGE SPACE?
                    tara=brutto;
                }
                else if (inline.startsWith("S")){
                    outputStream.writeBytes("S S " + (brutto-tara)+ " kg "  +"\r\n");//HVOR MANGE SPACE?
                }
                else if (inline.startsWith("B")){ //denne ordre findes ikke p� en fysisk v�gt
                    String temp= inline.substring(2,inline.length());
                    brutto = Double.parseDouble(temp);
                    outputStream.writeBytes("DB"+"\r\n");
                }
                else if ((inline.startsWith("Q"))){
                    System.out.println("");
                    System.out.println("Program stoppet Q modtaget paa com   port");
                    System.in.close();
                    System.out.close();
                    outputStream.close();
                    inputStream.close();
                    socket.close();
                }
                
                else { 
                	System.out.println(inline);
                    outputStream.writeBytes("ES"+"\r\n");
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
	}
	
	public InetAddress getIP(){
		return ipAdress;
	}
	
	public DataOutputStream getOutputStream(){
		return outputStream;
	}
	
	public BufferedReader getInputStream(){
		return inputStream;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public boolean shouldRemove(){
		if(socket != null){
			if(socket.isClosed() || inputStream == null || outputStream == null){
				return true;
			}
		}
		else{
			return true;
		}
		return false;
	}
	
	public double getTara(){
		return tara;
	}
	
	public double getBrutto(){
		return brutto;
	}
}
