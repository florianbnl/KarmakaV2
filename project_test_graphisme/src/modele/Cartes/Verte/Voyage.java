package modele.Cartes.Verte;

import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

public class Voyage extends Carte {
	
	
	public Voyage() {
		super(ValeurCarteEnum.TROIS, "Voyage", CouleurCarteEnum.Vert, "Puisez 3 cartes à la Source. \nVous pouvez ensuite joueur une autre carte.");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		
	}
	
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		joueurAttack.getDiffuseur().firePropertyChange("joueurContinue", this, joueurAttack);
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}

