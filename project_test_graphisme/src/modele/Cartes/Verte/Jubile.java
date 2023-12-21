package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

public class Jubile extends Carte {
	
	
	public Jubile() {
		super(ValeurCarteEnum.TROIS, "Jubilé", CouleurCarteEnum.Vert, "Placez jusqu'à 2 cartes de votre Mais sur vos Oeuvres.");
	}

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
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		joueurAttack.getMain().getMainCarte().remove(c);
		joueurAttack.getOeuvres().getOeuvresCarte().addFirst(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}

