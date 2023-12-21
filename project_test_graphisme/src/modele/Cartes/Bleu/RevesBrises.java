package modele.Cartes.Bleu;

import modele.*;

public class RevesBrises extends Carte {
	
	
	public RevesBrises() {
		super(ValeurCarteEnum.DEUX, "Rêves Brisés", CouleurCarteEnum.Bleu, "Placez la première carte de la Vie Future d'un rival sur la vôtre");
	}

	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (!joueurVise.getVieFuture().getVieFutureCarte().isEmpty()) {
			joueurAttack.getVieFuture().ajouterCarte(joueurVise.getVieFuture().getFirst());
			joueurVise.getVieFuture().removeFirst();
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
	}

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}


