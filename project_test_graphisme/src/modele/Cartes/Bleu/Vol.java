package modele.Cartes.Bleu;

import modele.*;

public class Vol extends Carte {
	
	
	public Vol() {
		super(ValeurCarteEnum.TROIS, "Vol", CouleurCarteEnum.Bleu, "Placez dans votre Main l'Oeuvre Exposée d'un rival");
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (!joueurAttack.getOeuvres().getOeuvresCarte().isEmpty()) {
			joueurAttack.getMain().ajouterCarte(joueurVise.getOeuvres().getOeuvresCarte().removeFirst());
		}
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		
	}

}


