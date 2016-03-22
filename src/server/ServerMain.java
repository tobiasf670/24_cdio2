package server;

public class ServerMain {
	
    public static void main(String[] args){
    	if(args.length>0){
    		ServerRun serverRun = new ServerRun(args[0]);
    		serverRun.start();
    	}else{
    		ServerRun serverRun = new ServerRun("8000");
    		serverRun.start();
    	}
    }
}