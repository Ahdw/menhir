package strategie;

import java.util.ArrayList;

import carte.Carte;
import exception.InputException;
import joueur.Joueur;
import partie.Partie;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe StrategieFaible hérite de Stratégie et implémente les méthodes relatives à la stratégie faible.
 * 
 * @see Strategie
 */

public class StrategieFaible extends Strategie {

	/**
	 * constructeur de la classe.
	 */
	public StrategieFaible(){

	}

	/**
	 * Permet de choisir un joueur sur lequel faire une action.
	 * @return target : Joueur choisis.
	 * @throws InputException
	 */
	public Joueur choisisJoueur() {
		int random = (int) (Math.random()*Partie.getPartie().getNbJoueur());
		Joueur target = Partie.getPartie().getListeJoueur().get(random);
		while(target.equals(Partie.getPartie().getJoueurActuel())){
			target = Partie.getPartie().getListeJoueur().get((random+1)%(Partie.getPartie().getNbJoueur()));
		}
		return target;
	}

	/**
	 * Permet de choisir la carte que l'on veut jouer.
	 * @return c : Carte choisie.
	 */
	public Carte choisisCarte() {
		ArrayList<Carte> main = Partie.getPartie().getJoueurActuel().getMain();
		int a = main.size();
		int index = (int) (Math.random()*a);
		c = main.get(index);
		return c;
	}

	/**
	 * Permet de choisir une action à faire. Elle est choisie aléatoirement pour la stratégie facile.
	 * @return int action choisie.
	 */
	public int choisisAction() {
		return (int) (Math.random()*3);
	}


	/**
	 * Permet de choisir si le joueur veut piocher une carte allié ou deux graines.
	 * @return int action choisie
	 */
	public int choisis() {
		return (int) (Math.random()+1);
	}

	/**
	 * Permet de choisir si l'on veut jouer une carte allié.
	 * @return int action choisie.
	 */
	public int choisisJouerAllie() {
		// TODO Auto-generated method stub
		return (int) (Math.random()*2);
	}
}
