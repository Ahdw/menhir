package joueur;

import exception.InputException;
import strategie.StrategieManuele;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe JoueurReel permet de représenter le joueur de la partie qui sera contrôlé par un humain.
 */

public class JoueurReel extends Joueur{
	
	/**
	 * Constructeur de la classe.
	 */
	public JoueurReel(){
		strategie = new StrategieManuele();
		try {
			setProfile();
		} catch (InputException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Permet de saisir les informations du joueur réel.
	 */
	public void setProfile() throws InputException {
		nom = ctrl.saisieNom();//string
		age = ctrl.saisieAge();//int
		sexe = ctrl.saisieSexe();//boolean
	}


}
