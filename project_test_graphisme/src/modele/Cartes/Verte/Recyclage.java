package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe Recyclage spécialise Carte
 */
public class Recyclage extends Carte {
	
	/**
	 * Constructeur Recyclage
	 */
	public Recyclage() {
		super(ValeurCarteEnum.UN, "Recyclage", CouleurCarteEnum.Vert, "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Recyclage
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
		if (joueurAttack.getStrategie().vraiJoueur() && !plateauJeu.getFosse().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			
			ArrayList<Carte> afficherCarte = new ArrayList<Carte>();
			for (int i = 0; i<3 && PlateauJeu.getInstance().getFosse().getFosseCarte().size() > i; i++) {
				afficherCarte.add(PlateauJeu.getInstance().getFosse().getFosseCarte().get(i));
			}
			
			VueCapacite vc = new VueCapacite(this, afficherCarte, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else if (!plateauJeu.getFosse().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer;
			if (plateauJeu.getFosse().getFosseCarte().size() < 3) {
				numCarteRecuperer = joueurAttack.getStrategie().choixBot(plateauJeu.getFosse().getFosseCarte().size(), 1);
			} else {
				numCarteRecuperer = joueurAttack.getStrategie().choixBot(3, 1);
			}
			this.vueBouton1(plateauJeu.getFosse().getFosseCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		else {
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
		PlateauJeu.getInstance().getFosse().getFosseCarte().remove(c);
		joueurAttack.getVieFuture().getVieFutureCarte().add(c);
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

