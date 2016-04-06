package server;

import java.util.Observable;

public class WeightDTO extends Observable{
	
	private double tara = 0;
	private double brutto = 0;
	private String secDisp;
	private String mainDisp;
	
	public double getTara() {
		return tara;
	}
	public void setTara(double tara) {
		this.tara = tara;
		setChanged();
	}
	public double getBrutto() {
		return brutto;
	}
	public void setBrutto(double brutto) {
		this.brutto = brutto;
		setChanged();
	}
	public String getSecDisp() {
		return secDisp;
	}
	public void setSecDisp(String secDisp) {
		this.secDisp = secDisp;
		setChanged();
	}
	public String getMainDisp() {
		return mainDisp;
	}
	public void setMainDisp(String mainDisp) {
		this.mainDisp = mainDisp;
		setChanged();
		notifyObservers("maindisp");
	}

}
