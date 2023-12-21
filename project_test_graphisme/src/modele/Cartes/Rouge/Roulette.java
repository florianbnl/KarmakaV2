package modele.Cartes.Rouge;

import java.util.ArrayList;
import java.util.Iterator;

import Vue.VueCapacite;
import modele.*;
import modele.Cartes.CarteCache;

public class Roulette extends Carte {
	
	
	public Roulette() {
		super(ValeurCarteEnum.DEUX, "Roulette", CouleurCarteEnum.Rouge, "Défaussez jusqu'à 2 cartes de votre Main. \nVous pouvez ensuite puiser à la Source autant de carte(s) + 1.");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Defausser carte");
			nomBouton.add("Arreter");
			VueCapacite vc = new VueCapacite(this, joueurAttack.getMain().getMainCarte(), nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> nbCarte = joueurAttack.getStrategie().choixBot(3, 1);
			nbCarte = joueurAttack.getStrategie().choixBot(joueurAttack.getMain().getMainCarte().size(), nbCarte.getFirst());
			ArrayList<Carte> carte = new ArrayList<Carte>();
			Iterator<Integer> it = nbCarte.iterator();
			while (it.hasNext()) {
				carte.add(joueurAttack.getMain().getMainCarte().get(it.next()));
			}
			Iterator<Carte> itCarte = carte.iterator();
			while (itCarte.hasNext()) {
				Carte c = itCarte.next();
				joueurAttack.getMain().getMainCarte().remove(c);
				plateauJeu.getFosse().defausserCarte(c);
			}
			nbCarte = joueurAttack.getStrategie().choixBot(carte.size(), 1);
			for (int i = 0; i<nbCarte.getFirst(); i++) {
				joueurAttack.getMain().getMainCarte().add(plateauJeu.getSource().distribuerUneCarte());
			}
		}
		
		
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		PlateauJeu.getInstance().getFosse().defausserCarte(c);
		joueurAttack.getMain().getMainCarte().remove(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		if (c instanceof CarteCache) {
			for (int i = 0; i < Integer.parseInt(c.getNomCarte()); i++) {
			joueurAttack.getMain().getMainCarte().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		}
		}
		
		super.vueBouton1(c, joueurAttack);
	}
	
	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}

}
