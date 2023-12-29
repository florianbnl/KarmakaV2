package modele.Cartes.Bleu;

import modele.*;

/**
 * Classe REvesBrises spécialise Carte
 */
public class RevesBrises extends Carte {
	
	/**
	 * Constructeur classe RevesBrises
	 */
	public RevesBrises() {
		super(ValeurCarteEnum.DEUX, "Rêves Brisés", CouleurCarteEnum.Bleu, "Placez la première carte de la Vie Future d'un rival sur la vôtre");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Rêves Brisés
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
		if (!joueurVise.getVieFuture().getVieFutureCarte().isEmpty()) {
			joueurAttack.getVieFuture().ajouterCarte(joueurVise.getVieFuture().getFirst());
			joueurVise.getVieFuture().removeFirst();
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}


