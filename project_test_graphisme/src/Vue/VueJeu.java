package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import modele.Carte;
import modele.Joueur;
import modele.Partie;
import modele.Strategie.Aleatoire;
import modele.Strategie.JoueurReel;
import modele.Strategie.Strategie;

import javax.swing.JLabel;

import Controleur.ControleurJeu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;

public class VueJeu implements PropertyChangeListener, KeyListener{

	private JFrame frame;
	private ArrayList<JLabel> lblCarte;
	private JLabel lblInfoJoueur;
	private ArrayList<JLabel> lblCarteOeuvreAdverse;
	private ArrayList<JLabel> lblCarteOeuvre;
	private Partie partie;
	private JButton btnSauvegarder;
	private JButton btnPoint;
	private JButton btnPouvoir;
	private JButton btnFuture;
	private JButton btnRienFaire;
	private JLabel lblNumCarte;
	private JLabel lblNomJoueur;
	private Joueur joueurTour;
	private JLabel lblOeuvreAdverse;
	private JLabel lblOeuvre;
	private JLabel lblAffichePouvoir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueJeu window = new VueJeu("flo", "dyl", new JoueurReel());
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	// Si on reprend une partie
	public VueJeu() {
		this.partie = new Partie();
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./src/modele/sauvegarder2.txt"))){
			this.joueurTour = this.partie.getPlateauJeu().getJoueurs().get(bis.read());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lblCarte = new ArrayList<JLabel>();
		this.lblCarteOeuvre = new ArrayList<JLabel>();
		this.lblCarteOeuvreAdverse = new ArrayList<JLabel>();
		this.initialize();
		ControleurJeu cj = new ControleurJeu(btnSauvegarder, btnFuture, btnPouvoir, btnPoint, btnRienFaire, partie, lblNumCarte, lblNomJoueur);
	}
	// Si on commence une partie
	public VueJeu(String nomJoueur1, String nomJoueur2, Strategie strategieJoueur2) {
		this.initialiserCarteMain();
		this.lblCarteOeuvre = new ArrayList<JLabel>();
		this.lblCarteOeuvreAdverse = new ArrayList<JLabel>();
		this.lblCarte = new ArrayList<JLabel>();
		this.partie = new Partie(nomJoueur1, nomJoueur2, strategieJoueur2);
		this.joueurTour = this.partie.getPlateauJeu().getJoueurs().get(0);
		this.initialize();
		this.lblNomJoueur.setText(this.partie.getPlateauJeu().getJoueurs().get(this.recupererNumJoueur()).getNomJoueur());
		ControleurJeu cj = new ControleurJeu(btnSauvegarder, btnFuture, btnPouvoir, btnPoint, btnRienFaire, partie, lblNumCarte, lblNomJoueur);
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Iterator<Joueur> it = this.partie.getPlateauJeu().getJoueurs().iterator();
		while(it.hasNext()) {
			Joueur joueur = it.next();
			joueur.ajouterUnSouscripteur("joueurSuivant", this);
			joueur.ajouterUnSouscripteur("joueurContinue", this);
		}
		

		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 550);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(joueurTour.getCouleurText());
		
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.requestFocus();
		lblAffichePouvoir = new JLabel();
		lblAffichePouvoir.setBounds(190, 399, 613, 43);
		frame.getContentPane().add(lblAffichePouvoir);
		lblAffichePouvoir.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblOeuvreAdverse = new JLabel("Oeuvre Joueur Adverse");
		lblOeuvreAdverse.setBounds(769, 172, 165, 33);
		frame.getContentPane().add(lblOeuvreAdverse);
		if (this.partie.getPlateauJeu().getJoueurs().get((this.recupererNumJoueur() + 1) % 2).getOeuvres().getOeuvresCarte().isEmpty()){
			lblOeuvreAdverse.setVisible(false);
		}
		
		
		lblOeuvre = new JLabel("Ton oeuvre");
		lblOeuvre.setBounds(54, 172, 77, 33);
		frame.getContentPane().add(lblOeuvre);
		
		if (this.joueurTour.getOeuvres().getOeuvresCarte().isEmpty()) {
			lblOeuvre.setVisible(false);
		}
		
		lblNomJoueur = new JLabel();
		lblNomJoueur.setBounds(468, 11, 264, 36);
		frame.getContentPane().add(lblNomJoueur);
		lblNomJoueur.setFont(new Font(lblNomJoueur.getFont().getName(), lblNomJoueur.getFont().getStyle(), 30));
		this.setNomJoueur();
		
		btnRienFaire = new JButton("Ne pas jouer");
		btnRienFaire.setBounds(22, 453, 141, 49);
		frame.getContentPane().add(btnRienFaire);
		
