
package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controleur.LancerJeu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe VueDebutPartie
 */
public class VueDebutPartie {

	private JFrame frame;
	private JButton btnCommencerPartie;
	private JButton btnReprendrePartie;
	

	/**
	 * Construteur classe VueDebutPartie
	 * Créer la vue
	 */
	public VueDebutPartie() {
		initialize();
	}

	/**
	 * Méthode initialize
	 * Initialise le contenue de la fenêtre
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 550);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		btnCommencerPartie = new JButton("Commencer une nouvelle partie");
		btnCommencerPartie.setBounds(249, 67, 524, 105);
		getFrame().getContentPane().add(btnCommencerPartie);
		
		JButton btnReprendrePartie = new JButton("Reprendre la partie sauvegardé");
		btnReprendrePartie.setBounds(249, 260, 524, 105);
		getFrame().getContentPane().add(btnReprendrePartie);
		new LancerJeu(btnCommencerPartie, btnReprendrePartie, getFrame());
	}

	/**
	 * Méthode getFrame
	 * getter de l'attribut frame
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
}
