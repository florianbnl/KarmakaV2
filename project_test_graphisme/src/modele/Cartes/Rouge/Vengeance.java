package modele.Cartes.Rouge;

import modele.*;

public class Vengeance extends Carte {
	
	private String nomPouvoir;
	
	public Vengeance() {
		super(ValeurCarteEnum.TROIS, "Vengeance", CouleurCarteEnum.Rouge, "Défaussez l'Oeuvre Exposée d'un rival.");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (!joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			joueurVise.getOeuvres().getOeuvresCarte().removeFirst();	
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);

		
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
