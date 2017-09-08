package carte;

import cailloux.Cailloux;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Engrais est une classe utilis�e dans la classe CarteIngredient. Elle repr�sente la ligne engrais de la carte 
 * et permet de faire grandir des menhirs.
 */


public class Engrais extends Carte {
	
	/**
	 * Constructeur
	 * @param a : tableau d'entiers donnant la force de la carte.
	 */
	public Engrais(int[] a){
		nom = "Engrais";
		force = a;
	}
	
	/**
	 * M�thode r�alisant l'action de la carte : faire pousser des graines.
	 */
	public void effectuer() {
		int force = confirm(this.force[partie.getSaison()]);
		for(int i=0;i<force;i++){
			Cailloux c = partie.getJoueurActuel().getGraine().get(0);
			c.gandir();
			partie.getJoueurActuel().getMenhir().add(c);
			partie.getJoueurActuel().getGraine().remove(0);
		}
		
		this.annonce = nom+" "+ force;
		
	}

	/**
	 * M�thode retournant la force de la carte � la saison actuelle.
	 * @param a : int
	 * @return force de la carte
	 */
	int confirm(int a){
		int force = 0;
		if(a <= partie.getJoueurActuel().getGraine().size()){
			force = a;
		}
		else{
			force = partie.getJoueurActuel().getGraine().size();
		}
		return force;
	}
}
