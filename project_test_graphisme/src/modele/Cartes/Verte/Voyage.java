package modele.Cartes.Verte;

import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe Voyage spécialise Carte
 */
public class Voyage extends Carte {
	
	/**
	 * Constructeur classe Voyage
	 */
	public Voyage() {
		super(ValeurCarteEnum.TROIS, "Voyage", CouleurCarteEnum.Vert, "Puisez 3 cartes à la Source. \nVous pouvez ensuite joueur une autre carte.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Voyage
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
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		
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

