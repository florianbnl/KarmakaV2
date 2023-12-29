package modele.Cartes.Rouge;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

/**
 * Classe Crise spécialise Carte
 */
public class Crise extends Carte {
	
	/**
	 * Constructeur classe Crise
	 */
	public Crise() {
		super(ValeurCarteEnum.DEUX, "Crise", CouleurCarteEnum.Rouge, "Le rival de votre choix défausse une de ses Oeuvres");
	}
	
	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Crise
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
		if (joueurVise.getStrategie().vraiJoueur() && !joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			
			VueCapacite vc = new VueCapacite(this, joueurVise.getOeuvres().getOeuvresCarte(), nomBouton, null, 1, joueurVise.getCouleurText(), joueurAttack);
		} else if (!joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurVise.getStrategie().choixBot(joueurVise.getOeuvres().getOeuvresCarte().size(), 1);
			this.vueBouton1(joueurVise.getOeuvres().getOeuvresCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
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
		PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2).getOeuvres().getOeuvresCarte().remove(c);
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	/**
	 * Méghode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
