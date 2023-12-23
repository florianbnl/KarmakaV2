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
/**
 * Classe ControleurCapacite :
 * 
 * Controleur de la VueCapacite qui permet de gérer les actions des boutons
 * 
 */
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
	

	/**
	 * 
	 * Constructeur Classe ControleurCapacite
	 * 
	 * @param object
	 * type AccesVueCapacite: récupérer l'objet qui utilise la vue
	 * 
	 * @param vueCapacite
	 * type VueCapacite: récupérer la vue créée
	 * 
	 * @param frame
	 * type JFrame: récupérer la fenetre de la vue VueInsererInfoJoueur
	 * 
	 * @param btnHaut
	 * type JButton: récupérer le bouton btnHaut
	 * 
	 * @param btnBas
	 * type JButton: récupérer le bouton btnBas
	 * 
	 * @param lblNumCarte
	 * type JLabel: récupérer le numéro de la carte sélectionner
	 * 
	 * @param carte
	 * type ArrayList<Carte>: avoir accès au carte qui sont affichées a l'utilisateur
	 * 
	 * @param nbBoutonTouche
	 * type Integer: connaitre le nombre de fois où un bouton dois être sélectionné dans la vueCapacite
	 * 
	 * @param joueurAttack
	 * type Joueur: savoir le joueur qui est entrain de jouer son tour
	 * 
	 */
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
			/**
			 * Méthode actionPerformed sur btnHaut
			 * Permer d'ajouter une action lorsque le bouton btnHaut est appuyé
			 * 
			 * @param e
			 * type ActionEvent: connaitre l'évènement utilisé sur le btnHaut
			 * 
			 */
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
			/**
			 * Méthode actionPerformed sur btnBas
			 * Permer d'ajouter une action lorsque le bouton btnBas est appuyé
			 * 
			 * @param e
			 * type ActionEvent: connaitre l'évènement utilisé sur le btnBas
			 * 
			 */
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
	
	/**
	 * Méthode mettreAJourNbBoutonTouche
	 * décrémenter nbBoutonTouche
	 */	
	public void mettreAJourNbBoutonTouche() {
		this.nbBoutonTouche--;
	}
	/**
	 * Méthode getNbBoutonTouche
	 * getter de l'attribut nbBoutonTouche
	 */
	public int getNbBoutonTouche() {
		return this.nbBoutonTouche;
	}
	
	/**
	 * Méthode  setNbBoutonTouche
	 * setter de l'attribut nbBoutonTouche
	 */
	public void setNbBoutonTouche() {
		this.nbBoutonTouche = 0;
	}
	
	/**
	 * Méthode roulette
	 * permet de réaliser la 2ème action de la carte Roulette ("Puiser à la source autant de carte + 1)
	 * 
	 * @param joueurAttack
	 * type Joueur: savoir le joueur qui est entrain de jouer son tour
	 */
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
