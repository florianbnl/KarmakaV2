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

/**
 * Classe VueCapacite implémente PropertyChangeListener, KeyListener
 */
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
	 * Constructeur classe VueCapacite
	 * Créer la vue
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
	 * Méthode initialize
	 * Initialise le contenue de la fenêtre
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
	 * Méthode setCarteMain
	 * Permet d'afficher les cartes dans la fenêtre
	 * 
	 * @param carteMain
	 * type ArrayList<JLabel>: les contenues qui affichent les cartes
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
	 * Méthode getFrame
	 * getter de l'attribut frame
	 * @wbp.parser.entryPoint
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Méthode setFrame
	 * setter de l'attribut frame
	 * 
	 * @param frame
	 * type JFrame:
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Méthode keyTyped
	 * Permet de réaliser une action lorsque une touche du clavier est touchée
	 * 
	 * @param e
	 * type KeyEvent: connaitre la touche appuyé sur le clavier
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode keyPressed
	 * Permet de réaliser une action lorsque une touche du clavier est pressé
	 * 
	 * @param e
	 * type KeyEvent: connaitre la touche appuyé sur le clavier
	 */
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
	
	/**
	 * Méthode keyReleased
	 * Permet de réaliser une action lorsque une touche du clavier est relachée
	 * 
	 * @param e
	 * type KeyEvent: connaitre la touche appuyé sur le clavier
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Méthode getCarteAfficher
	 * getter de l'attribut carteAfficher
	 */
	public ArrayList<Carte> getCarteAfficher() {
		return carteAfficher;
	}
	
	/**
	 * Méthode setCarteAfficher
	 * setter de l'attribut carteAfficher
	 * 
	 * @param carteAfficher
	 * type ArrayList<Carte>:
	 */
	public void setCarteAfficher(ArrayList<Carte> carteAfficher) {
		this.carteAfficher = carteAfficher;
	}
	
	/**
	 * Méthode mettreAJour
	 * Permet de mettre à jour la vue
	 * 
	 * @param  carte
	 * type Object: la carte à enlever lors de la mise à jour
	 */
	public void mettreAJour(Object carte) {
		this.carteAfficher.remove(carte);
		this.lblNumCarte.setText("1");
		this.setCarteMain(lblCarte);
		frame.setFocusable(true);
		frame.requestFocus();
	}
	
	/**
	 * Méthode propertyChange
	 * Permet de réaliser une action lorsque l'objet observé nous envoie un changement d'état
	 * 
	 * @param evt
	 * type PropertyChangeEvent: l'évènement qui est diffuser
	 * 
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
