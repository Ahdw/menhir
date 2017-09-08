package carte;

import cailloux.Cailloux;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Geant est utilis�e dans la classe CarteIngredient. Elle repr�sente la ligne g�ant de la carte
 * et permet de piocher des graines.
 */

public class Geant extends Carte {
	
	/**
	 * Constructeur de la classe.
	 * @param a : tableau d'entiers
	 */
	public Geant(int[] a){
		nom = "Geant";
		force = a;
	}
	
	
	/**
	 * M�thode r�alisant l'action de piocher en fonction de la force de la carte � la saison actuelle.
	 */
	public void effectuer() {
		int intent = force[partie.getSaison()];
		for(int i=0;i<intent;i++){
			partie.getJoueurActuel().getGraine().add(new Cailloux());
		}
		
		this.annonce = nom+" "+intent;
	}

}