		lblNumCarte = new JLabel();
		lblNumCarte.setBounds(490, 214, 18, 20);
		frame.getContentPane().add(lblNumCarte);
		lblNumCarte.setText("1");
		
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(102, 243, 100, 146);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(263, 243, 100, 146);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(410, 190, 168, 199);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(628, 243, 100, 146);
		frame.getContentPane().add(lblCarte.getLast());
		
		lblCarte.add(new JLabel());
		lblCarte.getLast().setBounds(789, 243, 100, 146);
		frame.getContentPane().add(lblCarte.getLast());
		if (joueurTour.getStrategie().vraiJoueur()) {
			this.setCarteMain(lblCarte, this.recupererNumJoueur());
		}
		
		
		lblInfoJoueur = new JLabel();
		lblInfoJoueur.setBounds(422, 58, 206, 114);
		frame.getContentPane().add(lblInfoJoueur);
		this.setInfoJoueur(lblInfoJoueur);
		
		lblCarteOeuvreAdverse.add(new JLabel());
		lblCarteOeuvreAdverse.getLast().setBounds(811, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvreAdverse.getLast());
		
		lblCarteOeuvreAdverse.add(new JLabel());
		lblCarteOeuvreAdverse.getLast().setBounds(764, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvreAdverse.getLast());
		
		lblCarteOeuvreAdverse.add(new JLabel());
		lblCarteOeuvreAdverse.getLast().setBounds(713, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvreAdverse.getLast());
		
		this.setCarteOeuvre(lblCarteOeuvreAdverse, (recupererNumJoueur() + 1) % 2);
		
		lblCarteOeuvre.add(new JLabel());
		lblCarteOeuvre.getLast().setBounds(32, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvre.getLast());
		
		lblCarteOeuvre.add(new JLabel());
		lblCarteOeuvre.getLast().setBounds(83, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvre.getLast());
		
		lblCarteOeuvre.add(new JLabel());
		lblCarteOeuvre.getLast().setBounds(130, 36, 100, 146);
		frame.getContentPane().add(lblCarteOeuvre.getLast());
		
		if (joueurTour.getStrategie().vraiJoueur()) {
			this.setCarteOeuvre(lblCarteOeuvre, recupererNumJoueur());
		}
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(811, 0, 175, 23);
		frame.getContentPane().add(btnSauvegarder);
		
		btnPoint = new JButton("Point");
		btnPoint.setBounds(190, 453, 190, 49);
		frame.getContentPane().add(btnPoint);
		
		btnPouvoir = new JButton("Pouvoir");
		btnPouvoir.setBounds(422, 453, 190, 49);
		frame.getContentPane().add(btnPouvoir);
		
		btnFuture = new JButton("Vie Future");
		btnFuture.setBounds(648, 453, 190, 49);
		frame.getContentPane().add(btnFuture);
		
