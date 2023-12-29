package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe Jubile spécialise Carte
 */
public class Jubile extends Carte {
	
	/**
	 * Constructeur classe Jubile
	 */
	public Jubile() {
		super(ValeurCarteEnum.TROIS, "Jubilé", CouleurCarteEnum.Vert, "Placez jusqu'à 2 cartes de votre Mais sur vos Oeuvres.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Jubilé
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
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir pour l'Oeuvre");
			nomBouton.add("Arreter");
			
			VueCapacite vc = new VueCapacite(this, joueurAttack.getMain().getMainCarte(), nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> nbCarte = joueurAttack.getStrategie().choixBot(3, 1);
			for (int i = 0; i<nbCarte.getFirst() && 0 < joueurAttack.getMain().getMainCarte().size(); i++) {
				ArrayList<Integer> numCarte = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
				Carte c = joueurAttack.getMain().getMainCarte().get(numCarte.getFirst());
				this.vueBouton1(c, joueurAttack);
			}
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
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
		joueurAttack.getMain().getMainCarte().remove(c);
		joueurAttack.getOeuvres().getOeuvresCarte().addFirst(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}

