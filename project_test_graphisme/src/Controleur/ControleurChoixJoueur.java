package Controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Vue.VueJeu;
import modele.Partie;
import modele.Strategie.Aleatoire;
import modele.Strategie.Defensive;
import modele.Strategie.JoueurReel;
import modele.Strategie.Offensive;
import modele.Strategie.Strategie;

/**
 * Classe ControleurChoixJoueur
 * 
 * Controleur de la vue VueInsererInfoJoueur
 */
public class ControleurChoixJoueur {

	private JTextField recupererNomJoueur1;
	private JTextField recupererNomJoueur2;
	private JComboBox typeJoueur;
	private JComboBox typeBot;
	private JLabel textTypeBot;
	private JButton lancerPartie;
	
	/**
	 * Contructeur Classe ControleurChoixJoueur
	 * 
	 * @param nomJoeur1
	 * type JTextField: récupérer le nom du joueur 1 entrée par l'utilisateur
	 * 
	 * @param nomJoueur2
	 * type JTextField: récupérer le nom du joueur 2 entrée par l'utilisateur
	 * 
	 * @param typeJoueur
	 * type JComboBox: récupérer l'info sélectionner par l'utilisateur
	 * 
	 * @param typeBot
	 * type JComboBox: récupérer l'info sélectionner par l'utilisateur
	 * 
	 * @param textTypeBot
	 * type JLabel: afficher un texte au joueur
	 * 
	 * @param lancerPartie
	 * type JButton: récupérer le bouton lancerPartie
	 * 
	 * @param RecupererInfoJoueur
	 * type JFrame: récupérer la fenetre de la vue VueInsererInfoJoueurs
	 */
	public ControleurChoixJoueur(JTextField nomJoueur1,JTextField nomJoueur2, JComboBox typeJoueur, JComboBox typeBot, JLabel textTypeBot, JButton lancerPartie, JFrame RecupererInfoJoueur) {
		this.recupererNomJoueur1 = nomJoueur1;
		this.recupererNomJoueur2 = nomJoueur2;
		this.typeJoueur = typeJoueur;
		this.typeBot = typeBot;
		this.textTypeBot = textTypeBot;
		this.lancerPartie = lancerPartie;
		
		
		typeJoueur.addItemListener(new ItemListener() {
			/**
			 * Méthode itemStateChanged sur typeJoueur
			 * Permet d'afficher ou non l'attribut typeBot et textTypeBot selon si le joueur est Reel ou non
			 * 
			 * @param e
			 * type ItemEvent: connaitre l'évèmenent utilisé sur le typeJoueur
			 */
			public void itemStateChanged(ItemEvent e) {
				if (typeJoueur.getSelectedItem().equals("Reel")) {
					typeBot.setVisible(false);
					textTypeBot.setVisible(false);
				} else if (typeJoueur.getSelectedItem().equals("Bot")) {
					typeBot.setVisible(true);
					textTypeBot.setVisible(true);
				}
			}
		});
		
		lancerPartie.addActionListener(new ActionListener() {
			/**
			 * Méthode actionPerformed sur lancerPartie
			 * Permet de lancer la partie en récupérant le nom du joueur 1, nom du joueur 2 et la strategie du joueur 2
			 * 
			 * @param e
			 * type ActionEvent: connaitr l'évènement utilisé sur lancerPartie
			 */
			public void actionPerformed(ActionEvent e) {
				Strategie strategieJoueur2 = null;
				switch (typeJoueur.getSelectedIndex()) {
				case 0: {
					strategieJoueur2 = new JoueurReel();
					break;
				}
				case 1: {
					
					switch (typeBot.getSelectedIndex()) {
					case 0: {
						strategieJoueur2 = new Aleatoire();
						break;
					}
					case 1: {
						strategieJoueur2 = new Defensive();
						break;
					}
					case 2: {
						strategieJoueur2 = new Offensive();
					}
					}
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + typeBot.getActionCommand());
				}
				RecupererInfoJoueur.dispose();
				VueJeu window = new VueJeu(recupererNomJoueur1.getText(), recupererNomJoueur2.getText(), strategieJoueur2);
				window.getFrame().setVisible(true);
			}
		});
	}
	
	
}
