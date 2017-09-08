package strategie;

import java.util.ArrayList;

import partie.Partie;
import carte.Carte;
import joueur.Joueur;


/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe StrategieForte h�rite de Strat�gie et impl�mente les m�thodes relatives � la strat�gie forte.
 * 
 * @see Strategie
 */


public class StrategieForte extends Strategie {
	
	private int valeurAJouer=0;

	/**
	 * Constructeur de la classe.
	 */
	public StrategieForte(){
		
	}
	
	/**
	 * Permet de choisir un joueur � attaquer.
	 * return target : Joueur choisi.
	 */
	public Joueur choisisJoueur() {
		int random = (int) (Math.random()*Partie.getPartie().getNbJoueur());
		Joueur target = Partie.getPartie().getListeJoueur().get(random);
		while (target.equals(Partie.getPartie().getJoueurActuel())){
			target = Partie.getPartie().getListeJoueur().get((random+1)%(Partie.getPartie().getNbJoueur()));
		}
		return target;
	}

	
	/**
	 * Permet de choisir une carte � jouer.
	 * @return c : Carte � jouer.
	 */
	public Carte choisisCarte() {
		ArrayList<Carte> main = Partie.getPartie().getJoueurActuel().getMain();
		int a = main.size();
		int index = (int) (Math.random()*a);
		c = main.get(index);
		return c;
	}


	/**
	 * Permet de choisir une action � effectuer.
	 * @return int : action choisie.
	 */
	public int choisisAction() {
		int val = 0;
		switch (valeurAJouer%4)
		{
			case 0:
				val = 0;
				valeurAJouer++;
				break;
			case 1:
				val = 2;
				valeurAJouer++;
				break;
			case 2:
				val = 1;
				valeurAJouer++;
				break;
			case 3:
				val = 1;
				valeurAJouer++;
				break;
		}
		
		return val;
	}


	/**
	 * Permet de choisir au d�but de chaque manche dans le mode avanc� si l'on veut piocher une carte alli� ou deux graines.
	 * @return int : choix.
	 */
	public int choisis() {
		return (int) (Math.random()+1);
	}


	/**
	 * Permet de choisir si l'on veut jouer une carte alli�.
	 * @return int : choix.
	 */
	public int choisisJouerAllie() {
		return (int) (Math.random()*2);
	}
}
