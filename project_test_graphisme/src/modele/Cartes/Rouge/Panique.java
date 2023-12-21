package modele.Cartes.Rouge;

import modele.*;

public class Panique extends Carte {
	
	private String nomPouvoir;
	
	public Panique() {
		super(ValeurCarteEnum.UN, "Panique", CouleurCarteEnum.Rouge, "Défaussez la première carte de la Pile d'un joueur. \nVous pouvez ensuite jouer une autre carte.");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (!joueurVise.getPile().getPileCartes().isEmpty()) {
			plateauJeu.getFosse().defausserCarte(joueurVise.getPile().getPileCartes().removeFirst());
		}

		
	}
	
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		joueurAttack.getDiffuseur().firePropertyChange("joueurContinue", this, joueurAttack);
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
