package partie;

import java.util.ArrayList;
import java.util.LinkedList;

import controle.Controle;
import controle.Graphic;
//import controle.LigneDeCommande;
import exception.InputException;
import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import modeDeJeu.Mode;
import modeDeJeu.ModeAvance;
import modeDeJeu.ModeRapide;
import strategie.Strategie;
import strategie.StrategieFaible;
import strategie.StrategieForte;
import viewGraphic.ViewCarte;
import viewGraphic.ViewChamps;
import viewGraphic.ViewParametre;
import viewGraphic.ViewPoint;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe partie représente le jeu du menhir avec les différent joueurs, la saison, la difficultée associée (ou la stratégie choisie) et le mode de partie.
 */

public class Partie implements Runnable{
	private static Partie partie = null;
	
	private static Controle ctrl;
	private Mode mode;
	private Strategie strategie;
	private int saison;
	
	private int nbJoueurs; 
	private Joueur joueurActuel, joueurReel;
	private LinkedList <Joueur> listeJoueurs  = new LinkedList <Joueur>();
	private ArrayList<Joueur> listeJoueurVirtuel = new ArrayList<Joueur>();
	
	/**
	 * Constructeur de la classe Partie à qui est associé une nterface graphique.
	 */
	private Partie(){
		ctrl = Graphic.getGraphic();
		setMode();
		setStrategie();
		setNbJoueur();
		Thread it = new Thread(this);
		it.start();
	}
	
	/**
	 * Permet d'instancier une partie et de la renvoyer.
	 * @return partie : la partie du jeu de menhir qui est instanciée si elle n'existe pas.
	 */
	public static Partie getPartie(){
		if(partie == null){
			partie = new Partie();
		}
		return partie;
	}
	
	/**
	 * Renvoie le mode de jeu.
	 * @return mode : mode de jeu avancé ou rapide.
	 */
	public Mode getMode(){
		return mode;
	}
	
	/**
	 * renvoie le contrôle graphique qui est associée à la classe.
	 * @return ctrl : controle.
	 */
	public static Controle getCtrl(){
		return ctrl;
	}
	
	/**
	 * Cette méthode permet d'associer un mode à la partie.
	 * @param mode
	 * @throws InputException
	 */
	void trySetMode(int mode) throws InputException{
		if(mode > 1){
			throw new InputException("mode invalide");
		}
		
		if(mode == 1){
			this.mode = new ModeAvance();
		}
		else if(mode == 0){
			this.mode = new ModeRapide();
			
		}
	}
	
	/**
	 * Permet d'associer une stratégie, ou difficultée, à la partie.
	 * @param strategie
	 * @throws InputException
	 */
	void trySetStrategie(int strategie) throws InputException{

		if(strategie > 1){
			throw new InputException("strategie invalide");
		}
		if(strategie == 1){
			this.strategie = new StrategieForte();
		}
		if(strategie == 0){
			this.strategie = new StrategieFaible();
		}

	}
	
	/**
	 * Méthode pour initialiser tous les joueurs de la partie et les ajouter dans l'ordre dans la liste des joueurs..
	 * @throws InputException
	 */
	void tryInitJoueur() throws InputException{
		Joueur j = new JoueurReel();
		listeJoueurs.add(j);
		this.joueurReel = j;
		
		JoueurVirtuel.init();
		for(int i=0;i<nbJoueurs-1;i++){
			j = new JoueurVirtuel(strategie);
			this.listeJoueurVirtuel.add(j);
			
			if(j.getSexe().equals("femme") && j.getAge()<listeJoueurs.getFirst().getAge()){
				listeJoueurs.addFirst(j);
			}
			else if(j.getSexe().equals("femme") && listeJoueurs.getFirst().getSexe().equals("homme")){
				listeJoueurs.addFirst(j);
			}
			else{
				listeJoueurs.add(j);
			}
		}
		
		setJoueurActuel(listeJoueurs.getFirst());
	}
	
	/**
	 * Méthode pour démarrer la partie en lançant l'initialistion des cartes.
	 */
	public void demarrer(){
		initJoueur();
		this.mode.initCarte();
		this.mode.derouler();
	}
	
	/**
	 * Méthode pour changer le joueur actuel.
	 * @param j : nouveau Joueur actuel.
	 */
	public void setJoueurActuel(Joueur j){
		joueurActuel = j;
	}
	
	/**
	 * Permet de récupérer le joueur actuel.
	 * @return joueurActuel.
	 */
	public Joueur getJoueurActuel(){
		return joueurActuel;
	}
	
	/**
	 * Permet de changer la saison.
	 * @param a : nouvelle saison.
	 */
	public void setSaison(int a){
		saison = a;
	}
	
	/**
	 * Permet de récupérer la saison.
	 * @return saison.
	 */
	public int getSaison(){
		return saison;
	}
	
	/**
	 * Permet de récupérer le nombre de joueurs.
	 * @return nbJoueurs.
	 */
	public int getNbJoueur(){
		return nbJoueurs;
	}
	
	/**
	 * Permet de récupérer la liste des joueurs.
	 * @return listeJoueurs.
	 */
	public LinkedList<Joueur> getListeJoueur(){
		return listeJoueurs;
	}
	
	/**
	 * Permet de récupérer la liste des joueurs virtuels.
	 * @return listeJoueursVirtuels.
	 */
	public ArrayList<Joueur> getListeJoueurVirtuel(){
		return listeJoueurVirtuel;
	}
	
	/**
	 * Permet de récupérer la saison actuelle sous forme de chaine de caractère.
	 * @return s : saison actuelle.
	 */
	public String toString(){
		String s = "SPRING";
		if(saison == 1){
			s = "SUMMER";
		}
		else if(saison == 2){
			s = "AUTOMNE";
		}
		else if(saison == 3){
			s = "WINTER";
		}
		return s;
	}
	
	/**
	 * Permet de définir le mode de la partie en mode avancé ou rapide.
	 */
	void setMode(){
		int flag = 1;
		while(flag == 1){
			try {
				trySetMode(ctrl.saisieMode());
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Permet de définir la stratégie (ou niveau de difficulté) de la partie.
	 */
	void setStrategie(){
		int flag = 1;
		while(flag == 1){
			try {
				trySetStrategie(ctrl.saisieStrategie());
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Permet de lancer l'initialisation des joueurs.
	 */
	void initJoueur(){
		int flag = 1;
		while(flag == 1){
			try {
				tryInitJoueur();
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Permet de définir le nombre de joueurs dans la partie.
	 */
	public void setNbJoueur(){
		try {
			this.nbJoueurs = ctrl.saisieNbJoueur();
		} catch (InputException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de récupérer le joueurRéel
	 * @return joueurReel
	 */
	public Joueur getJoueurReel(){
		return this.joueurReel;
	}
	
	/**
	 * Méthode exécutée au lancement du thread qui va démarrer la partie.
	 */
	public void run(){
		this.demarrer();
	}
	
	/**
	 * Permet de réinitialiser toutes les options et toues les scores de la partie.
	 */
	public static void reset(){
		partie = null;
		Graphic.reset();
		ViewCarte.reset();
		ViewChamps.reset();
		ViewParametre.reset();
		ViewPoint.reset();
	}
}
