package modeDeJeu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import cailloux.Cailloux;
import carte.Carte;
import carte.Chien;
import carte.TaupeGeant;
import controle.Graphic;
import exception.InputException;
import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import partie.Partie;
import viewGraphic.ViewCarte;
import viewGraphic.ViewChamps;
import viewGraphic.ViewParametre;
import viewGraphic.ViewPoint;
import viewGraphic.ViewPrinciple;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe ModeAvance se base sur le mode simple. Elle permet de gérer le déroulement d'une partie 
 * avancée en gérant les différentes manches et le tour des joueurs.
 */

public class ModeAvance implements Mode {
	private ModeRapide base;
	private int valueIndex = 0;
	private int manche = 0;
	private LinkedList<Joueur> list;
	private static final int[][] value = {	{1,1,1,1},{0,2,2,0},{0,1,2,1},
		{2,0,2,0},{1,2,0,1},{0,1,3,0},
	}; 
	private LinkedList<Carte> allie = new LinkedList <Carte>();

	/**
	 * Constructeur de la classe.
	 */
	public ModeAvance(){
		base = new ModeRapide();
	}

	/**
	 * Méthode permettant d'initialiser les cartes alliés et de les mettre dans la pioche.
	 */
	public void initCarte() {
		base.initCarte();

		for(int i=0;i<3;i++){
			valueIndex = i;
			allie.add(new TaupeGeant("a_"+(valueIndex+1),forceValue()));
			valueIndex += 3;
			allie.add(new Chien("a_"+(valueIndex+1),forceValue()));
		}
		Collections.shuffle(allie);
	}

	/**
	 * Méthode permettant de définir la force des cartes en fonction des saisons au moment de leur création.
	 * @return force : tableau d'entier.
	 */
	int[] forceValue(){
		int[] force = new int[4];
		force = value[valueIndex];
		return force;
	}

	/**
	 * Méthode permettant de démarrer une manche en distribuant les cartes et en choisissant le premier joueur.
	 * Permet également de choisir entre piocher 2 graines ou une carte allié au début de la manche.
	 */
	public void demarrerManche() {
		ViewCarte.getVc().updateL();
		ViewPrinciple.getView().updateM("Manche "+(manche+1));

		// distribuer 4 cartes ingrediens
		base.distribuerCarte();

		//add ponit card
		ViewPoint.getVpoint();

		//choisis l'action
		ViewParametre.getVpr().pop();
		Iterator<Joueur> it = Partie.getPartie().getListeJoueur().iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			int choix = 0;
			int flag = 1;

			while(flag == 1){
				try {
					choix = j.getStrategie().choisis();
					flag = 0;
				} catch (InputException e) {
					System.out.println(e.getMessage());
				}
			}

			if(choix == 0){
				for(int i=0; i<2;i++){
					j.getGraine().add(new Cailloux());//soit piocher 2 cailloux
				}
			}
			else{
				Collections.shuffle(allie);
				j.getAllie().add(allie.poll());//soit piocher une carte allie 
				if(j instanceof JoueurReel){
					ViewCarte.getVc().addA(j.getAllie().get(0));
				}
			}

			Graphic.getGraphic().update();
		}

		//le premier joueur de chaque manche doit different
		list = Partie.getPartie().getListeJoueur();
		Partie.getPartie().setJoueurActuel(list.get(manche)); //different joueur commence le manche
		manche ++;

