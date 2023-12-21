package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import modele.AccesVueCapacite;
import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;
import modele.Cartes.Bleu.RevesBrises;
import modele.Cartes.Bleu.Transmigration;
import modele.Cartes.Mosaique.Mimetisme;
import modele.Strategie.JoueurReel;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Controleur.ControleurCapacite;

public class VueCapacite implements PropertyChangeListener, KeyListener {


	private JFrame frame;
	private ArrayList<Carte> carteAfficher;
	private ArrayList<String> infoAfficherCarte;
	private ArrayList<JLabel> lblCarte;
	private JButton btnHaut, btnBas;
	private JLabel lblNumCarte;
	private ArrayList<String> nomBouton;
	private Color couleur;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */

    /**
     * @wbp.parser.entryPoint
     */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		ArrayList<Carte> carteTest = new ArrayList<Carte>();
		carteTest.add(new Transmigration());
		carteTest.add(new Mimetisme());
		carteTest.add(new RevesBrises());
		ArrayList<String> nom= new ArrayList<String>();
		nom.add("test");
		nom.add("salut");
		VueCapacite vc = new VueCapacite(new Transmigration(), carteTest, nom , null, 2, new Color(150,0,0,150), new Joueur("flo", new JoueurReel(), PlateauJeu.getInstance(), Color.RED));
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public VueCapacite(AccesVueCapacite object, ArrayList<Carte> carteAfficher, ArrayList<String> nomBouton, ArrayList<String> infoAfficherCarte, int nbButtonTouche, Color couleur, Joueur joueurAttack) {

		if (!carteAfficher.isEmpty()) {
			object.ajouterUnSouscripteur("VueCapacite", this);
		this.carteAfficher = carteAfficher;
		this.infoAfficherCarte = infoAfficherCarte;
		this.nomBouton = nomBouton;
		this.couleur = couleur;
		System.out.println("debuuuuuuuuuuuuuuut " + joueurAttack);
		this.initialize();
		this.frame.setVisible(true);
		new ControleurCapacite(object, this, frame, btnHaut, btnBas, lblNumCarte, carteAfficher, nbButtonTouche, joueurAttack);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private synchronized void initialize() {
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 550);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.getContentPane().setBackground(couleur);
		
		lblNumCarte = new JLabel();
		lblNumCarte.setBounds(449, 11, 18, 20);
		frame.getContentPane().add(lblNumCarte);
		lblNumCarte.setText("1");
		
		lblCarte = new ArrayList<JLabel>();
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(101, 84, 198, 243);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(330, 11, 255, 316);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(617, 84, 198, 243);
		frame.getContentPane().add(lblCarte.getLast());
		
		this.setCarteMain(lblCarte);
		
		btnHaut = new JButton();
		btnHaut.setBounds(330, 385, 255, 53);
		frame.getContentPane().add(btnHaut);
		btnHaut.setText(this.nomBouton.getFirst());
		if (this.nomBouton.getFirst().compareTo("desable") == 0) {
			btnHaut.setVisible(false);
		}
		
		btnBas = new JButton();
		btnBas.setBounds(330, 449, 255, 53);
		frame.getContentPane().add(btnBas);
		if (this.nomBouton.size() == 1) {
			btnBas.setVisible(false);
		} else {
			btnBas.setText(this.nomBouton.getLast());
		}
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setCarteMain(ArrayList<JLabel> carteMain) {
		String path = "./src/ImageCarte/";
		int positionCarte = Integer.parseInt(this.lblNumCarte.getText()) - 2;
		Iterator<JLabel> it;
		it = carteMain.iterator();
		while(it.hasNext()) {
			JLabel label = it.next();
			if (positionCarte >=0 && (positionCarte < this.carteAfficher.size())){
				ImageIcon img= new ImageIcon(path + this.carteAfficher.get(positionCarte).getNomCarte() + ".png");
				Image image = img.getImage();
				image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				img = new ImageIcon(image);
				label.setIcon(img);
			} else {
				label.setIcon(null);
			}
			positionCarte++;
		}
		this.frame.repaint();
		
		
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			System.out.println("left");
			if(Integer.parseInt(this.lblNumCarte.getText()) < this.carteAfficher.size()) {
				this.lblNumCarte.setText(String.valueOf(Integer.parseInt(this.lblNumCarte.getText()) + 1));
				this.setCarteMain(this.lblCarte);
			}
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			System.out.println("right");
			if(Integer.parseInt(this.lblNumCarte.getText()) > 1 ) {
				this.lblNumCarte.setText(String.valueOf(Integer.parseInt(this.lblNumCarte.getText()) - 1));
				this.setCarteMain(this.lblCarte);
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Carte> getCarteAfficher() {
		return carteAfficher;
	}
	public void setCarteAfficher(ArrayList<Carte> carteAfficher) {
		this.carteAfficher = carteAfficher;
	}
	
	public void mettreAJour(Object carte) {
		this.carteAfficher.remove(carte);
		this.lblNumCarte.setText("1");
		this.setCarteMain(lblCarte);
		frame.setFocusable(true);
		frame.requestFocus();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof Carte) {
			switch(evt.getPropertyName()) {
			case "VueCapacite":
				this.mettreAJour(evt.getNewValue());
				break;
			}
		}
		
	}
	
}
