package Vue;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.Joueur;

/**
 * Classe VueGagner
 */
public class VueGagner {

	private JFrame frame;
	private Joueur joueurGagnant;
	private JLabel lblGagnant;

	/**
	 * Constructeur classe VueGagner
	 * Créer la vue
	 */
	public VueGagner(Joueur joueurGagnant) {
		this.joueurGagnant = joueurGagnant;
		initialize();
	}

	/**
	 * Méthode initializa
	 * Initialise le contenue de la vue
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane.setBackground(this.joueurGagnant.getColorText());
		
		lblGagnant = new JLabel();
		lblGagnant.setBounds(228, 74, 584, 329);
		frame.getContentPane().add(lblGagnant);
		lblGagnant.setText("<html>" + this.joueurGagnant.getNomJoueur() + " a \ngagné !!!</html>");
		lblGagnant.setFont(new Font(lblGagnant.getFont().getName(), lblGagnant.getFont().getStyle(), 100));
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
