package modele.Cartes;

import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

public class CarteCache extends Carte {

	public CarteCache(ValeurCarteEnum valeurCarte, String nomCarte, CouleurCarteEnum couleur, String nomPouvoir) {
		super(valeurCarte, nomCarte, couleur, nomPouvoir);
		// TODO Auto-generated constructor stub
	}
	
	public CarteCache(String numCarte) {
		super(null, "CarteCache" , null, numCarte);
	}

	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		// TODO Auto-generated method stub
		
	}

}
