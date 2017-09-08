package viewGraphic;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

import joueur.Joueur;
import partie.Partie;

public class ViewChamps {
	private ViewPrinciple v = ViewPrinciple.getView();
	private JPanel pane = v.getPanel();
	private int nbj = Partie.getPartie().getNbJoueur();
	private ArrayList<PLabel> graines = new ArrayList<PLabel>();
	private ArrayList<PLabel> menhirs = new ArrayList<PLabel>();
	private ArrayList<Joueur> joueurVirtuel = Partie.getPartie().getListeJoueurVirtuel();
	
	private static ViewChamps vch;
	private ViewChamps(){
		init();
	}
	
	public static ViewChamps getVch(){
		if(vch == null){
			vch = new ViewChamps();
		}
		return vch;
	}
	
	public void init(){
		Joueur jr = Partie.getPartie().getJoueurReel();
		PLabel champs = new PLabel(jr);
		JLabel info = new JLabel(jr.getNom()+" "+jr.getAge(), SwingConstants.CENTER);
		JLabel graine = new JLabel();
		JLabel menhir = new JLabel();
		PLabel ng = new PLabel(jr);
		PLabel nm = new PLabel(jr);
		champs.setBounds(10, 240, 200, 200);
		info.setBounds(20, 260, 150, 20);
		graine.setBounds(20, 290, 40, 60);
		ng.setBounds(100, 240+80, 70, 20);
		menhir.setBounds(20, 360, 40, 60);
		nm.setBounds(100, 240+150, 70, 20);
		Image icon;
		try {
			icon = ImageIO.read(this.getClass().getResource("/champs.jpg"));
			champs.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			icon = ImageIO.read(this.getClass().getResource("/graine.png"));
			graine.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			icon = ImageIO.read(this.getClass().getResource("/menhir.png"));
			menhir.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pane.add(info);
		pane.add(graine);
		pane.add(menhir);
		pane.add(nm);
		pane.add(ng);
		pane.add(champs);
		graines.add(ng);
		menhirs.add(nm);
//		champs.setText("<html><div style='text-align: center;'><font color=black size=5>"+Partie.getPartie().getJoueurReel().getNom()+" "+Partie.getPartie().getJoueurReel().getAge()+"<br/>Graines:"+Partie.getPartie().getJoueurReel().getGraine().size()+"<br/>Menhir:"+Partie.getPartie().getJoueurReel().getMenhir().size()+"</font></html>");
//		champs.setIconTextGap(-200);
		
		for(int i=0; i<5;i++){
			if(i<nbj-1){
				initChamps(i,this.joueurVirtuel.get(i));
			}
			else{
				JLabel defaut = new JLabel();
				defaut.setBounds(10*(i+1)+200*i, 10, 200, 200);
				Image profile;
				try {
					profile = ImageIO.read(this.getClass().getResource("/farfadet.jpg"));
					defaut.setIcon(new ImageIcon(profile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pane.add(defaut);
				pane.repaint();
			}
		}
	}
	
	/**
	 * create champs for one virtual player
	 */
	void initChamps(int n, Joueur j){
		PLabel champs = new PLabel(j);
		JLabel info = new JLabel(j.getNom()+" "+j.getAge(), SwingConstants.CENTER);
		JLabel graine = new JLabel();
		JLabel menhir = new JLabel();
		PLabel ng = new PLabel(j);
		PLabel nm = new PLabel(j);
		champs.setBounds(10*(n+1)+200*n, 10, 200, 200);
		info.setBounds(10*(n+2)+200*n, 30, 150, 20);
		graine.setBounds(10*(n+2)+200*n, 60, 40, 60);
		menhir.setBounds(10*(n+2)+200*n, 130, 40, 60);
		ng.setBounds(100+10*(n+1)+200*n, 10+80, 70, 20);
		nm.setBounds(100+10*(n+1)+200*n, 10+150, 70, 20);
		Image icon;
		try {
			icon = ImageIO.read(this.getClass().getResource("/champs.jpg"));
			champs.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			icon = ImageIO.read(this.getClass().getResource("/graine.png"));
			graine.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			icon = ImageIO.read(this.getClass().getResource("/menhir.png"));
			menhir.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		champs.setText("<html><div style='text-align: center;'><font color=black size=5>"+j.getNom()+" "+j.getAge()+"<br/>Graines:"+j.getGraine().size()+"<br/>Menhir:"+j.getMenhir().size()+"</font></html>");
//		champs.setIconTextGap(-200);
		pane.add(info);
		pane.add(graine);
		pane.add(menhir);
		pane.add(nm);
		pane.add(ng);
		pane.add(champs);
		graines.add(ng);
		menhirs.add(nm);
	}
	
	/**
	 * update the numbers of all the players
	 */
	public void update(){
		LinkedList<Joueur> joueur = Partie.getPartie().getListeJoueur();
		Iterator<Joueur> it = joueur.iterator();
		while(it.hasNext()){
			Joueur j = it.next();			
			Iterator<PLabel> itP = graines.iterator();
			while(itP.hasNext()){
				PLabel p = itP.next();
				Joueur jj = p.getJ();
				if(jj.getNom() == j.getNom()){
					p.setText("x "+j.getGraine().size());
				}
			}
			
			Iterator<PLabel> itP1 = menhirs.iterator();
			while(itP1.hasNext()){
				PLabel p1 = itP1.next();
				Joueur jj1 = p1.getJ();
				if(jj1.getNom() == j.getNom()){
					p1.setText("x "+j.getMenhir().size());
				}
			}
			
		}
	}
	
	public static void reset(){
		vch = null;
	}
}
