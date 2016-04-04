package server;

public class GUIController {

	
	private Simulator serverGUI;
	
	public GUIController(){
		
		serverGUI = new Simulator();
		serverGUI.setVisible(true);
	}

	public Simulator getServerGUI() {
		return serverGUI;
	}

	public void setServerGUI(Simulator serverGUI) {
		this.serverGUI = serverGUI;
	}
	
}