		ViewChamps.getVch().update();
	}

	/**
	 * Cette méthode permet de gérer toutes les étapes de la partie pour le mode avancé.
	 */
	public void derouler() {
		int index = Partie.getPartie().getNbJoueur();

		//index is the number of total manches
		for(int s=0;s<index;s++){
			demarrerManche();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			ViewPrinciple.getView().update(Partie.getPartie().getJoueurActuel().getNom()+" commence la manche");

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//4 saisons
			for(int i=0;i<4;i++){
				ViewPrinciple.getView().updateS(Partie.getPartie().toString());

				for(int j=0;j<Partie.getPartie().getNbJoueur();j++){					
					Partie.getPartie().getJoueurActuel().getStrategie().jouer();
					poserTG();
					setJoueurActuel();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				ViewPrinciple.getView().update("\n*******Fin De "+Partie.getPartie().toString()+"*******");
				changeSaison();
			}			
			finM();			
		}

		fin();
	}

	/**
	 * Permet de changer le joueur actuel.
	 */
	public void setJoueurActuel() {
		base.setJoueurActuel();								  
	}

	/**
	 * Permet de changer de saison.
	 */
	public void changeSaison() {
		base.changeSaison();
	}

	/**
	 * Permet de récupérer la pioche.
	 */
	public LinkedList<Carte> getTalon() {
		return base.getTalon();
	}

	/**
	 * Permet de récupérer la liste des cartes alliés.
	 */
	public LinkedList<Carte> getAllie() {
		return allie;
	}

	/**
	 * Permet de terminer la partie en comptant le nombre de points et en déterminant le gagnant.
	 */
	public void fin() {
		int point = 0;
		Joueur gagnant = null;
		String str;

		Iterator<Joueur> it = Partie.getPartie().getListeJoueur().iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			if(j.getPoint() >= point){
				point = j.getPoint();
				gagnant = j;
			}
		}

		if(gagnant.getPoint() == 0){
			str = "<html>no one wins<br/>Merci de Jouer!</html>";
			ViewPrinciple.getView().update(str);			
		}
		else{
			if(gagnant instanceof JoueurReel){
				str = "<html>CONGRATULATIONS !<br/>You are the winner!</html>";
				ViewPrinciple.getView().update(str);
			}
			else{
				str = "<html>WHAT A PITTY !<br/>You lose this one</html>";
				ViewPrinciple.getView().update(str);
			}
		}

		ViewPrinciple.getView().fin(str);
	}

	/**
	 * Permet de poser la carte TaupeGéante à n'importe quel moment de la partie.
	 */
	public void poserTG(){
		Iterator<Joueur> it = Partie.getPartie().getListeJoueur().iterator();
		while(it.hasNext()){
			Joueur j = it.next();

			if(j.getAllie().size() != 0 && j.getAllie().get(0) instanceof TaupeGeant){

				int flag = 0; // default is not
				TaupeGeant tg = (TaupeGeant) j.getAllie().get(0);

				if(j instanceof JoueurReel){
					if(Partie.getPartie().getJoueurActuel() instanceof JoueurVirtuel){
						ViewParametre.getVpr().popC("<html>Vous avez une Taupe Geant<br/>Veillez poser cette Carte?</html>");
						try {
							flag = Partie.getCtrl().saisieChoixAllie(); // wait for choosing
						} catch (InputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if(flag == 1){
							Graphic.getGraphic().update();//restore the parameters
							ViewCarte.getVc().getAbutton().setEnabled(true);
							ViewCarte.getVc().getAbutton().doClick();//effectuer the card

							//wait for target
							try {
								Graphic.getGraphic().saisieTarget();
							} catch (InputException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							ViewPrinciple.getView().update(j.getNom()+ " "+tg.getAnnonce());
							ViewChamps.getVch().update();
							allie.add(tg);
							Partie.getPartie().getJoueurReel().getAllie().clear();

							/**
							 * make sure you can see the annonce
							 */
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						Graphic.getGraphic().update(); // restore the attributes
					}
				}
				else{
					try {
						flag = j.getStrategie().choisis();
					} catch (InputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(flag == 1){
						tg.effectuer(j);
						ViewPrinciple.getView().update(j.getNom()+ " "+tg.getAnnonce());
						ViewChamps.getVch().update();
						allie.add(tg);
						j.getAllie().clear();

						/**
						 * make sure you can see the annonce
						 */
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	/**
	 * Méthode gérant la fin d'une manche dans le mode avancé. Cela permet de compter le nombre de points gangés pendant la manche.
	 */
	public void finM(){
		//draw back the cards allied and calculate the points
		Iterator<Joueur> it = Partie.getPartie().getListeJoueur().iterator(); 
		while(it.hasNext()){
			Joueur j = it.next();
			if(j.getAllie().size() != 0){
				allie.add(j.getAllie().get(0));
				j.getAllie().clear();
			}

			j.addPoint();
			j.getGraine().clear();
			j.getMenhir().clear();

			//refresh the view
			if(j instanceof JoueurReel){
				if(ViewCarte.getVc().getAbutton() != null){
					ViewCarte.getVc().getAbutton().setVisible(false);
				}
				ViewPoint.getVpoint().update(j.getPoint());
			}
		}
		ViewPoint.getVpoint().update();
	}

}
