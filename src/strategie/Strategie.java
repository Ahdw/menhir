package strategie;

import carte.Carte;
import carte.Chien;
import controle.Controle;
import controle.Graphic;
import exception.InputException;
import joueur.Joueur;
import joueur.JoueurReel;
import modeDeJeu.Mode;
import modeDeJeu.ModeAvance;
import modeDeJeu.ModeRapide;
import partie.Partie;
import viewGraphic.ViewCarte;
import viewGraphic.ViewChamps;
import viewGraphic.ViewParametre;
import viewGraphic.ViewPrinciple;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe Strategie est une classe abstraite qui permet de définir les méthodes des classes qui en héritent.
 * Selon la stratégie, le comportement du joueur ne sera pas le même.
 * 
 * @see StrategieFaible
 * @see StrategieForte
 * @see StrategieManuele
 *
 */

public abstract class Strategie implements Runnable{
	protected Controle ctrl = Partie.getCtrl();
	protected Carte c;
	protected ViewPrinciple vp = ViewPrinciple.getView();

	/**
	 * Permet de choisir un joueur sur lequel faire une action.
	 * @return Joueur choisis.
	 * @throws InputException
	 */
	public abstract Joueur choisisJoueur() throws InputException;

	/**
	 * Permet de choisir une carte à jouer.
	 * @return Carte choisie.
	 * @throws InputException
	 */
	public abstract Carte choisisCarte() throws InputException;

	/**
	 * Permet de choisir une action à faire selon la carte choisie.
	 * @return int : action choisie
	 * @throws InputException
	 */
	public abstract int choisisAction() throws InputException;

	/**
	 * Permet de choisir au début de chaque manche dans le mode avancé si on pioche une carte allié ou deux graines.
	 * @return int : choix 
	 * @throws InputException
	 */
	public abstract int choisis() throws InputException;

	/**
	 * Permet de choisir si on veut poser la carte allié ou pas.
	 * @return int : choix
	 * @throws InputException
	 */
	public abstract int choisisJouerAllie() throws InputException;

	/**
	 * Permet de vérifier les choix.
	 * @param intent : int
	 * @param j : Joueur
	 */
	public void confirm(int intent, Joueur j){
		Mode mode = Partie.getPartie().getMode();
		if(mode instanceof ModeAvance){											//mode avance
			if(j.getAllie().size()!=0 && j.getAllie().get(0) instanceof Chien){ //carte allie existe et elle est carte chien
				Chien chien = (Chien) j.getAllie().get(0);						//lui reference comme chien
				Joueur joueur = Partie.getPartie().getJoueurActuel();

				String str = "<html>"+joueur.getNom() + " veut voler vos graines<br/>mais vous avez un chien de garde<br/>Veuillez poser cette carte?</html>";
				if(j instanceof JoueurReel){
					ViewParametre.getVpr().popC(str);
				}

				int flag = 1;
				int choix = 0;

				//get choix
				while(flag == 1){
					try {
						choix = choisisJouerAllie();
						flag = 0;
					} catch (InputException e) {
						System.out.println(e.getMessage());
					}
				}

				if(choix == 1){
					if(j instanceof JoueurReel){
						ViewCarte.getVc().getAbutton().setVisible(false);
					}
					chien.effectuer(intent, j);
					this.vp.update(chien.getAnnonce());
					j.getAllie().clear();
					Partie.getPartie().getMode().getAllie().add(chien);
				}
			}
		}
	}

	/**
	 * Permet de lancer le processus de jeu d'une carte.
	 */
	public void jouer() {
		this.run();
		ViewChamps.getVch().update();
	}


	/**
	 * Permet de choisir une carte allié ou une carte ingrédient (en fonction du mode de jeu).
	 */
	public void run(){
		if(Partie.getPartie().getMode() instanceof ModeRapide){
			jouerIngredient();
		}
		else {
			jouerIngredient();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jouerAllie();
		}
		ctrl.update();
	}

	/**
	 * Permet de jouer une carte ingrédient.
	 */
	void jouerIngredient(){
		//enable the buttons
		if(Partie.getPartie().getJoueurActuel() instanceof JoueurReel){
			ViewCarte.getVc().update();
		}

		int flag = 1;
		while(flag == 1){
			try {
				c = choisisCarte();
				c.effectuer();
				flag = 0;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}

		Partie.getPartie().getMode().getTalon().add(c);
		Partie.getPartie().getJoueurActuel().getMain().remove(c);
		//System.out.println(this.toString());
		vp.update(Partie.getPartie().getJoueurActuel().getNom()+" "+c.getAnnonce());

		//disable the buttons
		if(Partie.getPartie().getJoueurActuel() instanceof JoueurReel){
			ViewCarte.getVc().update();
		}
	}

	/**
	 * Permet de jouer une carte allié.
	 */
	void jouerAllie(){
		if(Partie.getPartie().getJoueurActuel().getAllie().size()>0){
			int choix = 0;
			int flag = 1;

			if(Partie.getPartie().getJoueurActuel() instanceof JoueurReel){
				ViewParametre.getVpr().popC("Veuillez poser cette carte alliÃ©?");
			}

			while(flag == 1){
				try {
					Graphic.getGraphic().update();
					choix = choisisJouerAllie();
					flag = 0;
				} catch (InputException e) {
					System.out.println(e.getMessage());
				}
			}

			if(choix == 1){
				c = Partie.getPartie().getJoueurActuel().getAllie().get(0);
				if(Partie.getPartie().getJoueurActuel() instanceof JoueurReel){
					ViewCarte.getVc().updateA();//enable carte allie
					ViewCarte.getVc().getAbutton().doClick();

					Partie.getPartie().getMode().getAllie().add(c);
					Partie.getPartie().getJoueurActuel().getAllie().clear();
					this.vp.update(Partie.getPartie().getJoueurActuel().getNom()+" "+c.getAnnonce());

					Graphic.getGraphic().update();//restore the attributes
				}
				else{
					c =Partie.getPartie().getJoueurActuel().getAllie().get(0);
					c.effectuer();
					Partie.getPartie().getMode().getAllie().add(c);
					Partie.getPartie().getJoueurActuel().getAllie().clear();
					this.vp.update(Partie.getPartie().getJoueurActuel().getNom()+" "+c.getAnnonce());
				}
			}
		}

		Graphic.getGraphic().update();
	}
	
	public String toString(){
		String str = "\n"+Partie.getPartie().getJoueurActuel().getNom()+":\n"+"Graine: "+Partie.getPartie().getJoueurActuel().getGraine().size()+"\nMenhir: "+Partie.getPartie().getJoueurActuel().getMenhir().size();
		str += "\n"+c.getAnnonce();
		return str;
	}
}
