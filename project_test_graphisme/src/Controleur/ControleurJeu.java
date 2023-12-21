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

public class ControleurJeu  {
	
	private JButton btnSauvegarder, btnFuture, btnPouvoir, btnPoint;
	private Partie partie;
	private JLabel lblNomJoueur;
	private JLabel lblNumCarte;
	private JButton btnRienFaire;
	
	
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
			
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 2);
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).getDiffuseur().firePropertyChange("joueurContinue", null, e);
				System.out.println(partie.getPlateauJeu());
			}
		});
		
		btnFuture.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 1);
				
			}
		});
		
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).utiliserCarte(Integer.parseInt(numCarte.getText())-1, 0);
				
			}
		});
		
		btnRienFaire.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!partie.getPlateauJeu().getJoueurs().get(recupererNumJoueur()).getPile().isEmpty()) {
					PlateauJeu.getInstance().getJoueurs().get(0).getDiffuseur().firePropertyChange("joueurSuivant", e, null);
				}
				
			}
		});
		
		
	}
	
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
