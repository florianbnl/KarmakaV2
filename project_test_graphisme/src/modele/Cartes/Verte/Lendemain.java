package modele.Cartes.Verte;

import java.util.ArrayList;

import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe Lendemain spécialise Carte
 */
public class Lendemain extends Carte {
	
	/**
	 * Constructeur classe Lendemain
	 */
	public Lendemain() {
		super(ValeurCarteEnum.UN, "Lendemain", CouleurCarteEnum.Vert, "Puisez une carte à la Source. \nVous pouvez ensuite jouer une autre carte.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Lendemain
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

