package modele.Cartes.Bleu;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

/**
 * Classe Deni spécialise Carte
 */
public class Deni extends Carte {
	
	
	/**
	 * Constructeur classe Deni
	 */
	public Deni() {
		super(ValeurCarteEnum.DEUX, "Déni", CouleurCarteEnum.Bleu, "Défaussez une carte de votre Main. \n Copiez le pouvoir de cette carte");
	}


	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Déni
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
		if(joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getMain().getMainCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else if (joueurAttack.getMain().getMainCarte().size() > 0) {
			ArrayList<Integer> numeroCarte = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
			this.vueBouton1(this, joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} else {
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
		joueurAttack.getMain().getMainCarte().remove(c);
		c.capacite(PlateauJeu.getInstance(), joueurAttack, PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1)% 2));
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
		super.vueBouton1(c, joueurAttack);
		
	}
	
	/**
	 * Méthode vueBouton2
	 * Permet de réaliser l'action lorsque le bouton 2 est appuyé dans VueCapacite
	 * 
	 * @param c
	 * type Carte: la carte sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton2(Carte c, Joueur joueurAttack) {
	}

	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	
	

}


