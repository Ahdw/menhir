package modeDeJeu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import cailloux.Cailloux;
import carte.Carte;
import carte.CarteIngredient;
import carte.Engrais;
import carte.Farfadet;
import carte.Geant;
import controle.Graphic;
import joueur.Joueur;
import joueur.JoueurReel;
import partie.Partie;
import viewGraphic.ViewCarte;
import viewGraphic.ViewChamps;
import viewGraphic.ViewPrinciple;


/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe ModeRapide permet de gérer le déroulement d'une partie 
 * rapide en gérant le tour des jours et les saisons.
 */


public class ModeRapide implements Mode {

	/**
	 * Contructeur de la classe.
	 */
	public ModeRapide(){
		if (Partie.getCtrl() instanceof Graphic){
			this.vp = ViewPrinciple.getView();
		}
	}
	private static final String[] nom = {"RAYON DE LUNE","CHANT DE SIRENE","LARME DE DRYADE","FONTAINE D'EAU PURE",
		"POUDRE D'OR","RACINE D'ARC-EN-CIEL","ESPRIT DE DOLMEN","RIRE DE FEES"};
	private static final int[][] value = {	//rayon de lune
		{1,1,1,1},{2,0,1,1},{2,0,2,0},//rayon de lune 1
		{2,0,1,1},{1,3,0,0},{0,1,2,1},//rayon de lune 2
		{0,0,4,0},{0,2,2,0},{0,0,1,3},

		//chant de sirene
		{1,3,1,0},{1,2,1,1},{0,1,4,0},
		{2,1,1,1},{1,0,2,2},{3,0,0,2},
		{1,2,2,0},{1,1,2,1},{2,0,1,2},

		//larme de dryade
		{2,1,1,2},{1,1,1,3},{2,0,2,2},
		{0,3,0,3},{2,1,3,0},{1,1,3,1},
		{1,2,1,2},{1,0,1,4},{2,4,0,0},

		//dontaine d'eau pure
		{1,3,1,2},{2,1,2,2},{0,0,3,4},
		{2,2,0,3},{1,1,4,1},{1,2,1,3},
		{2,2,3,1},{2,3,0,3},{1,1,3,3},

		//poudre d'or
		{2,2,3,1},{2,3,0,3},{1,1,3,3},
		{2,2,2,2},{0,4,4,0},{1,3,2,2},
		{3,1,3,1},{1,4,2,1},{2,4,1,1},

		//racine d'arc-en-ciel
		{4,1,1,1},{1,2,1,3},{1,2,2,2},
		{2,3,2,0},{0,4,3,0},{2,1,1,3},
		{2,2,3,0},{1,1,1,4},{2,0,3,2},

		//esprit de dolmen
		{3,1,4,1},{2,1,3,3},{2,3,2,2},
		{2,4,1,2},{2,2,2,3},{1,4,3,1},
		{3,3,3,0},{1,3,3,2},{2,3,1,3},

		//rire de fees
		{1,2,2,1},{1,2,3,0},{0,2,2,2},
		{4,0,1,1},{1,1,3,1},{0,0,3,3},
		{2,0,1,3},{0,3,0,3},{1,2,2,1},
	};

	private LinkedList<Carte> talon = new LinkedList <Carte>();
	private int valueIndex = 0, serialIndex = 1;
	private ViewPrinciple vp = null;

	/**
	 * Permet d'initialiser les cartes et de les ajouter dans la pioche.
	 */
	public void initCarte() {
		for(int i=0;i<8;i++){
			createCarte(nom[i]);
		}
		serialIndex = 1;
		Collections.shuffle(talon);
	}

	/**
	 * Permet de démarrer la manche qui correspond à la partie pour le mode rapide.
	 * Cela distribue les cartes et les cailloux.
	 */
	public void demarrerManche() {
		distribuerCarte();
		distribuerCailloux();
		ViewChamps.getVch().update();
	}

