package modele.Cartes.Rouge;

import modele.*;

/**
 * Classe Vengeance spécialise Carte
 */
public class Vengeance extends Carte {
	
	private String nomPouvoir;
	
	/**
	 * Constructeur classe Vengeance
	 */
	public Vengeance() {
		super(ValeurCarteEnum.TROIS, "Vengeance", CouleurCarteEnum.Rouge, "Défaussez l'Oeuvre Exposée d'un rival.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Vengeance
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
		if (!joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			joueurVise.getOeuvres().getOeuvresCarte().removeFirst();	
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
