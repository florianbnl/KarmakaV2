package modele.Cartes.Mosaique;

import modele.*;

public class Mimetisme extends Carte {
	
	private String nomPouvoir;
	
	public Mimetisme() {
		super(ValeurCarteEnum.UN, "Mimétisme", CouleurCarteEnum.Mosaique, "Choisissez un Rival. \nCopiez le pouvoir de son Oeuvre Exposée.");
	}

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (!joueurVise.getOeuvres().getOeuvresCarte().isEmpty()) {
			joueurVise.getOeuvres().getOeuvresCarte().getFirst().capacite(plateauJeu, joueurAttack, joueurVise);
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		
	}

}
