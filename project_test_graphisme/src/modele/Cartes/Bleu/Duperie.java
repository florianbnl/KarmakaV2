package modele.Cartes.Bleu;

import modele.*;
import modele.Cartes.CarteCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Vue.VueCapacite;



public class Duperie extends Carte {
	
	
	public Duperie() {
		super(ValeurCarteEnum.TROIS, "Duperie", CouleurCarteEnum.Bleu, "Regardez 3 cartes de la Main d'un rival. \nAjoutez-en une à votre Main");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
	
		ArrayList<Carte> carteAfficher = new ArrayList<Carte>();
		if (joueurVise.getMain().getMainCarte().size() < 3) {
			carteAfficher = joueurVise.getMain().getMainCarte();
		}
		else {
			ArrayList<Integer> numCarte = new ArrayList<Integer>();
			Random rd = new Random();
			numCarte.add(rd.nextInt(joueurVise.getMain().getMainCarte().size()));
			int nombreCarteTire = 1;
			while (nombreCarteTire<3) {
				int num = rd.nextInt(joueurVise.getMain().getMainCarte().size());
				if (!numCarte.contains(num)){
					numCarte.add(num);
					nombreCarteTire++;
					carteAfficher.add(joueurVise.getMain().getMainCarte().get(num));
				}
				
			}
		}
		
		if (joueurAttack.getStrategie().vraiJoueur() && !carteAfficher.isEmpty()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Choisir une carte a retourner");
			VueCapacite vc = new VueCapacite(this, carteAfficher, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		} else if (!carteAfficher.isEmpty()){
			ArrayList<Integer> numCarteRecuperer = joueurAttack.getStrategie().choixBot(carteAfficher.size(), 1);
			this.vueBouton1(carteAfficher.get(numCarteRecuperer.getFirst()), joueurAttack);
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		} else {
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}

	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2).getMain().getMainCarte().remove(c);
		joueurAttack.getMain().getMainCarte().add(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	

}