		if (!this.joueurTour.getStrategie().vraiJoueur()) {
			this.joueurTour.getStrategie().strategie(joueurTour);
		}
		
	}
	
	public ArrayList<JLabel> initialiserCarteMain(){
		this.lblCarte = new ArrayList<JLabel>();
		return lblCarte;
	}
	
	public void setPouvoir(Carte c) {
		this.lblAffichePouvoir.setText("<html>" + c.getNomPouvoir().replace("\n", "<br>") + "</html>");
	}
	
	public void setCarteMain(ArrayList<JLabel> carteMain, int numJ) {
		String path = "./src/ImageCarte/";
		int positionCarte = Integer.parseInt(this.lblNumCarte.getText()) - 3;
		Iterator<JLabel> it;
		it = carteMain.iterator();
		while(it.hasNext()) {
			JLabel label = it.next();
			if (positionCarte >=0 && (positionCarte < this.partie.getPlateauJeu().getJoueurs().get(numJ).getMain().getMainCarte().size())){
				ImageIcon img= new ImageIcon(path + this.partie.getPlateauJeu().getJoueurs().get(numJ).getMain().getMainCarte().get(positionCarte).getNomCarte() + ".png");
				Image image = img.getImage();
				image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				img = new ImageIcon(image);
				label.setIcon(img);
				if  (carteMain.get(2) == label) {
					this.setPouvoir(this.partie.getPlateauJeu().getJoueurs().get(numJ).getMain().getMainCarte().get(positionCarte));
				}
			} else {
				label.setIcon(null);
			}
			positionCarte++;
		}
		this.frame.repaint();
		
		
	}
	
	public void setCarteOeuvre(ArrayList<JLabel> oeuvre, int numJ) {
		String path = "./src/ImageCarte/";
		int positionCarte = 0;
		Iterator<JLabel> it = oeuvre.iterator();
		while(it.hasNext()) {
			JLabel label = it.next();
			if (positionCarte < this.partie.getPlateauJeu().getJoueurs().get(numJ).getOeuvres().getOeuvresCarte().size()) {
				ImageIcon img= new ImageIcon(path + this.partie.getPlateauJeu().getJoueurs().get(numJ).getOeuvres().getOeuvresCarte().get(positionCarte).getNomCarte() + ".png");
				Image image = img.getImage();
				image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				img = new ImageIcon(image);
				label.setIcon(img);
				} else {
				label.setIcon(null);
			}
			positionCarte++;
		}
	}
	
	public void setInfoJoueur(JLabel infoJoueur) {
		String msg = "";
		if (!this.partie.getPlateauJeu().getJoueurs().get(this.partie.getPlateauJeu().getJoueurs().indexOf(joueurTour)).getStrategie().vraiJoueur()){
			msg = this.partie.getPlateauJeu().getJoueurs().get(this.partie.getPlateauJeu().getJoueurs().indexOf(joueurTour)).getStrategie().getMsg();
		}
		infoJoueur.setText( "<html>"
				+ msg
				+ "<br>Anneaux Karmique: " + this.joueurTour.getAnneauxKarmique().getAnneauxKarmiques()
				+ "<br>Nombre Carte dans la vie Future: " + this.joueurTour.getVieFuture().getVieFutureCarte().size()
				+ "<br>Nombre Carte dans la pile: " + this.joueurTour.getPile().getPileCartes().size()
				+ "<br>Echelle Karmique: " + this.joueurTour.getEchelleKarmique().getEchelleVieJoueur()
				+ "</html>"
				);
		frame.repaint();
	}
	
	public void setOeuvreVisible() {
		if (this.partie.getPlateauJeu().getJoueurs().get((this.recupererNumJoueur() + 1) % 2).getOeuvres().getOeuvresCarte().isEmpty()){
			lblOeuvreAdverse.setVisible(false);
		} else {
			lblOeuvreAdverse.setVisible(true);
		}
			
		if (this.joueurTour.getOeuvres().getOeuvresCarte().isEmpty()) {
			lblOeuvre.setVisible(false);
		} else {
			lblOeuvre.setVisible(true);
		}
		frame.repaint();
		
	}
	
	public void setNomJoueur() {
		this.lblNomJoueur.setText(joueurTour.getNomJoueur());
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public int recupererNumJoueur() {
		return this.partie.getPlateauJeu().getJoueurs().indexOf(this.joueurTour);
	}
	
	public void mettreAJourVue() {
		this.joueurTour.tourJoueur();
		this.gagner(joueurTour);
		if (joueurTour.getStrategie().vraiJoueur()) {
			if (this.joueurTour.getPile().getPileCartes().isEmpty()) {
			this.btnRienFaire.setVisible(false);
		} else {
			this.btnRienFaire.setVisible(true);
		}
		if (this.joueurTour.getMain().getMainCarte().isEmpty()) {
			this.btnFuture.setVisible(false);
			this.btnPoint.setVisible(false);
			this.btnPouvoir.setVisible(false);
		} else {
			this.btnFuture.setVisible(true);
			this.btnPoint.setVisible(true);
			this.btnPouvoir.setVisible(true);
		}
		frame.setFocusable(true);
		frame.getContentPane().setBackground(joueurTour.getCouleurText());
		this.lblNumCarte.setText("1");
		this.setCarteOeuvre(this.lblCarteOeuvre, recupererNumJoueur());
		this.setCarteOeuvre(this.lblCarteOeuvreAdverse,(recupererNumJoueur() + 1)%2);
		this.setCarteMain(this.lblCarte, this.recupererNumJoueur());
		this.setInfoJoueur(lblInfoJoueur);
		this.setNomJoueur();
		this.setOeuvreVisible();
		frame.requestFocus();
		frame.repaint();
		} 
		else {
			this.setInfoJoueur(lblInfoJoueur);
			joueurTour.getStrategie().strategie(joueurTour);
		}
		
	}
		

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof Joueur) {
			switch(evt.getPropertyName()) {
			case "joueurSuivant":
				this.joueurTour = this.partie.getPlateauJeu().getJoueurs().get((this.recupererNumJoueur() + 1) %2);
				this.mettreAJourVue();
				break;
			case "joueurContinue":
				this.mettreAJourVue();
				break;
			}
		}
		
	}
	
	public void gagner(Joueur j) {
		if (!j.aGagner()) {
			frame.dispose();
			VueGagner vg = new VueGagner(j);
			vg.getFrame().setVisible(true);
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			if(Integer.parseInt(this.lblNumCarte.getText()) < this.joueurTour.getMain().getMainCarte().size()) {
				this.lblNumCarte.setText(String.valueOf(Integer.parseInt(this.lblNumCarte.getText()) + 1));
				this.setCarteMain(this.lblCarte, this.recupererNumJoueur());
			}
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			if(Integer.parseInt(this.lblNumCarte.getText()) > 1 ) {
				this.lblNumCarte.setText(String.valueOf(Integer.parseInt(this.lblNumCarte.getText()) - 1));
				this.setCarteMain(this.lblCarte, this.recupererNumJoueur());
			}
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
