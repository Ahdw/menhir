package carte;

import java.util.Iterator;
import java.util.LinkedList;

import exception.InputException;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe CarteIngredient est une composition des cartes @see Geant, @see Farfadet et @see Engrais.
 */

public class CarteIngredient extends Carte {
	LinkedList<Carte> action = new LinkedList<Carte>();
	
	/**
	 * Le constructeur de CarteIngredient.
	 * @param nom : String
	 * @param serial : String
	 * @param actions : Tableau de cartes
	 */
	public CarteIngredient(String nom, String serial, Carte[] actions){
		this.nom = nom;
		this.serial = serial;
		for(int a=0;a<3;a++){
			this.action.add(actions[a]);
		}
	}
	
	
	/**
	 * methode permettant de réaliser l'action de la carte choisie.
	 */
	public void effectuer() {
		int choix = 0;
		int flag = 1;
		while(flag == 1){
			try {
				choix = partie.getJoueurActuel().getStrategie().choisisAction();
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
			
		}
		action.get(choix).effectuer();
		this.annonce = action.get(choix).getAnnonce();
	}
	
	/**
	 * méthode pour décrire une carte ingrédient en chaine de caractère.
	 * @return la chaine de caractère.
	 */
	public String toString(){
		int k = 4;
		String str = this.nom+"\n";
		
		Iterator<Carte> it = action.iterator();
		while(it.hasNext()){
			Carte c = it.next();
			str += c.nom;				//add action name
			
			
										
			for(int j=0;j<k;j++){		//add k spaces
				str += " ";
			}
				
			
			for(int i=0;i<4;i++){
				str += c.force[i]+" ";	//add value and a space	
			}
			
			str += "\n";				//add \n
			k = k/2;					//k changes for next time
			
		}
		
		return str;
	}

}
