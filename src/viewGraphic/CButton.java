package viewGraphic;

import javax.swing.JButton;

import carte.Carte;

public class CButton extends JButton {

	private static final long serialVersionUID = 5294851333051103812L;
	private Carte c;
	
	public CButton(Carte c){
		this.c = c;
	}

	public Carte getC(){
		return c;
	}
}
