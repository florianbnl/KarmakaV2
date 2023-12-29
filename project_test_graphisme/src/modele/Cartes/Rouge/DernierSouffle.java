package modele.Cartes.Rouge;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

/**
 * Classe DernierSouffle spécialise Carte
 */
public class DernierSouffle extends Carte {
	
	/**
	 * Constructeur classe DernierSouffle
	 */
	public DernierSouffle() {
		super(ValeurCarteEnum.UN, "Dernier Souffle", CouleurCarteEnum.Rouge, "Le joueur de votre choix défausse une carte de sa Main.");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Dernier Souffle
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
			nomBouton.add(joueurAttack.getNomJoueur() + " défausse un carte");
			nomBouton.add(joueurVise.getNomJoueur() + " défausse une carte");
			
			ArrayList<Carte> carteAffiche = new ArrayList<Carte>();
			carteAffiche.add(new CarteCache("1"));
			
			VueCapacite vc = new VueCapacite(this, carteAffiche, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numJoueur = joueurAttack.getStrategie().choixBot(2, 1);
			switch (numJoueur.getFirst()) {
			case 0: {
				this.vueBouton1(this, joueurAttack);
				break;
			}
			case 1: {
				this.vueBouton2(this, joueurAttack);
				break;
			}
			}
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
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Defausser Carte");
			super.vueBouton1(c, joueurAttack);
			VueCapacite vc = new VueCapacite(joueurAttack, joueurAttack.getMain().getMainCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
			PlateauJeu.getInstance().getFosse().defausserCarte(joueurAttack.getMain().getMainCarte().get(numCarteRecuperer.getFirst()));
			joueurAttack.getMain().getMainCarte().remove(numCarteRecuperer.getFirst());
			PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack)+ 1) % 2).getStrategie().seFaireAttaquer(this, joueurAttack, PlateauJeu.getInstance());
		}
		
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
		Joueur joueurVise = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
		
		if (joueurVise.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Defausser Carte");
			super.vueBouton2(c, joueurAttack);
			VueCapacite vc = new VueCapacite(joueurVise, joueurVise.getMain().getMainCarte(), nomBouton, null, 1, joueurVise.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numCarteRecuperer = joueurVise.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
			PlateauJeu.getInstance().getFosse().defausserCarte(joueurVise.getMain().getMainCarte().get(numCarteRecuperer.getFirst()));
			joueurVise.getMain().getMainCarte().remove(numCarteRecuperer.getFirst());
		}
		if (!joueurAttack.getStrategie().vraiJoueur()) {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, PlateauJeu.getInstance());
		}
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
