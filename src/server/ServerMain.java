package server;

public class ServerMain {
	 
	
	
    public static void main(String[] args){
    	
    	WeightDTO weightDTO = new WeightDTO();
    	GUIController gc = new GUIController(weightDTO);
    	
    	
    	if(args.length>0){
    		ServerRun serverRun = new ServerRun(args[0]);
    		serverRun.start(gc, weightDTO);
    	}else{
    		ServerRun serverRun = new ServerRun("8000");
    		serverRun.start(gc, weightDTO);
    	}
    }
}