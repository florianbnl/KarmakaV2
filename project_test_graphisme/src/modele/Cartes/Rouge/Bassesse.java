package modele.Cartes.Rouge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

/**
 * Classe Bassesse spécialise Carte
 */
public class Bassesse extends Carte {
	
	/**
	 * Constructeur classe Bassesse
	 */
	public Bassesse() {
		super(ValeurCarteEnum.TROIS, "Bassesse", CouleurCarteEnum.Rouge, "Défaussez au hasard 2 cartes de la Main d'un rival");
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
	 * Permet d'utiliser la capacite de la carte Bassesse
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
		if (joueurAttack.getStrategie().vraiJoueur() && !joueurVise.getMain().getMainCarte().isEmpty()) {
			int numCarte = 0;
			Iterator<Carte> it = joueurVise.getMain().getMainCarte().iterator();
			ArrayList<Carte> carteAfficher = new ArrayList<Carte>();
			while (it.hasNext()) {
				carteAfficher.add(new CarteCache(String.valueOf(numCarte)));
				carteAfficher.getLast().setNomCarte(String.valueOf(numCarte));
				it.next();
				numCarte++;
			}
			
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("choisir Carte");
			
			VueCapacite vc = new VueCapacite(this, carteAfficher, nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		} else if (!joueurVise.getMain().getMainCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurVise.getMain().getMainCarte().size(), 2);
			Iterator<Integer> it = numCarteRecuperer.iterator();
			while (it.hasNext()) {
				this.vueBouton1(joueurVise.getMain().getMainCarte().get(it.next()), joueurAttack);
			}
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
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
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

}
