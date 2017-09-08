package viewGraphic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import partie.Partie;

public class ViewPrinciple {
	/**
	 * attributs
	 */
	public static ViewPrinciple view;
	
	JFrame frame = new JFrame();
	JPanel pane = new JPanel();
	JLabel annonce = new JLabel();
	JLabel manche = new JLabel();
	JLabel saison = new JLabel();
	
	/**
	 * constructor
	 */
	private ViewPrinciple(){
		init("Menir Simulator");
	}
	
	/**
	 * get instance
	 * @return
	 */
	public static ViewPrinciple getView(){
		if(view == null){
			view = new ViewPrinciple();
		}
		return view;
	}
	
	/**
	 * get the label annonce
	 */
	public JLabel getAnnonce(){
		return this.annonce;
	}
	
	/**
	 * main view
	 * @param title
	 */
	public void init(String title){
		frame.setTitle(title);
		pane.setLayout(null);
		
		frame.add(pane);
		frame.setSize(1080, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * start with the parameters panel
	 */
	public void start(){
		ViewParametre.getVpr().init();
	}
	
	/**
	 * get the panel
	 */
	public JPanel getPanel(){
		return this.pane;
	}
	
	/**
	 * update the annonce
	 */
	public void update(String str){
		annonce.setBounds(500, 370, 300, 50);
		this.pane.add(annonce);
		this.annonce.setVisible(true);
		this.annonce.setText(str);
	}
	
	/**
	 * update saison
	 */
	public void updateS(String str){
		saison.setBounds(500, 320, 300, 20);
		this.pane.add(saison);
		this.saison.setText(str);
	}
	
	/**
	 * update the annonce of manche
	 */
	public void updateM(String str){
		manche.setBounds(500, 270, 300, 20);
		this.pane.add(manche);
		this.manche.setVisible(true);
		this.manche.setText(str);
	}
	
	/**
	 * pop a window in the end
	 */
	public void fin(String str){
		JFrame sframe = new JFrame("The end");
		JPanel spanel = new JPanel();
		JLabel slabel = new JLabel(str,SwingConstants.CENTER);
		JButton retry = new JButton("Retry");
		JButton exit = new JButton("Exit");
		retry.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				Partie.reset();
				start();
				sframe.setVisible(false);
				sframe.dispose();
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		spanel.setLayout(null);
		slabel.setBounds(0, 0, 300, 100);
		retry.setBounds(40, 120, 100, 20);
		exit.setBounds(160, 120, 100, 20);
		retry.setBackground(Color.WHITE);
		exit.setBackground(Color.GRAY);
		
		spanel.add(slabel);
		spanel.add(retry);
		spanel.add(exit);
		sframe.add(spanel);
		sframe.setSize(300, 200);
		sframe.setResizable(false);
		sframe.setVisible(true);
		sframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		sframe.setLocationRelativeTo(null);
	}
}
