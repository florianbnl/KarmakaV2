package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Vue.VueCapacite;
import modele.AccesVueCapacite;
import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;
import modele.Cartes.CarteCache;
import modele.Cartes.Rouge.Roulette;

public class ControleurCapacite {
	
	private Object object;
	private VueCapacite vueCapacite;
	private JFrame frame;
	private JButton btnHaut, btnBas;
	private JLabel lblNumCarte;
	private ArrayList<Carte> carte;
	private int nbBoutonTouche;
	private boolean estRoulette;
	private int nombreCarte;
	

	public ControleurCapacite(AccesVueCapacite object, VueCapacite vueCapacite, JFrame frame, JButton btnHaut, JButton btnBas, JLabel lblNumCarte, ArrayList<Carte> carte, int nbBoutonTouche, Joueur joueurAttack) {
		this.object = object;
		this.vueCapacite = vueCapacite;
		this.frame = frame;
		this.btnHaut = btnHaut;
		this.btnBas = btnBas;
		this.lblNumCarte = lblNumCarte;
		this.carte = carte;
		this.nbBoutonTouche = nbBoutonTouche;
		this.estRoulette = !(this.carte.getFirst() instanceof CarteCache);
		this.nombreCarte = this.carte.size();
		
		btnHaut.addActionListener(new ActionListener() {
			
			public synchronized void actionPerformed(ActionEvent e) {
				object.vueBouton1(vueCapacite.getCarteAfficher().get(Integer.parseInt(lblNumCarte.getText())-1), joueurAttack);
				mettreAJourNbBoutonTouche();
				if (getNbBoutonTouche() == 0 || carte.size()-1 < 1) {
					frame.dispose();
					if (object instanceof Roulette && estRoulette) {
						roulette(joueurAttack);
					}
					else if (object instanceof Carte) {
						Joueur joueurVise = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
						joueurVise.getStrategie().seFaireAttaquer((Carte)object,joueurVise , PlateauJeu.getInstance());
					}
				}
			}
		});
				
		
		btnBas.addActionListener(new ActionListener() {
			
			public synchronized void actionPerformed(ActionEvent e) {
				object.vueBouton2(vueCapacite.getCarteAfficher().get(Integer.parseInt(lblNumCarte.getText())-1), joueurAttack);
				setNbBoutonTouche();
				if (getNbBoutonTouche() == 0) {
					carte.remove(Integer.parseInt(lblNumCarte.getText())-1);
					Iterator<Carte> it = carte.iterator();
					while (it.hasNext()) {
						object.vueBouton2(it.next(), joueurAttack);
					}
					frame.dispose();
					if (object instanceof Roulette && estRoulette) {
						roulette(joueurAttack);
					}
					else if (object instanceof Carte) {
						Joueur joueurVise = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
						joueurVise.getStrategie().seFaireAttaquer((Carte)object,joueurVise , PlateauJeu.getInstance());
					}
				}
			}
		});
}
	
	public void mettreAJourNbBoutonTouche() {
		this.nbBoutonTouche--;
	}
	
	public int getNbBoutonTouche() {
		return this.nbBoutonTouche;
	}
	
	public void setNbBoutonTouche() {
		this.nbBoutonTouche = 0;
	}
	
	public void roulette(Joueur joueurAttack) {
		ArrayList<String> nomBouton = new ArrayList<String>();
		nomBouton.add("desable");
		nomBouton.add("Choix nombres cartes");
		
		nombreCarte -= joueurAttack.getMain().getMainCarte().size();
		ArrayList<Carte> afficherCarte = new ArrayList<Carte>();
		
		for (int i = 0; i<= nombreCarte + 1; i++) {
			afficherCarte.add(new CarteCache(String.valueOf(i)));
			afficherCarte.getLast().setNomCarte(String.valueOf(i));
		}
		
		VueCapacite vc = new VueCapacite((AccesVueCapacite)object, afficherCarte, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
	}
}
