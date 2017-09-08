package controle;

import carte.Carte;
import exception.InputException;
import joueur.Joueur;

public interface Controle {
	public void update();
	
	public int saisieMode() throws InputException;
	public int saisieStrategie() throws InputException;
	public int saisieNbJoueur() throws InputException;
	
	public String saisieNom() throws InputException;
	public Boolean saisieSexe() throws InputException;
	public int saisieAge() throws InputException;
	
	public Carte saisieCarte() throws InputException;
	public int saisieAction() throws InputException;
	public Joueur saisieTarget() throws InputException;
	
	public int saisieChoix() throws InputException;
	public int saisieChoixAllie() throws InputException;
	
	//public void setAnnonce(String str);
	//public void setNotification(String str);
	//public void setInfo(String str);
}
