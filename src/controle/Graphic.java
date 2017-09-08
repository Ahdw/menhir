package controle;

import carte.Carte;
import exception.InputException;
import joueur.Joueur;

public class Graphic implements Controle {
	public static Graphic ctrlG;
	
	private Carte c = null;
	private int chiffre = 20855144;
	
	private Joueur j = null;
	
	private String nom = null;
	private Boolean sexe = null;
	private int age = 0;
	
	private int mode = 0;
	private int strategie = 0;
	private int nbj = 0;
	
	
	/**
	 * Constructeur
	 */
	private Graphic(){
		
	}
	
	public static Graphic getGraphic(){
		if(ctrlG == null){
			ctrlG = new Graphic();
		}
		
		return ctrlG;
	}
	
	public synchronized void setCarte(Carte c){
		this.c = c;
		notifyAll();
	}
	
	public synchronized void setInt(int n){
		this.chiffre = n;
		notifyAll();
	}
	
	public synchronized void setJ(Joueur j){
		this.j = j;
		notifyAll();
	}
	
	public void setMode(int n){
		this.mode = n;
	}
	
	public void setStrategie(int n){
		this.strategie = n;
	}
	
	public void setNbj(int n){
		this.nbj = n;
	}
	
	
	
	public void setNom(String s){
		this.nom = s;
	}
	
	public void setSexe(Boolean b){
		this.sexe = b;
	}
	
	public void setAge(int n){
		this.age = n;
	}
	
	@Override
	public int saisieMode() throws InputException {
		return this.mode;
	}

	@Override
	public int saisieStrategie() throws InputException {
		return this.strategie;
	}

	@Override
	public int saisieNbJoueur() throws InputException {
		return this.nbj;
	}

	@Override
	public String saisieNom() throws InputException {
		return this.nom;
	}

	@Override
	public Boolean saisieSexe() throws InputException {
		return this.sexe;
	}

	@Override
	public int saisieAge() throws InputException {
		return this.age;
	}
	
	@Override
	public synchronized Carte saisieCarte() throws InputException {
			while(this.c == null){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return c;
	}

	@Override
	public synchronized int saisieAction() throws InputException {
			while(this.chiffre == 20855144){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return this.chiffre;
	}

	@Override
	public synchronized Joueur saisieTarget() throws InputException {
		while(this.j == null){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.j;
	}

	@Override
	public synchronized int saisieChoix() throws InputException {
		while(this.chiffre == 20855144){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return this.chiffre;
	}

	@Override
	public synchronized int saisieChoixAllie() throws InputException {
		while(this.chiffre == 20855144){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return this.chiffre;
	}

	@Override
	public void update() {
		this.c = null;
		this.chiffre = 20855144;
		this.j = null;
	}
	
	public static void reset(){
		ctrlG = null;
	}

}
