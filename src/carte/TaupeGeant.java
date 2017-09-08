package carte;

import exception.InputException;
import joueur.Joueur;


/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe TaupeGeante repr�sente la carte alli� taupe g�ante dans le jeu du menhir. 
 * Elle permet de d�truire des m�nhirs � un autre joueur.
 */

public class TaupeGeant extends Carte {
	Joueur j;
	
	/**
	 * Constructeur de la classe TaupeGeante
	 * @param serial : String
	 * @param a : Tableau d'entiers
	 */
	public TaupeGeant(String serial, int[] a){
		this.serial = serial;
		nom = "Taupe Geant";
		force = a;
	}
	
	/**
	 * M�thode permettant de lancer l'action de TaupeGeante.
	 */
	public void effectuer() {
		int intent = confirm(this.force[partie.getSaison()]);
		for(int i=0;i<intent;i++){
			j.getMenhir().remove(0);
		}
		this.annonce = nom+" "+j.getNom()+" "+intent;
	}
	
	/**
	 * Permet de r�cup�rer la force de la carte en fonction de la saison actuelle.
	 * @param a : int
	 * @return force de l'action.
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
		if(a < j.getMenhir().size()){
			force = a;
		}
		else{
			force = j.getMenhir().size();
		}
		return force;
	}
	
	/**
	 * Permet de r�aliser l'action de la taupe en fonction de la force de la carte et de la protection du joueur.
	 * @param origine
	 */
	public void effectuer(Joueur origine){
		int intent = confirm(this.force[partie.getSaison()],origine);
		for(int i=0;i<intent;i++){
			j.getMenhir().remove(0);
		}
		this.annonce = nom+" "+j.getNom()+" "+intent;
	}
	
	/**
	 * Permet de r�cup�rer la force de l'action en fonction de la saison actuelle et de la protection du joueur.
	 * @param a
	 * @param origine
	 * @return
	 */
	int confirm(int a, Joueur origine){
		int force = 0;
		j = null;
		int flag = 1;
		while(flag == 1){
			try {
				j = origine.getStrategie().choisisJoueur();
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
		if(a < j.getMenhir().size()){
			force = a;
		}
		else{
			force = j.getMenhir().size();
		}
		return force;
	}
	
	
	/**
	 * M�thode permettant de d�crire la carte taupe g�ante sous forme de caine de caract�re
	 * @return str : chaine de caract�re
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