	/**
	 * La méthode dérouler permet de gérer l'entièreté de la partie avec les différent tours des joueurs, 
	 * les saisons et la fin de la partie.
	 */
	public void derouler() {
		demarrerManche();
		for(int i=0;i<4;i++){//4 saisons
			vp.updateS(Partie.getPartie().toString());
			for(int j=0;j<Partie.getPartie().getNbJoueur();j++){

				Partie.getPartie().getJoueurActuel().getStrategie().jouer();				
				setJoueurActuel();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(Partie.getCtrl() instanceof Graphic){
				vp.update("\n*******Fin De "+Partie.getPartie().toString()+"*******");
			}
			changeSaison();
		}
		fin();
	}

	/**
	 * Permet de changer de joueur actuel.
	 */
	public void setJoueurActuel() {

		Joueur j = Partie.getPartie().getJoueurActuel();
		LinkedList<Joueur> list = Partie.getPartie().getListeJoueur();

		int index = Partie.getPartie().getListeJoueur().indexOf(j);
		Partie.getPartie().setJoueurActuel(list.get((index+1)%list.size()));
	}

/**
 * Permet de passer à la saison suivante.
 */
	public void changeSaison() {

		int saison = (Partie.getPartie().getSaison()+1)%4;
		Partie.getPartie().setSaison(saison);
	}

	/**
	 * Permet de donner la force des cartes créées.
	 * @return force : tableau d'entiers.
	 */
	int[] forceValue(){
		int[] force = new int[4];
		force = value[valueIndex];
		valueIndex ++;
		return force;
	}

	/**
	 * Permet de créer un tableau comportant les cartes géant, engrais et farfadet.
	 * @return action : tableau comportant les 3 cartes.
	 */
	Carte[] create(){
		Carte[] action = new Carte[3];
		action[0] = new Geant(forceValue());
		action[1] = new Engrais(forceValue());
		action[2] = new Farfadet(forceValue());
		return action;
	}

	/**
	 * Permet de créer les cartes ingrédient.
	 * @param nom : String le nom de la carte
	 */
	void createCarte(String nom){
		for(int c=0;c<3;c++){
			talon.add(new CarteIngredient(nom,"g_"+serialIndex,create()));
			serialIndex ++;
		}
	}

	/**
	 * Permet de distribuer les cartes venant de la pioche au début de la partie à chaque joueur.
	 */
	void distribuerCarte(){
		Collections.shuffle(talon);
		for(int i=0; i<4; i++){
			Iterator <Joueur> it = Partie.getPartie().getListeJoueur().iterator();
			while(it.hasNext()){
				it.next().getMain().add(talon.poll());
			}
		}

		if(Partie.getCtrl() instanceof Graphic){
			ViewCarte.getVc().create();
		}
	}

	/**
	 * Permet de donner des cailloux à chaque joueur.
	 */
	void distribuerCailloux(){
		for(int i=0; i<2; i++){
			Iterator <Joueur> it = Partie.getPartie().getListeJoueur().iterator();
			while(it.hasNext()){
				it.next().getGraine().add(new Cailloux());
			}
		}
	}

	/**
	 * Permet de récupérer la pioche.
	 */
	public LinkedList<Carte> getTalon(){
		return talon;
	}

	/**
	 * Permet de terminer la partie en comptant les points et en déterminant le gagnant.
	 */
	public void fin(){
		int menhir = 0;
		Joueur gagnant = null;
		String str;

		Iterator<Joueur> it = Partie.getPartie().getListeJoueur().iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			if(j.getMenhir().size() >= menhir){
				menhir = j.getMenhir().size();
				gagnant = j;
			}
		}

		if(gagnant instanceof JoueurReel){
			str = "<html>CONGRATULATIONS !<br/>You are the winner!</html>";
			ViewPrinciple.getView().update(str);
		}
		else{
			str = "<html>WHAT A PITTY !<br/>You lose this one</html>";
			ViewPrinciple.getView().update(str);
		}

		ViewPrinciple.getView().fin(str);
	}


	/**
	 * Il n'y a pas de carte Allié dans la partie normale donc on return null quoi qu'il arrive.
	 * @return null
	 */
	public LinkedList<Carte> getAllie() {
		return null;
	}
}
