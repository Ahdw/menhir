package viewGraphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

import controle.Graphic;
import partie.Partie;

public class ViewParametre {
	private ViewPrinciple v = ViewPrinciple.getView();
	private JPanel pane = v.getPanel();
	private static ViewParametre vpr;
	private ArrayList<Component> components = new ArrayList<Component>();
	
	private ViewParametre(){
		
	}
	
	public static ViewParametre getVpr(){
		if(vpr == null){
			vpr = new ViewParametre();
		}
		return vpr;
	}
	
	/**
	 * init the panel to get the parameters
	 */
	public void init(){
		components.clear();
		JLabel title = new JLabel();
		Image icon;
		try {
			icon = ImageIO.read(this.getClass().getResource("/menhir.jpg"));
			title.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton rapide = new JButton("Rapide");
		JButton avance = new JButton("Avancé");
		JButton faible = new JButton("Faible");
		JButton forte = new JButton("Forte");
		
		rapide.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				rapide.setEnabled(false);
				avance.setEnabled(false);
				Graphic.getGraphic().setMode(0);
			}

			
		});
		
		avance.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				rapide.setEnabled(false);
				avance.setEnabled(false);
				Graphic.getGraphic().setMode(1);
				
			}
		});
		
		faible.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				faible.setEnabled(false);
				forte.setEnabled(false);
				Graphic.getGraphic().setStrategie(0);
				
			}

		});
		
		forte.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				faible.setEnabled(false);
				forte.setEnabled(false);
				Graphic.getGraphic().setStrategie(1);
				
			}

		});
		
		TextField name = new TextField("input your name");
		
		JButton valider1 = new JButton("Valider");
		valider1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				name.setEditable(false);
				valider1.setEnabled(false);
				Graphic.getGraphic().setNom(name.getText());
				
			}
		});
		
		TextField age = new TextField("input your age");
		
		
		JButton valider2 = new JButton("Valider");
		valider2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(age.getText());
				if(n > 7){
					age.setEditable(false);
					valider2.setEnabled(false);
					Graphic.getGraphic().setAge(n);
				}
				else{
					age.setText("minimum 8");
				}
				
			}
		});
		
		
		JButton male = new JButton("male");
		JButton female = new JButton("female");
		male.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				male.setEnabled(false);
				female.setEnabled(false);
				Graphic.getGraphic().setSexe(false);
				
			}
		});
		
		
		female.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				male.setEnabled(false);
				female.setEnabled(false);
				Graphic.getGraphic().setSexe(true);
			}
		});
		
		TextField nbj = new TextField("nombre de joueur(2-6)");
		JButton valider3 = new JButton("valider");
		valider3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(nbj.getText());
				if(n < 2 || n > 6){
					nbj.setText("must between 2 and 6");
				}
				else{
					nbj.setEditable(false);
					valider3.setEnabled(false);
					Graphic.getGraphic().setNbj(n);
				}
				
			}
		});
		
		JLabel warning = new JLabel();
		warning.setText("welcome to play!");
		
		JButton commencer = new JButton("Commencer");
		commencer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(age.isEditable() || nbj.isEditable()){
					warning.setText("check your parameters !");
				}
				else{
					pane.removeAll();
					pane.repaint();
					Partie.getPartie();
				}
			}
			
		});
		
		JButton regle = new JButton("Règles de jeu");
		regle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				regle.setEnabled(false);
				JFrame f = new JFrame("Règles de jeu");
				JPanel p = new JPanel();
				JLabel l = new JLabel();
				Image i;
				try {
					i = ImageIO.read(this.getClass().getResource("/regle.jpg"));
					l.setIcon(new ImageIcon(i));
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				
				p.add(l);
				f.add(p);
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f.addWindowListener(new WindowListener(){

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e) {
						f.setVisible(false);
						f.dispose();
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						regle.setEnabled(true);
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				f.setVisible(true);
				f.pack();
				f.setResizable(false);
				
			}
			
		});
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator<Component> it = components.iterator();
				while(it.hasNext()){
					it.next().setEnabled(true);
					nbj.setEditable(true);
					name.setEditable(true);
					age.setEditable(true);
				}
				
			}
			
		});
		
		regle.setBounds(880, 440, 150, 20);
		reset.setBounds(880, 480, 150, 20);
		
		title.setBounds(0, 0, 1080, 400);
		
		rapide.setBounds(150, 440, 100, 50);
		avance.setBounds(150, 500, 100, 50);
		faible.setBounds(280, 440, 100, 50);
		forte.setBounds(280, 500, 100, 50);	
		
		nbj.setBounds(400, 440, 200, 30);
		valider3.setBounds(400, 480, 100, 20);
		
		name.setBounds(610, 440, 100, 30);
		valider1.setBounds(610, 480, 100, 20);
		
		age.setBounds(610, 510, 100, 30);
		valider2.setBounds(610, 550, 100, 20);
		
		male.setBounds(610, 580, 100, 20);
		female.setBounds(610,610,100,20);
		
		commencer.setBounds(720, 440, 150, 20);
		warning.setBounds(720, 470, 150, 20);
		
		pane.add(title);
		
		components.add(rapide);
		components.add(avance);
		components.add(faible);
		components.add(forte);
		components.add(nbj);
		components.add(valider3);
		components.add(name);
		components.add(valider1);
		components.add(age);
		components.add(valider2);
		components.add(male);
		components.add(female);
		components.add(commencer);
		components.add(warning);
		components.add(regle);
		components.add(reset);
		
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){
			pane.add(it.next());
		}
		
		pane.repaint();
	}
	
	/**
	 * pop a frame to input the choice, piocher une carte ou 2 cailloux
	 */
	public void pop(){
		JFrame frame = new JFrame("saisir la choix");
		JPanel panel = new JPanel();
		JButton carte = new JButton("Piocher une carte aliée");
		JButton cailloux = new JButton("Piocher 2 graines");
		
		cailloux.setBackground(Color.WHITE);
		cailloux.addActionListener(new Action(){
			public void run(){
				frame.setVisible(false);
				Graphic.getGraphic().setInt(0);
			}
		});
		
		carte.setBackground(Color.WHITE);
		carte.addActionListener(new Action(){
			public void run(){
				frame.setVisible(false);
				Graphic.getGraphic().setInt(1);
			}
		});
		
		panel.setLayout(null);
		cailloux.setBounds(10, 10, 300, 30);
		carte.setBounds(10, 50, 300, 30);
		panel.add(cailloux);
		panel.add(carte);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(350, 150);
	}
	
	/**
	 * pop a frame to input the choice, whether to pose the dog card
	 */
	public void popC(String str){
		JFrame frame = new JFrame("saisir la choix");
		JPanel panel = new JPanel();
		JLabel annonce = new JLabel(str);
		JButton oui = new JButton("Oui");
		JButton non = new JButton("Non");
		
		oui.setBackground(Color.WHITE);
		oui.addActionListener(new Action(){
			public void run(){
				frame.setVisible(false);
				Graphic.getGraphic().setInt(1);
			}
		});
		
		non.setBackground(Color.WHITE);
		non.addActionListener(new Action(){
			public void run(){
				frame.setVisible(false);
				Graphic.getGraphic().setInt(0);
			}
		});
		
		panel.setLayout(null);
		annonce.setBounds(10,10,200,60);
		oui.setBounds(10, 80, 100, 20);
		non.setBounds(10, 110, 100, 20);
		panel.add(annonce);
		panel.add(oui);
		panel.add(non);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(250,180);
	}
	
	
	/**
	 * inner class implements runnable and ActionListener
	 */
	class Action implements Runnable, ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			this.run();			
		}
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void reset(){
		vpr = null;
	}
}
