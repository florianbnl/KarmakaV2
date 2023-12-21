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

public class LancerJeu {
	
	private JButton btnCommencerPartie;
	private JButton btnReprendrePartie;
	
	public LancerJeu(JButton btnCommencerPartie, JButton btnReprendrePartie, JFrame debutPartie) {
		this.btnCommencerPartie = btnCommencerPartie;
		this.btnReprendrePartie = btnReprendrePartie;
		
		
		btnCommencerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debutPartie.dispose();
				VueInsererInfoJoueurs window = new VueInsererInfoJoueurs();
				window.getFrame().setVisible(true);
			}
		});
		
		btnReprendrePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debutPartie.dispose();
				VueJeu window = new VueJeu();
				window.getFrame().setVisible(true);
				}
		});
		
		
		
	}
	
	public void fermerFenetre(JFrame page) {
		page.dispose();
	}
	
	public static void main(String[] args) {
		VueDebutPartie window = new VueDebutPartie();
		window.getFrame().setVisible(true);
	}
}

