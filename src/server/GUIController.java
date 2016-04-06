package server;

import javax.swing.JTextArea;

public class GUIController {

	
	private Simulator serverGUI;
	
	
	public GUIController(WeightDTO weight){
		
		serverGUI = new Simulator(weight);
		serverGUI.setVisible(true);
	}

	public Simulator getServerGUI() {
		return serverGUI;
	}
	
	public JTextArea getMainDisp(){
		return this.serverGUI.getMainDisp();
	}
	
}
