package Vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controleur.ControleurChoixJoueur;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;

public class VueInsererInfoJoueurs {

	private JFrame frame;
	private JTextField txtFieldNom1;
	private JTextField txtFieldNom2;
	private JLabel lblNomJoueur1;
	private JLabel lblNomJoueur2;
	private JLabel lblJoueur1;
	private JLabel lblJoueur2;
	private JComboBox comboBoxTypeJoueur;
	private JComboBox comboBoxTypeBot;
	private JLabel lblTextTypeJoueur;
	private JLabel lblTextTypeBot;
	private JButton btnLancerPartie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueInsererInfoJoueurs window = new VueInsererInfoJoueurs();
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
	public VueInsererInfoJoueurs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 550);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtFieldNom1 = new JTextField();
		txtFieldNom1.setBounds(50, 176, 132, 30);
		frame.getContentPane().add(txtFieldNom1);
		txtFieldNom1.setText("Joueur1");
		txtFieldNom1.setColumns(10);
		
		lblNomJoueur1 = new JLabel("Nom Joueur");
		lblNomJoueur1.setBounds(50, 155, 96, 21);
		frame.getContentPane().add(lblNomJoueur1);
		
		txtFieldNom2 = new JTextField();
		txtFieldNom2.setColumns(10);
		txtFieldNom2.setBounds(625, 176, 132, 30);
		txtFieldNom2.setText("Joueur2");
		frame.getContentPane().add(txtFieldNom2);
		
		lblNomJoueur2 = new JLabel("Nom Joueur");
		lblNomJoueur2.setBounds(625, 155, 96, 21);
		frame.getContentPane().add(lblNomJoueur2);
		
		lblJoueur1 = new JLabel("Joueur 1");
		lblJoueur1.setBounds(50, 41, 287, 54);
		lblJoueur1.setFont(new Font(lblJoueur1.getFont().getName(), lblJoueur1.getFont().getStyle(), 30));
		frame.getContentPane().add(lblJoueur1);
		
		lblJoueur2 = new JLabel("Joueur 2");
		lblJoueur2.setBounds(625, 41, 287, 54);
		lblJoueur2.setFont(new Font(lblJoueur2.getFont().getName(), lblJoueur2.getFont().getStyle(), 30));
		frame.getContentPane().add(lblJoueur2);
		
		comboBoxTypeJoueur = new JComboBox();
		comboBoxTypeJoueur.setBounds(625, 266, 132, 22);
		comboBoxTypeJoueur.addItem(new String("Reel"));
		comboBoxTypeJoueur.addItem(new String("Bot"));
		frame.getContentPane().add(comboBoxTypeJoueur);
		
		comboBoxTypeBot = new JComboBox();
		comboBoxTypeBot.setBounds(625, 363, 132, 22);
		File dossierStrategie = new File("./src/modele/Strategie");
		try {
			File[] nomStrategie = dossierStrategie.listFiles();
			for (File fichier : nomStrategie) {
				String tamp = fichier.getName().replaceFirst(".java", "");
				if (tamp.compareTo("Strategie") != 0 && tamp.compareTo("JoueurReel") !=0) {
					comboBoxTypeBot.addItem(tamp);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboBoxTypeBot.setVisible(false);
		frame.getContentPane().add(comboBoxTypeBot);
		
		lblTextTypeJoueur = new JLabel("Type Joueur");
		lblTextTypeJoueur.setBounds(625, 245, 96, 21);
		
		frame.getContentPane().add(lblTextTypeJoueur);
		
		lblTextTypeBot = new JLabel("Type de bot");
		lblTextTypeBot.setBounds(625, 344, 96, 21);
		lblTextTypeBot.setVisible(false);
		frame.getContentPane().add(lblTextTypeBot);
		
		btnLancerPartie = new JButton("Lancer partie");
		btnLancerPartie.setBounds(50, 445, 873, 41);
		frame.getContentPane().add(btnLancerPartie);
		
		ControleurChoixJoueur controleurChoixJoueur = new ControleurChoixJoueur(txtFieldNom1, txtFieldNom2, comboBoxTypeJoueur, comboBoxTypeBot, lblTextTypeBot, btnLancerPartie, this.getFrame());
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
