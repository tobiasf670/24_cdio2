package server;

public class GUIController {

	
	private Simulator serverGUI;
	
	
	public GUIController(WeightDTO weight){
		
		serverGUI = new Simulator(weight);
		serverGUI.setVisible(true);
	}

	public Simulator getServerGUI() {
		return serverGUI;
	}
	
}
