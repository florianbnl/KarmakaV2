package modele.Cartes.Rouge;

import java.util.ArrayList;
import java.util.Iterator;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

/**
 * Classe Roulette spécialise Carte
 */
public class Roulette extends Carte {
	
	/**
	 * Constructeur classe Roulette
	 */
	public Roulette() {
		super(ValeurCarteEnum.DEUX, "Roulette", CouleurCarteEnum.Rouge, "Défaussez jusqu'à 2 cartes de votre Main. \nVous pouvez ensuite puiser à la Source autant de carte(s) + 1.");
	}

	
	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Roulette
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
			nomBouton.add("Defausser carte");
			nomBouton.add("Arreter");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getMain().getMainCarte(), nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> nbCarte = joueurAttack.getStrategie().choixBot(3, 1);
			nbCarte = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), nbCarte.getFirst());
			ArrayList<Carte> carte = new ArrayList<Carte>();
			Iterator<Integer> it = nbCarte.iterator();
			while (it.hasNext()) {
				carte.add(joueurAttack.getMain().getMainCarte().get(it.next()));
			}
			Iterator<Carte> itCarte = carte.iterator();
			while (itCarte.hasNext()) {
				Carte c = itCarte.next();
				joueurAttack.getMain().getMainCarte().remove(c);
				plateauJeu.getFosse().defausserCarte(c);
			}
			nbCarte = joueurAttack.getStrategie().choixBot(carte.size(), 1);
			for (int i = 0; i<nbCarte.getFirst(); i++) {
				joueurAttack.getMain().getMainCarte().add(plateauJeu.getSource().distribuerUneCarte());
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
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
		joueurAttack.getMain().getMainCarte().remove(c);
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
		if (c instanceof CarteCache) {
			for (int i = 0; i < Integer.parseInt(c.getNomCarte()); i++) {
			joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		}
		}
		
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
