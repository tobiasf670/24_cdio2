package server;

public class ServerMain {
	 
	
	
    public static void main(String[] args){
    	
    	WeightDTO weightDTO = new WeightDTO();
    	GUIController gc = new GUIController(weightDTO);
    	
    	
    	if(args.length>0){
    		ServerRun serverRun = new ServerRun(args[0], gc, weightDTO);
    		serverRun.start();
    	}else{
    		ServerRun serverRun = new ServerRun("8000", gc, weightDTO);
    		serverRun.start();
    	}
    }
}