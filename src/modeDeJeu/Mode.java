package modeDeJeu;
import java.util.LinkedList;

import carte.Carte;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * L'interface Mode permet de d�finir quelles m�thodes les diff�rents modes de jeu devront impl�menter.
 * @see ModeAvance
 * @see modeRapide
 */

public interface Mode {
	
	/**
	 * Permet d'initialiser les cartes et de les distribuer.
	 */
	public void initCarte();
	
	/**
	 * Permet de d�marret une manche.
	 */
	public void demarrerManche();
	
	/**
	 * Permet de faire d�rouler la partie avec l'ordre des joueurs et la geston des saisons.
	 */
	public void derouler();
	
	/**
	 * Permet de changer le joueur actuel.
	 */
	public void setJoueurActuel();
	
	/**
	 * Permet de passer � la saison suivante.
	 */
	public void changeSaison();
	
	/**
	 * Permet de r�cup�rer la pioche.
	 * @return Linkedlist <Carte> : la liste des cartes de la pioche.
	 */
	public LinkedList<Carte> getTalon();
	
	/**
	 * permet de r�cup�rer la liste des cartes alli�es.
	 * @return LinkedList <Carte> : la liste des cartes alli�s.
	 */
	public LinkedList<Carte> getAllie(); //uniquement pour la mode avance
	
	/**
	 * Permet de terminer la partie en comptant les points.
	 */
	public void fin();
}
