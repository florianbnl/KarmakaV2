package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modele.Carte;
import modele.Joueur;
import modele.Partie;
import modele.PlateauJeu;

/**
 * Classe ControleurJeu
 * 
 * Controleur de la classe VueJeu
 */
public class ControleurJeu  {
	
	private JButton btnSauvegarder, btnFuture, btnPouvoir, btnPoint;
	private Partie partie;
	private JLabel lblNomJoueur;
	private JLabel lblNumCarte;
	private JButton btnRienFaire;
	
	/**
	 * Constructeur Classe ControleurJeu
	 * 
	 * @param btnSauvegarder
	 * type JButton: récupérer le bouton btnSauvegarder
	 * 
	 * @param btnFuture
	 * type JButton: récupérer le bouton btnFuture
	 * 
	 * @param btnPouvoir
	 * type JButton: récupérer le bouton btnPouvoir
	 * 
	 * @param btnPoint
	 * type JButton : récupérer le bouton btnPoint
	 * 
	 * @param btnRienFaire
	 * type JButton: récupérer le bouton btnRienFaire
	 * 
	 * @param partie
	 * type Partie: récupérer l'objet partie
	 * 
	 * @param numCarte
	 * type JLabel: récupérer le numéro de la carte dans numCarte
	 * 
	 * @param nomJoueur
	 * type JLabel: récupérer le nom du joueur dans nomJoueur
	 */
	public ControleurJeu(JButton btnSauvegarder, JButton btnFuture, JButton btnPouvoir, JButton btnPoint, JButton btnRienFaire, Partie partie, JLabel numCarte, JLabel nomJoueur) {
		this.btnFuture = btnFuture;
		this.btnPoint = btnPoint;;
		this.btnPouvoir = btnPouvoir;
		this.btnSauvegarder = btnSauvegarder;
		this.partie = partie;
		this.lblNumCarte = numCarte;
		this.btnRienFaire = btnRienFaire;
		this.lblNomJoueur = nomJoueur;
		
		btnSauvegarder.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnSauvegarde
			 * Permet de sauvegarder le numéro du joueur qui joue et d'enregistrer une partie quand le bouton est actionner
			 * 
			 * @param e
			 * type ActionEvent: connaitre l'évèmenet utilisé sur btnSauvegarder
			 */
			public void actionPerformed(ActionEvent e) {
				try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/modele/sauvegarder2.txt"))){
					bos.write(recupererNumJoueur());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				partie.getPlateauJeu().enregistrerPlateauJeu();
				System.out.println("Enregistrer");
			}
		});
		
		btnPouvoir.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnPouvoir
			 * Permet d'utiliser le pouvoir de la carte sélectionner
			 * 
			 * @param e
			 * type ActionEvent qui permret de connaitre l'évèmeent utilisé sur btnPouvoir
			 */
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 2);
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).getDiffuseur().firePropertyChange("joueurContinue", null, e);
				System.out.println(partie.getPlateauJeu());
			}
		});
		
		btnFuture.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnFuture
			 * Permet d'utiliser la carte sélectionner pour le future
			 * 
			 * @param e
			 * type ActionEvent: connaitre l'évènement utilisé sur btnFuture
			 */
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 1);
				
			}
		});
		
		btnPoint.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnPoint
			 * Permet d'utiliser la carte sélectionner pour ses points
			 * 
			 * @param e
			 * type ActionEvent: connaitre l'évèmenent utilisé sur btnPoint
			 */
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 0);
				
			}
		});
		
		btnRienFaire.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnRienFaire
			 * Permet de passer le tour d'un joueur
			 * 
			 * @param e 
			 * type ActionEvent: connaitre l'évènement utilisé sur btnRienFaire
			 */
			public void actionPerformed(ActionEvent e) {
				if (!partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).getPile().isEmpty()) {
					PlateauJeu.getInstance().getJoueurs().get(0).getDiffuseur().firePropertyChange("joueurSuivant", e, null);
				}
				
			}
		});
		
		
	}
	
	/**
	 * Méthode recupererNumJoueur
	 * Permet de récupérer le numéro du joueur (numéro dans l'ArrayList<Joueur> Joueurs de Partie) qui réalise son tour 
	 */
	public int recupererNumJoueur() {
		Iterator<Joueur> it = this.partie.getPlateauJeu().getJoueurs().iterator();
		while(it.hasNext()) {
			Joueur joueur = it.next();
			if (joueur.getNomJoueur().compareTo(lblNomJoueur.getText()) == 0) {
				return partie.getPlateauJeu().getJoueurs().indexOf(joueur);
			}
		}
		return 0;
	}

}
