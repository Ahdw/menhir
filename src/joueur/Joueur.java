package joueur;

import java.util.ArrayList;
import cailloux.Cailloux;
import carte.Carte;
import controle.Controle;
import exception.InputException;
import partie.Partie;
import strategie.Strategie;


/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Joueur est une classe abstraite qui reprend le fonctionnement général des joueurs.
 * Un joueur possède une main composée de cartes et d'une liste de cartes alliés @see Carte
 * Il possède également des graines et des menhirs @see Cailloux
 * et un nombre de points en plus de ses caractéristiques "personnelles".
 */

public abstract class Joueur {
	protected ArrayList<Carte> main = new ArrayList<Carte>();
	protected ArrayList<Carte> allie = new ArrayList<Carte>();
	protected ArrayList<Cailloux> graine = new ArrayList<Cailloux>();
	protected ArrayList<Cailloux> menhir = new ArrayList<Cailloux>();
	
	protected int point = 0;
	
	protected String nom;
	protected int age;
	protected boolean sexe;//0=homme,1=femme
	
	protected Controle ctrl = Partie.getCtrl();
	protected Strategie strategie;
	
	public abstract void setProfile() throws InputException;
	
	/**
	 * 
	 * @return main : les cartes de la main du joueur
	 */
	public ArrayList<Carte> getMain(){
		return this.main;
	}
	
	/**
	 * 
	 * @return allie : les cartes alliées du joueur.
	 */
	public ArrayList<Carte> getAllie(){
		return this.allie;
	}
	
	/**
	 * 
	 * @return graine : les graines du joueur.
	 */
	public ArrayList<Cailloux> getGraine(){
		return this.graine;
	}
	
	/**
	 * 
	 * @return menhir : les menhirs du joueur.
	 */
	public ArrayList<Cailloux> getMenhir(){
		return this.menhir;
	}
	
	/**
	 * 
	 * @return nom : le nom du joueur.
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * 
	 * @return age : l'age du joueur.
	 */
	public int getAge(){
		return this.age;
	}
	
	/**
	 * 
	 * @return "homme" si le joueur est un homme et "femme" si lle joueur est une femme.
	 */
	public String getSexe(){
		if(sexe){
			return "femme";
		}
		else{
			return "homme";
		}
	}
	
	/**
	 * 
	 * @return strategie : la stratégie du joueur.
	 */
	public Strategie getStrategie(){
		return this.strategie;
	}
	
	/**
	 * 
	 * @return point : le nombre de points du joueur.
	 */
	public int getPoint(){
		return point;
	}
	
	/**
	 * Ajoute des points au joueur en fonction du nombre de ménhirs qu'il a.
	 */
	public void addPoint(){
		this.point += this.getMenhir().size();
	}
}
