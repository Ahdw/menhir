package modeDeJeu;
import java.util.LinkedList;

import carte.Carte;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * L'interface Mode permet de définir quelles méthodes les différents modes de jeu devront implémenter.
 * @see ModeAvance
 * @see modeRapide
 */

public interface Mode {
	
	/**
	 * Permet d'initialiser les cartes et de les distribuer.
	 */
	public void initCarte();
	
	/**
	 * Permet de démarret une manche.
	 */
	public void demarrerManche();
	
	/**
	 * Permet de faire dérouler la partie avec l'ordre des joueurs et la geston des saisons.
	 */
	public void derouler();
	
	/**
	 * Permet de changer le joueur actuel.
	 */
	public void setJoueurActuel();
	
	/**
	 * Permet de passer à la saison suivante.
	 */
	public void changeSaison();
	
	/**
	 * Permet de récupérer la pioche.
	 * @return Linkedlist <Carte> : la liste des cartes de la pioche.
	 */
	public LinkedList<Carte> getTalon();
	
	/**
	 * permet de récupérer la liste des cartes alliées.
	 * @return LinkedList <Carte> : la liste des cartes alliés.
	 */
	public LinkedList<Carte> getAllie(); //uniquement pour la mode avance
	
	/**
	 * Permet de terminer la partie en comptant les points.
	 */
	public void fin();
}
