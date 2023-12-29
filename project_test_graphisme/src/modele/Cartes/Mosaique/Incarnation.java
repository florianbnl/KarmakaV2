package modele.Cartes.Mosaique;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

/**
 * Classe Incarnation spécialise Carte
 */
public class Incarnation extends Carte {
	
	/**
	 * Constructeur classe Carte
	 */
	public Incarnation() {
		super(ValeurCarteEnum.UN, "Incarnation", CouleurCarteEnum.Mosaique, "Choisissez une de vos Oeuvres. \nCopiez son pouvoir");
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Incarnation
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
		if (joueurAttack.getStrategie().vraiJoueur() && !joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getOeuvres().getOeuvresCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else if (!joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getOeuvres().getOeuvresCarte().size(), 1);
			this.vueBouton1(joueurAttack.getOeuvres().getOeuvresCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} 
		else {
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
		c.capacite(PlateauJeu.getInstance(), joueurAttack, PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1)% 2));
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
}
