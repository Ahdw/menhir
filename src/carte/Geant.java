package carte;

import cailloux.Cailloux;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Geant est utilisée dans la classe CarteIngredient. Elle représente la ligne géant de la carte
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
	 * Méthode réalisant l'action de piocher en fonction de la force de la carte à la saison actuelle.
	 */
	public void effectuer() {
		int intent = force[partie.getSaison()];
		for(int i=0;i<intent;i++){
			partie.getJoueurActuel().getGraine().add(new Cailloux());
		}
		
		this.annonce = nom+" "+intent;
	}

}
