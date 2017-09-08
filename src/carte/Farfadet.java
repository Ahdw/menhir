package carte;

import cailloux.Cailloux;
import exception.InputException;
import joueur.Joueur;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Farfadet est utilisée dans la classe CarteIngredient. Elle représente la ligne farfadet de la carte
 * et permet de voler des graines à un autre joueur.
 */

public class Farfadet extends Carte {
	private int intent;
	private Joueur j;
	
	/**
	 * Constructeur de Farfadet.
	 * @param a : int
	 */
	public Farfadet(int[] a){
		nom = "Farfadet";
		force = a;
	}
	
	/**
	 * Méthode permettant de réaliser l'action de voler des graines.
	 */
	public void effectuer() {
		intent = confirm(force[partie.getSaison()]);
		for(int i=0;i<intent;i++){
			j.getGraine().remove(0);
			partie.getJoueurActuel().getGraine().add(new Cailloux());
		}
		
		this.annonce = nom+" from "+j.getNom()+" "+intent;
	}

	/**
	 * Méthode permettant de récupérer la force de l'action en fonction de la saison et de la protection du joueur.
	 * @param a : int
	 * @return force de l'action
	 */
	int confirm(int a){
		int force = 0;
		j = null;
		int flag = 1;
		while(flag == 1){
			try {
				j = partie.getJoueurActuel().getStrategie().choisisJoueur();
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(a < j.getGraine().size()){
			force = a;
		}
		else{
			force = j.getGraine().size();
		}
		
		j.getStrategie().confirm(force, j);
		
		return force;
	}
}
