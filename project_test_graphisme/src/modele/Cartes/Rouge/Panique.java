package modele.Cartes.Rouge;

import modele.*;

/**
 * Classe Panique spécialise Carte
 */
public class Panique extends Carte {
	
	private String nomPouvoir;
	
	/**
	 * Constructeur classe Panique
	 */
	public Panique() {
		super(ValeurCarteEnum.UN, "Panique", CouleurCarteEnum.Rouge, "Défaussez la première carte de la Pile d'un joueur. \nVous pouvez ensuite jouer une autre carte.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Panique
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
		if (!joueurVise.getPile().getPileCartes().isEmpty()) {
			plateauJeu.getFosse().defausserCarte(joueurVise.getPile().getPileCartes().removeFirst());
		}

		
	}
	
	/**
	 * Méthode apresUtilisationCapacite
	 * Permet de réaliser des actions après l'utilisation d'une capacité
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		joueurAttack.getDiffuseur().firePropertyChange("joueurContinue", this, joueurAttack);
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
