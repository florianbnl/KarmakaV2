package modele.Cartes.Bleu;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

public class CoupDOeil extends Carte {
	
	
	public CoupDOeil() {
		super(ValeurCarteEnum.UN  , "Coup d'Oeil", CouleurCarteEnum.Bleu, "Regardez la Main d'un rival. \nVous pouvez ensuite jouer une autre carte.");
	}

	
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise){
		if(joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Continuer");
			VueCapacite vc = new VueCapacite(this, joueurVise.getMain().getMainCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
	}
	
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getDiffuseur().firePropertyChange("joueurContinue", this, joueurAttack);
	}
	

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	

}


