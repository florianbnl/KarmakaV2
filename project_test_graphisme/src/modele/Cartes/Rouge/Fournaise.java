package modele.Cartes.Rouge;

import modele.*;

/**
 * Classe Fournaise spécialise Carte
 */
public class Fournaise extends Carte {
	
	/**
	 * Constructeur classe Fournaise
	 */
	public Fournaise() {
		super(ValeurCarteEnum.DEUX, "Fournaise", CouleurCarteEnum.Rouge, "Défaussez les 2 premières cartes de la Vie Future d'un rival");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Fournaise
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
		for (int i = 0; i<2 && joueurVise.getVieFuture().getVieFutureCarte().size() > 0; i++) {
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
