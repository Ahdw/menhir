package cailloux;

/**
 * @author Raphael CADAPEAUD et Dawei HUANG
 * 
 * La classe cailloux permet de représenter les graines ou les menhirs dans une partie du jeu du menhir.
 *
 */

public class Cailloux {
	boolean estMenhir = false;
	
	/**
	 * La méthode grandir permet simplement de changer une graine en menhir.
	 */
	public void gandir(){
		estMenhir = true;
	}
}
