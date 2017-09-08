package viewGraphic;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

import joueur.Joueur;
import partie.Partie;

public class ViewPoint {
	private JPanel panel = ViewPrinciple.getView().getPanel();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JLabel> points = new ArrayList<JLabel>();
	private ArrayList<Joueur> jv = Partie.getPartie().getListeJoueurVirtuel();
	
	private static ViewPoint vpoint;
	private ViewPoint(){
		init();
	}
	public static ViewPoint getVpoint(){
		if(vpoint == null){
			vpoint = new ViewPoint();
		}
		return vpoint;
	}
	
	/**
	 * create the view on the main panel
	 */
	public void init(){
		//add bulletin
		this.paintB();
		
		//add carte de comptage
		JLabel point = new JLabel();
		point.setBounds(220, 240, 200, 200);
		Image icon;
		try {
			icon = ImageIO.read(this.getClass().getResource("/point.jpg"));
			point.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//how to show the points
		for(int y=0; y<5; y++){
			for(int x=0;x<5;x++){
				JLabel label = new JLabel();
				
				label.setOpaque(true);
				label.setBackground(Color.YELLOW);
				
				label.setVisible(false);
				
				label.setBounds(220+40*x, 240+40*y, 40, 40);
				panel.add(label);
				labels.add(label);
			}
		}
		panel.add(point);
		panel.repaint();
	}
	
	/**
	 * modify the outlook of the card
	 */
	public void update(int n){
		
		Iterator<JLabel> it = labels.iterator();
		while(it.hasNext()){
			it.next().setVisible(false);
		}
		
		if(n>0){
			labels.get(n-1).setVisible(true);
		}
	}
	
	/**
	 * create the bulletin
	 */
	private void paintB(){
		JLabel bulletin = new JLabel();
		Image icon;
		try {
			icon = ImageIO.read(this.getClass().getResource("/bulletin.jpg"));
			bulletin.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bulletin.setBounds(850, 240, 200, 200);
		bulletin.setIconTextGap(-200);
		
		
		for(int i=0; i<jv.size();i++){
			JLabel label = new JLabel();
			label.setBounds(890, 240+i*40, 120, 40);
			this.points.add(label);
			panel.add(label);
		}
		
		panel.add(bulletin);
	}
	
	/**
	 * update the bulletin
	 */
	public void update(){
		for(int i=0; i<jv.size();i++){
			points.get(i).setForeground(Color.YELLOW);
			points.get(i).setText(jv.get(i).getNom()+" points: "+jv.get(i).getPoint());
		}
		
		panel.repaint();
	}
	
	public static void reset(){
		vpoint = null;
	}
}
