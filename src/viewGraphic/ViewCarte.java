package viewGraphic;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import carte.Carte;
import carte.TaupeGeant;
import controle.Graphic;
import joueur.Joueur;
import joueur.JoueurVirtuel;
import partie.Partie;

public class ViewCarte {
	private ViewPrinciple v = ViewPrinciple.getView();
	private JPanel pane = v.getPanel();
	private ArrayList<CButton> buttons = new ArrayList<CButton>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();//background of the cards
	private CButton abutton;
	private ViewChamps vch;
	
	private static ViewCarte vc;
	private ViewCarte(){
		
	}
	
	public static ViewCarte getVc(){
		if(vc == null){
			vc = new ViewCarte();
		}
		return vc;
	}
	
	public ViewChamps getVch(){
		return this.vch;
	}
	
	/**
	 * create cards button separately
	 * @param serial
	 */
	public void initButton(String serial, Carte c, int n) {
		//background of the cards,labels
		JLabel defaut = new JLabel();
		labels.add(defaut);
		defaut.setBounds(10*n+200*(n-1), 450, 200, 200);
		Image profile;
		try {
			profile = ImageIO.read(this.getClass().getResource("/geant.jpg"));
			defaut.setIcon(new ImageIcon(profile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defaut.setVisible(false);
		
		//buttons
		CButton button = new CButton(c);
		buttons.add(button);
		button.addActionListener(new SubWindow(button));

		try {
			Image icon = ImageIO.read(this.getClass().getResource("/"+serial+".jpg"));
			button.setIcon(new ImageIcon(icon));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		button.setBounds(10*n+200*(n-1), 450, 200, 200);
		pane.add(defaut);
		pane.add(button);
		button.setEnabled(false);
	}
	
	/**
	 * create buttons for hand cards
	 * @param p
	 */
	public void create(){
		ArrayList<Carte> temp = Partie.getPartie().getJoueurReel().getMain();
		for(int i = 0; i<4; i++){
			initButton(temp.get(i).getSerial(),temp.get(i),i+1);
		}
		vch = ViewChamps.getVch();
		vch.init();
		pane.repaint();
	}
	
	/**
	 * add new button for carte allie
	 */
	public void addA(Carte c){
		CButton button = new CButton(c);
		this.abutton = button;
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(c instanceof TaupeGeant){
					/**
					 * sub window to choose target name
					 */
					JFrame subWin = new JFrame("Choose target");
					
					JPanel p = new JPanel();
					Joueur target;
					LinkedList<Joueur> temp = Partie.getPartie().getListeJoueur();
					for(int i = 0; i<temp.size();i++){
						target = temp.get(i);
						if( target instanceof JoueurVirtuel){
							PButton name = new PButton(target);
							name.setText(target.getNom());
							
							name.addActionListener(new Names(name,target,subWin));
							name.setBackground(Color.WHITE);
							p.add(name);
						}
					}
					p.repaint();
					subWin.add(p);
					
					subWin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					subWin.setLocationRelativeTo(null);
					subWin.pack();
					subWin.setResizable(false);
					subWin.setVisible(true);
				}
				
				button.setVisible(false);
				Graphic.getGraphic().update();//restore parameters
				
				if(c instanceof TaupeGeant){
					TaupeGeant tg = (TaupeGeant) c;
					tg.effectuer(Partie.getPartie().getJoueurReel());
				}
				else{
					c.effectuer();
				}
				Graphic.getGraphic().setCarte(c);//notify the motor to continue(case chien)
			}
			
		});

		try {
			Image icon = ImageIO.read(this.getClass().getResource("/"+c.getSerial()+".jpg"));
			button.setIcon(new ImageIcon(icon));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		button.setBounds(850, 450, 200, 200);
		pane.add(button);
		button.setEnabled(false);
	}
	
	/**
	 * set ingredient card buttons enabled to disabled (or inverse)
	 */
	public void update(){
		Iterator<CButton> it = buttons.iterator();
		while(it.hasNext()){
			CButton x = it.next();
			Boolean b = !x.isEnabled();
			x.setEnabled(b);
		}
	}
	
	/**
	 * set carte allie enabled to disabled (or inverse)
	 */
	public void updateA(){
		Boolean b = !this.abutton.isEnabled();
		this.abutton.setEnabled(b);
	}
	
	/**
	 * set backgrounds invisible
	 */
	public void updateL(){
		Iterator<JLabel> it = this.labels.iterator();
		while(it.hasNext()){
			it.next().setVisible(false);
		}
	}
	
	/**
	 * inner class for the card buttons
	 * @author Dawei
	 *
	 */
	class SubWindow implements ActionListener, Runnable{
		
		private CButton button;
		private Carte c;
		
		SubWindow(CButton button){
			this.button = button;
			this.c = button.getC();
		}
		
		public void actionPerformed(ActionEvent e) {
			this.run();	
		}

		@Override
		public void run() {
			update();//disable the cards
			Graphic.getGraphic().setCarte(c);
			
			JFrame box = new JFrame("Choose Action");
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			
			JLabel text = new JLabel();
			text.setBounds(10, 5, 120, 20);
			
			JButton geant = new JButton("Geant");
			JButton engrais = new JButton("Engrais");
			JButton farfadet = new JButton("Farfadet");
			JButton exit = new JButton("exit");
			
			geant.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					update();//enable the cards
					text.setText("piocher des graines");
					Graphic.getGraphic().setInt(0);
					button.setVisible(false);
					int index = buttons.indexOf(button);
					labels.get(index).setVisible(true);
					box.setVisible(false);
				}
			});
			
			engrais.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					update();//enable the cards
					text.setText("engrais des graines");
					Graphic.getGraphic().setInt(1);
					button.setVisible(false);
					int index = buttons.indexOf(button);
					labels.get(index).setVisible(true);
					box.setVisible(false);
				}
			});
			
			farfadet.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					text.setText("voler des graines");
					Graphic.getGraphic().setInt(2);
					
					/**
					 * sub window to choose target name
					 */
					JFrame subWin = new JFrame("Choose target");
					
					JPanel p = new JPanel();
					
					Joueur target;
					LinkedList<Joueur> temp = Partie.getPartie().getListeJoueur();
					for(int i = 0; i<temp.size();i++){
						target = temp.get(i);
						if( target instanceof JoueurVirtuel){	
							PButton name = new PButton(target);
							name.setText(target.getNom());
							
							name.addActionListener(new Names(name,target,subWin));
							name.setBackground(Color.WHITE);
							p.add(name);
						}
					}
					
					subWin.add(p);
					
					subWin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					subWin.setLocationRelativeTo(null);
					subWin.pack();
					subWin.setResizable(false);
					subWin.setVisible(true);
					
					
					/**
					 * end action for button farfadet
					 */
					button.setVisible(false);
					update();//enable the cards
					int index = buttons.indexOf(button);
					labels.get(index).setVisible(true);
					box.setVisible(false);
