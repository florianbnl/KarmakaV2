package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;
import modele.Cartes.CarteCache;

/**
 * Classe Longevite spécialise Carte
 */
public class Longevite extends Carte {
	
	/**
	 * Constructeur Longevite
	 */
	public Longevite() {
		super(ValeurCarteEnum.DEUX, "Longévité", CouleurCarteEnum.Vert, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur");
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte Longévité
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
			nomBouton.add("Pour toi");
			nomBouton.add("Pour ton rival");
			
			ArrayList<Carte> afficherCarte = new ArrayList<Carte>();
			afficherCarte.add(new CarteCache("1"));
			VueCapacite vc = new VueCapacite(this, afficherCarte, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numJoueur = joueurAttack.getStrategie().choixBot(2, 1);
			switch (numJoueur.getFirst()) {
			case 0: {
				this.vueBouton1(null, joueurAttack);
			}
			case 1: {
				this.vueBouton2(null, joueurAttack);
			}
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
		joueurAttack.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
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
		Joueur joueurRival = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
		joueurRival.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurRival.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		
	}

	/**
	 * Méthode toString 
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	


}

