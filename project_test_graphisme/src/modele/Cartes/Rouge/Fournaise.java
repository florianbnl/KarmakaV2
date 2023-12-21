package modele.Cartes.Rouge;

import modele.*;

public class Fournaise extends Carte {
	
	
	public Fournaise() {
		super(ValeurCarteEnum.DEUX, "Fournaise", CouleurCarteEnum.Rouge, "Défaussez les 2 premières cartes de la Vie Future d'un rival");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		for (int i = 0; i<2 && joueurVise.getVieFuture().getVieFutureCarte().size() > 0; i++) {
			joueurVise.getVieFuture().removeFirst();
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		
		
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