//					box.dispose();
				}
			});
			
			exit.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					//button.setVisible(false);
					update();//enable the cards
					box.setVisible(false);
//					box.dispose();
					
				}
				
			});
			
			geant.setBackground(Color.CYAN);
			engrais.setBackground(Color.CYAN);
			farfadet.setBackground(Color.CYAN);
			exit.setBackground(Color.GRAY);
			
			geant.setBounds(20, 30, 90, 30);
			engrais.setBounds(20, 70, 90, 30);
			farfadet.setBounds(20, 110, 90, 30);
			exit.setBounds(20, 150, 90, 30);
			
			panel.add(text);
			panel.add(geant);
			panel.add(engrais);
			panel.add(farfadet);
			panel.add(exit);
			
			
			box.add(panel);
			box.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			box.setSize(150,240);
			box.setResizable(false);
			box.setLocationRelativeTo(null);
			box.setVisible(true);
			
		}
		
	}
	
	/**
	 * another inner class, to show the list of names
	 */
	class Names implements Runnable, ActionListener{
		PButton pb;
		Joueur j;
		JFrame f;
		public Names(PButton pb, Joueur j, JFrame f){
			this.pb = pb;
			this.j = j;
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.run();
		}

		@Override
		public void run() {
			Graphic.getGraphic().setJ(j);
			pb.setVisible(false);
			f.setVisible(false);
			f.dispose();
		}
		
	}

	public JButton getAbutton() {
		return abutton;
	}
	
	public static void reset(){
		vc = null;
	}
}
