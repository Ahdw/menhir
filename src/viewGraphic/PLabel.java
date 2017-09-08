package viewGraphic;

import javax.swing.*;

import joueur.Joueur;

public class PLabel extends JLabel {

	private static final long serialVersionUID = -3672060549663767188L;
	private Joueur j;
	public PLabel(Joueur j){
		this.j = j;
	}
	public Joueur getJ(){
		return this.j;
	}
}
