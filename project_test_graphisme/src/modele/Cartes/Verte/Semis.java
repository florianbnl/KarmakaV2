package modele.Cartes.Verte;

import java.util.ArrayList;
import java.util.Iterator;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe Semis spécialise Carte
 */
public class Semis extends Carte {
	
	/**
	 * Constructeur classe Semis
	 */
	public Semis() {
		super(ValeurCarteEnum.DEUX, "Semis", CouleurCarteEnum.Vert, "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Semis
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 * 
	 */
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getMain().getMainCarte().add(plateauJeu.getSource().distribuerUneCarte());
		joueurAttack.getMain().getMainCarte().add(plateauJeu.getSource().distribuerUneCarte());
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir");
			
			VueCapacite vc = new VueCapacite(this, joueurAttack.getMain().getMainCarte(), nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 2);
			Iterator<Integer> it = numCarteRecuperer.iterator();
			ArrayList<Carte> carte = new ArrayList<Carte>();
			while (it.hasNext()) {
				carte.add(joueurAttack.getMain().getMainCarte().get(it.next()));
			}
			Iterator<Carte> itCarte = carte.iterator();
			while (itCarte.hasNext()) {
				this.vueBouton1(itCarte.next(), joueurAttack);
			}
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
		
	}
	
	/**
	 * Méthode vueBouton1
	 * Permet de réaliser l'action lorsque le bouton 1 est appuyé dans VueCapacite
	 * 
	 * @param c
	 * type Carte: la carte sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		joueurAttack.getVieFuture().getVieFutureCarte().add(c);
		joueurAttack.getMain().getMainCarte().remove(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}

