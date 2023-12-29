package modele.Cartes.Mosaique;

import modele.*;

/**
 * Classe Mimetisme spécialise Carte
 */
public class Mimetisme extends Carte {
	
	private String nomPouvoir;
	
	/**
	 * Constructeur classe Mimetisme
	 */
	public Mimetisme() {
		super(ValeurCarteEnum.UN, "Mimétisme", CouleurCarteEnum.Mosaique, "Choisissez un Rival. \nCopiez le pouvoir de son Oeuvre Exposée.");
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
	 * Permet d'utiliser la capacite de la carte Mimétisme
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
			joueurVise.getOeuvres().getOeuvresCarte().getFirst().capacite(plateauJeu, joueurAttack, joueurVise);
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		
	}

}
