package viewGraphic;

import javax.swing.JButton;

import joueur.Joueur;

public class PButton extends JButton {
	private static final long serialVersionUID = 1L;
	private Joueur j;
	
	public PButton(Joueur j){
		this.j = j;
	}
	
	public Joueur getJ(){
		return j;
	}
}
