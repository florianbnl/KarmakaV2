package modele.Cartes.Bleu;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;

public class Transmigration extends Carte {
	
	
	public Transmigration() {
		super(ValeurCarteEnum.UN, "Transmigration", CouleurCarteEnum.Bleu, "Placez dans votre Main n'importe quelle carte de votre Vie Future.");
	}

	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur() && !joueurAttack.getVieFuture().getVieFutureCarte().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getVieFuture().getVieFutureCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else if (!joueurAttack.getVieFuture().getVieFutureCarte().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getVieFuture().getVieFutureCarte().size(), 1);
			this.vueBouton1(joueurAttack.getVieFuture().getVieFutureCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}

	}

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

	public void vueBouton1(Carte c, Joueur joueurAttack) {
		joueurAttack.getMain().getMainCarte().add(c);
		joueurAttack.getVieFuture().getVieFutureCarte().remove(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
	}

}


