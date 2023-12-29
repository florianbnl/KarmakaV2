package modele.Cartes.Bleu;

import modele.*;

/**
 * Classe Vol spécialise Carte
 */
public class Vol extends Carte {
	
	/**
	 * Constructeur classe Vol
	 */
	public Vol() {
		super(ValeurCarteEnum.TROIS, "Vol", CouleurCarteEnum.Bleu, "Placez dans votre Main l'Oeuvre Exposée d'un rival");
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
	 * Permet d'utiliser la capacite de la carte Vol
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
		if (!joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			joueurAttack.getMain().ajouterCarte(joueurVise.getOeuvres().getOeuvresCarte().removeFirst());
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		
	}

}


