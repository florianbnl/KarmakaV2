package modele.Cartes.Bleu;

import modele.*;
import modele.Cartes.CarteCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Vue.VueCapacite;

/**
 * Classe Duperie spécialise Carte
 */
public class Duperie extends Carte {
	
	/**
	 * Construteur classe Duperie
	 */
	public Duperie() {
		super(ValeurCarteEnum.TROIS, "Duperie", CouleurCarteEnum.Bleu, "Regardez 3 cartes de la Main d'un rival. \nAjoutez-en une à votre Main");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Duperie
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
	
		ArrayList<Carte> carteAfficher = new ArrayList<Carte>();
		if (joueurVise.getMain().getMainCarte().size() < 3) {
			carteAfficher = joueurVise.getMain().getMainCarte();
		}
		else {
			ArrayList<Integer> numCarte = new ArrayList<Integer>();
			Random rd = new Random();
			numCarte.add(rd.nextInt(joueurVise.getMain().getMainCarte().size()));
			int nombreCarteTire = 1;
			while (nombreCarteTire<3) {
				int num = rd.nextInt(joueurVise.getMain().getMainCarte().size());
				if (!numCarte.contains(num)){
					numCarte.add(num);
					nombreCarteTire++;
					carteAfficher.add(joueurVise.getMain().getMainCarte().get(num));
				}
				
			}
		}
		
		if (joueurAttack.getStrategie().vraiJoueur() && !carteAfficher.isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir une carte a retourner");
			VueCapacite vc = new VueCapacite(this, carteAfficher, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else if (!carteAfficher.isEmpty()){
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(carteAfficher.size(), 1);
			this.vueBouton1(carteAfficher.get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} else {
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
		PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2).getMain().getMainCarte().remove(c);
		joueurAttack.getMain().getMainCarte().add(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	/**
	 * Méthode vueBouton2
	 * Permet de réaliser l'action lorsque le bouton 2 est appuyé dans VueCapacite
	 * 
	 * @param c
	 * type Carte: la carte sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton2(Carte c, Joueur joueurAttack) {
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	

}


