package modele.Cartes.Rouge;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

public class Crise extends Carte {
	
	
	public Crise() {
		super(ValeurCarteEnum.DEUX, "Crise", CouleurCarteEnum.Rouge, "Le rival de votre choix défausse une de ses Oeuvres");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurVise.getStrategie().vraiJoueur() && !joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			
			VueCapacite vc = new VueCapacite(this, joueurVise.getOeuvres().getOeuvresCarte(), nomBouton, null, 1, joueurVise.getCouleurText(), joueurAttack);
		} else if (!joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurVise.getStrategie().choixBot(joueurVise.getOeuvres().getOeuvresCarte().size(), 1);
			this.vueBouton1(joueurVise.getOeuvres().getOeuvresCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2).getOeuvres().getOeuvresCarte().remove(c);
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
