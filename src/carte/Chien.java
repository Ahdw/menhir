package carte;

import joueur.Joueur;
import partie.Partie;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Chien est h�rite de carte. Elle repr�sente la carte chien de garde dans le jeu du menhir.
 */

public class Chien extends Carte {

	
	/**
	 * Constructeur de la classe chien.
	 * @param serial : String
	 * @param a : tableau d'entiers
	 */
	public Chien(String serial, int[] a){
		this.serial = serial;
		nom = "Chien De Garde";
		force = a;
	}
	
	
	/**
	 * cette methode est appel�e lorsqu'on utilise le chien pour changer l'affichage. 
	 */
	public void effectuer() {
		this.annonce = "laisse son chien";
		Partie.getPartie().getJoueurActuel().getAllie().clear();
	}
	
	
	/**
	 * cette m�thode r�alise l'action du chien.
	 * @param intent : int
	 * @param j : Joueur
	 * @return le r�sultat de l'action
	 */
	public int effectuer(int intent,Joueur j) {

		int force = this.getForce();
		
		if(intent < force){											//intent < force, intent = 0
			intent = 0;
		}
		else{
			intent -= force;										//intent > force, intent = intent - force;
		}
		this.annonce = intent + " graines saveds!";
		Partie.getPartie().getMode().getAllie().add(this);			//return this card to talon
		j.getAllie().clear();										//poser cette carte chien
		return intent;
	}
	
	/**
	 * Accesseur de la force de la carte en fonction de la saison.
	 * @return force
	 */
	public int getForce(){
		return force[Partie.getPartie().getSaison()];
	}
	
	/**
	 * Methode cr�ant une chaine de caract�re pour d�crire la carte chien.
	 * @return str : String
	 */
	public String toString(){
		String str = this.nom;
		
		for (int i=0;i<4;i++){
			str += " "+force[i];
		}
		
		str += "\n";
		return str;
	}

}
