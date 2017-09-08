package strategie;

import carte.Carte;
import exception.InputException;
import joueur.Joueur;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe StrategieManuelle h�rite de Strat�gie et impl�mente les m�thodes relatives � la strat�gie manuelle.
 * 
 * @see Strategie
 */


public class StrategieManuele extends Strategie {
	
	/**
	 * Constructeur de la classe.
	 */
	public StrategieManuele(){
		
	}
	
	/**
	 * Permet de choisir manuellement le nom du jouer � attaquer.
	 * @return Joueur : la cible choisie.
	 */
	public Joueur choisisJoueur() throws InputException {
		return ctrl.saisieTarget();	
	}

	/**
	 * Permet de choisir la carte � jouer manuellement.
	 * @return c : Carte choisie � jouer.
	 */
	public Carte choisisCarte() throws InputException {
		try {
			c = ctrl.saisieCarte();
		}
		catch (InputException e){
			throw e;
		}
		
		return c;
	}

	/**
	 * Permet de choisir manuellement l'action � effectuer de la carte.
	 * @return action : int choix de l'action � effectuer.
	 */
	public int choisisAction() throws InputException {
		int action;
		try {
			action = ctrl.saisieAction();
		}
		catch (InputException e){
			throw e;
		}
		
		return action;
	}


	/**
	 * Permet de choisir manuellement au d�but de chaque manche dans le mode avanc� si on pioche une carte alli� ou deux graines.
	 * @return int : choix.
	 */
	public int choisis() throws InputException {
		int choix;
		try {
			choix = ctrl.saisieChoix();
		}
		catch (InputException e){
			throw e;
		}
		
		return choix;
	}



	/**
	 * Permet de choisir manuellement si l'on veux jouer une carte alli�.
	 * @return choix : int choix effectu�.
	 */
	public int choisisJouerAllie() throws InputException {
		int choix;
		try {
			choix = ctrl.saisieChoixAllie();
		}
		catch (InputException e){
			throw e;
		}
		
		return choix;
	}

}
