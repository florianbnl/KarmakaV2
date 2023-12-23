package Controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Vue.VueDebutPartie;
import Vue.VueInsererInfoJoueurs;
import Vue.VueJeu;
import modele.Partie;

/**
 * Classe LancerJeu
 * 
 * Controleur de la vue VueDebutPartie
 */
public class LancerJeu {
	
	private JButton btnCommencerPartie;
	private JButton btnReprendrePartie;
	
	/**
	 * Constructeur classe LancerJeu
	 * 
	 * @param btnCommencerPartie
	 * type JButton récupérer le bouton btnCommencerPartie
	 * 
	 * @param btnReprendrePartie 
	 * type JButton récupérer le bouton btnReprendrePartie
	 * 
	 * @param debutPartie
	 * type JFrame récupérer la vue vueDebutPartie
	 */
	public LancerJeu(JButton btnCommencerPartie, JButton btnReprendrePartie, JFrame debutPartie) {
		this.btnCommencerPartie = btnCommencerPartie;
		this.btnReprendrePartie = btnReprendrePartie;
		
		
		btnCommencerPartie.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnCommencerPartie
			 * Permet de lancer une nouvelle partie
			 * 
			 * @param e
			 * type ActionEvent connaitre l'évènement utilisé sur btnCommencerPartie
			 */
			public void actionPerformed(ActionEvent e) {
				debutPartie.dispose();
				VueInsererInfoJoueurs window = new VueInsererInfoJoueurs();
				window.getFrame().setVisible(true);
			}
		});
		
		btnReprendrePartie.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur btnReprendrePartie 
			 * Permet de Reprendre la partie sauvegarder
			 * 
			 * @param e
			 * type ActionEvent connaitre l'évènement utilisé sur btnReprendrePartie
			 */
			public void actionPerformed(ActionEvent e) {
				debutPartie.dispose();
				VueJeu window = new VueJeu();
				window.getFrame().setVisible(true);
				}
		});
		
		
		
	}
	
	/**
	 * Méthode main
	 * Permet de lancer une partie
	 * 
	 * @param args
	 * type String[]
	 */
	public static void main(String[] args) {
		VueDebutPartie window = new VueDebutPartie();
		window.getFrame().setVisible(true);
	}
}

