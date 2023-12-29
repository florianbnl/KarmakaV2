package modele.Cartes.Bleu;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

/**
 * Classe CoupDOeil spécialise Carte
 */
public class CoupDOeil extends Carte {
	
	
	/**
	 * Constructeur classe CoupDOeil
	 */
	public CoupDOeil() {
		super(ValeurCarteEnum.UN  , "Coup d'Oeil", CouleurCarteEnum.Bleu, "Regardez la Main d'un rival. \nVous pouvez ensuite jouer une autre carte.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Coup d'Oeil
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
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise){
		if(joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Continuer");
			VueCapacite vc = new VueCapacite(this, joueurVise.getMain().getMainCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
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


