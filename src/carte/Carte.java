package carte;

import partie.Partie;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Carte est une classe abstraite représentant les cartes du jeu du menhir.
 * Elle se spécialise en 
 * @see CarteIngredient
 * @see TaupeGeante
 * @see Chien
 * @see Geant
 * @see Engrais
 * @see Farfadet
 */

public abstract class Carte {
	protected String nom,serial;
	protected String annonce;
	protected int[] force;
	protected Partie partie = Partie.getPartie();
	
	
	public abstract void effectuer();
	
	public String getSerial(){
		return this.serial;
	}
	
	public String getAnnonce(){
		return annonce;
	}
}
