package joueur;

import java.util.Collections;
import java.util.LinkedList;
import strategie.Strategie;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe JoueurVirtuel permet de représenter les joueurs contrôlés par l'ordinateur.
 */

public class JoueurVirtuel extends Joueur {

	private static final String[] NAMES_M = {"Mike","John","Sam","Jones","Alex"};
	private static final String[] NAMES_F = {"Maria","Jane","Christine","Cherry","Pussy"};
	private static LinkedList<String> names_m = new LinkedList<String>();
	private static LinkedList<String> names_f = new LinkedList<String>();
	
	/**
	 * Constructeur de la classe.
	 * @param strategie : C'est la stratégie que vont suivre les joueurs virtuels.
	 */
	public JoueurVirtuel(Strategie strategie){
		this.strategie = strategie;
		setProfile();
	}
	
	/**
	 * Initialise les joueurs virtuels en leur donnant des noms.
	 */
	public static void init(){
		for(int i=0; i<NAMES_M.length;i++){
			names_m.add(NAMES_M[i]);
		}
		
		for(int i=0; i<NAMES_F.length;i++){
			names_f.add(NAMES_F[i]);
		}
	}
	
	
	/**
	 * Permet de définir les différentes informations des joueurs virtuels.
	 */
	public void setProfile() {
		double a = Math.random();
		age = (int)((a+8)*(Math.random()*3+1));//age ~ 8-36
		if(a <= .5){
			sexe = true;
		}
		else{
			sexe = false;
		}
		
		if(sexe == false){
			Collections.shuffle(names_m);
			nom = names_m.pop();
		}
		else{
			Collections.shuffle(names_f);
			nom = names_f.pop();
		}
	}
}
