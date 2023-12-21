package modele.Cartes.Rouge;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

public class DernierSouffle extends Carte {
	
	
	public DernierSouffle() {
		super(ValeurCarteEnum.UN, "Dernier Souffle", CouleurCarteEnum.Rouge, "Le joueur de votre choix défausse une carte de sa Main.");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add(joueurAttack.getNomJoueur() + " défausse un carte");
			nomBouton.add(joueurVise.getNomJoueur() + " défausse une carte");
			
			ArrayList<Carte> carteAffiche = new ArrayList<Carte>();
			carteAffiche.add(new CarteCache("1"));
			
			VueCapacite vc = new VueCapacite(this, carteAffiche, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numJoueur = joueurAttack.getStrategie().choixBot(2, 1);
			switch (numJoueur.getFirst()) {
			case 0: {
				this.vueBouton1(this, joueurAttack);
				break;
			}
			case 1: {
				this.vueBouton2(this, joueurAttack);
				break;
			}
			}
		}
		
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Defausser Carte");
			super.vueBouton1(c, joueurAttack);
			VueCapacite vc = new VueCapacite(joueurAttack, joueurAttack.getMain().getMainCarte(), nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
			PlateauJeu.getInstance().getFosse().defausserCarte(joueurAttack.getMain().getMainCarte().get(numCarteRecuperer.getFirst()));
			joueurAttack.getMain().getMainCarte().remove(numCarteRecuperer.getFirst());
			PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack)+ 1) % 2).getStrategie().seFaireAttaquer(this, joueurAttack, PlateauJeu.getInstance());
		}
		
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		Joueur joueurVise = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
		
		if (joueurVise.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Defausser Carte");
			super.vueBouton2(c, joueurAttack);
			VueCapacite vc = new VueCapacite(joueurVise, joueurVise.getMain().getMainCarte(), nomBouton, null, 1, joueurVise.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numCarteRecuperer = joueurVise.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), 1);
			PlateauJeu.getInstance().getFosse().defausserCarte(joueurVise.getMain().getMainCarte().get(numCarteRecuperer.getFirst()));
			joueurVise.getMain().getMainCarte().remove(numCarteRecuperer.getFirst());
		}
		if (!joueurAttack.getStrategie().vraiJoueur()) {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, PlateauJeu.getInstance());
		}
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
