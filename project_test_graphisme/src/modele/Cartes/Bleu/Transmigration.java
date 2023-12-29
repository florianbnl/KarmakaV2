package modele.Cartes.Bleu;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

/**
 * Classe Transmigration spécialise Carte
 */
public class Transmigration extends Carte {
	
	/**
	 * Constructeur classe Transmigration
	 */
	public Transmigration() {
		super(ValeurCarteEnum.UN, "Transmigration", CouleurCarteEnum.Bleu, "Placez dans votre Main n'importe quelle carte de votre Vie Future.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Transmigration
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
		if (joueurAttack.getStrategie().vraiJoueur() && !joueurAttack.getVieFuture().getVieFutureCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getVieFuture().getVieFutureCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else if (!joueurAttack.getVieFuture().getVieFutureCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getVieFuture().getVieFutureCarte().size(), 1);
			this.vueBouton1(joueurAttack.getVieFuture().getVieFutureCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}

	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
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
		joueurAttack.getMain().getMainCarte().add(c);
		joueurAttack.getVieFuture().getVieFutureCarte().remove(c);
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


