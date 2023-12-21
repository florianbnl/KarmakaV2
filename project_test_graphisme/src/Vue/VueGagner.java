package Vue;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.Joueur;

public class VueGagner {

	private JFrame frame;
	private Joueur joueurGagnant;
	private JLabel lblGagnant;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VueGagner(Joueur joueurGagnant) {
		this.joueurGagnant = joueurGagnant;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblGagnant = new JLabel();
		lblGagnant.setBounds(228, 74, 584, 329);
		frame.getContentPane().add(lblGagnant);
		lblGagnant.setText("<html>" + this.joueurGagnant.getNomJoueur() + " a \ngagné !!!</html>");
		lblGagnant.setFont(new Font(lblGagnant.getFont().getName(), lblGagnant.getFont().getStyle(), 100));
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
