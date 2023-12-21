package modele.Cartes.Verte;

import java.util.ArrayList;

import Vue.VueCapacite;
import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;
import modele.Cartes.CarteCache;

public class Longevite extends Carte {
	
	
	public Longevite() {
		super(ValeurCarteEnum.DEUX, "Longévité", CouleurCarteEnum.Vert, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur");
	}

	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		if (joueurAttack.getStrategie().vraiJoueur()) {
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Pour toi");
			nomBouton.add("Pour ton rival");
			
			ArrayList<Carte> afficherCarte = new ArrayList<Carte>();
			afficherCarte.add(new CarteCache("1"));
			VueCapacite vc = new VueCapacite(this, afficherCarte, nomBouton, null, 1, joueurAttack.getCouleurText(), joueurAttack);
		}
		else {
			ArrayList<Integer> numJoueur = joueurAttack.getStrategie().choixBot(2, 1);
			switch (numJoueur.getFirst()) {
			case 0: {
				this.vueBouton1(null, joueurAttack);
			}
			case 1: {
				this.vueBouton2(null, joueurAttack);
			}
			}
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		joueurAttack.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurAttack.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		Joueur joueurRival = PlateauJeu.getInstance().getJoueurs().get((PlateauJeu.getInstance().getJoueurs().indexOf(joueurAttack) + 1) % 2);
		joueurRival.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		joueurRival.getPile().getPileCartes().add(PlateauJeu.getInstance().getSource().distribuerUneCarte());
		
	}

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	


}

