package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

public class Recyclage extends Carte {
	
	
	public Recyclage() {
		super(ValeurCarteEnum.UN, "Recyclage", CouleurCarteEnum.Vert, "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur() && !plateauJeu.getFosse().isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir Carte");
			
			ArrayList<Carte> afficherCarte = new ArrayList<Carte>();
			for (int i = 0; i<3 && PlateauJeu.getInstance().getFosse().getFosseCarte().size() > i; i++) {
				afficherCarte.add(PlateauJeu.getInstance().getFosse().getFosseCarte().get(i));
			}
			
			VueCapacite vc = new VueCapacite(this, afficherCarte, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else if (!plateauJeu.getFosse().isEmpty()) {
			ArrayList<Integer> numCarteRecuperer;
			if (plateauJeu.getFosse().getFosseCarte().size() < 3) {
				numCarteRecuperer = joueurAttack.getStrategie().choixBot(plateauJeu.getFosse().getFosseCarte().size(), 1);
			} else {
				numCarteRecuperer = joueurAttack.getStrategie().choixBot(3, 1);
			}
			this.vueBouton1(plateauJeu.getFosse().getFosseCarte().get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		PlateauJeu.getInstance().getFosse().getFosseCarte().remove(c);
		joueurAttack.getVieFuture().getVieFutureCarte().add(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
}

