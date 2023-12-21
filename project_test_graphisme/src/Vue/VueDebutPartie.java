
package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controleur.LancerJeu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueDebutPartie {

	private JFrame frame;
	private JButton btnCommencerPartie;
	private JButton btnReprendrePartie;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public VueDebutPartie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
