package modele.Cartes.Mosaique;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

public class Incarnation extends Carte {
	
	
	public Incarnation() {
		super(ValeurCarteEnum.UN, "Incarnation", CouleurCarteEnum.Mosaique, "Choisissez une de vos Oeuvres. \nCopiez son pouvoir");
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur() && !joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getOeuvres().getOeuvresCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else if (!joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getOeuvres().getOeuvresCarte().size(), 1);
			this.vueBouton1(joueurAttack.getOeuvres().getOeuvresCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} 
		else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		c.capacite(PlateauJeu.getInstance(), joueurAttack, PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1)% 2));
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
	}
}
