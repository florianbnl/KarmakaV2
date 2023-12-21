package modele.Cartes.Bleu;

import modele.*;

import java.util.ArrayList;
import java.util.Iterator;

import Vue.VueCapacite;

public class Destinee extends Carte {
	
	
	public Destinee() {
		super(ValeurCarteEnum.DEUX, "Destinée", CouleurCarteEnum.Bleu, "Regardez les 3 premières cartes de la Source. \nAjoutez-en jusqu'à 2 à votre Vie Future. \n Replacez le reste dans l'ordre souhaité.");
	}


	@Override
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		ArrayList<Carte> carteSource = new ArrayList<Carte>();
		for(int i =0; i<3 && i<plateauJeu.getSource().getSourceCarte().size(); i++) {
			carteSource.add(plateauJeu.getSource().distribuerUneCarte());
		}
		if (joueurAttack.getStrategie().vraiJoueur()) {
			
			ArrayList<String> nomBouton = new ArrayList<String>();
			nomBouton.add("Vie Future");
			nomBouton.add("Mettre toute les cartes <br> restantes dans la Source");
			
			VueCapacite vc = new VueCapacite(this, carteSource, nomBouton, null, 2, joueurAttack.getCouleurText(), joueurAttack);
		} else {
			ArrayList<Integer> nbCarteSource = joueurAttack.getStrategie().choixBot(3, 1);
			switch (nbCarteSource.getFirst()) {
			case 0: {
				nbCarteSource = joueurAttack.getStrategie().choixBot(3, 3);
				Iterator<Integer> it = nbCarteSource.iterator();
				while (it.hasNext()) {
					plateauJeu.getSource().getSourceCarte().addFirst(carteSource.get(it.next()));
				}
				break;
			}
			
			case 1:{
				nbCarteSource = joueurAttack.getStrategie().choixBot(3, 3);
				joueurAttack.getVieFuture().getVieFutureCarte().add(carteSource.get(nbCarteSource.removeFirst()));
				plateauJeu.getSource().getSourceCarte().addFirst(carteSource.get(nbCarteSource.removeFirst()));
				plateauJeu.getSource().getSourceCarte().addFirst(carteSource.get(nbCarteSource.removeFirst()));
				break;
			}
			case 2:{
				nbCarteSource = joueurAttack.getStrategie().choixBot(3, 3);
				joueurAttack.getVieFuture().getVieFutureCarte().add(carteSource.get(nbCarteSource.removeFirst()));
				joueurAttack.getVieFuture().getVieFutureCarte().add(carteSource.get(nbCarteSource.removeFirst()));
				plateauJeu.getSource().getSourceCarte().addFirst(carteSource.get(nbCarteSource.removeFirst()));
				break;
			}
			}
			joueurVise.getStrategie().seFaireAttaquer(this, joueurVise, plateauJeu);
		}
		
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		joueurAttack.getVieFuture().ajouterCarte(c);
		super.vueBouton1(c, joueurAttack);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		PlateauJeu.getInstance().getSource().ajouterCarteDessus(c);
		super.vueBouton2(c, joueurAttack);
	}

	public String toString() {
		return super.toString() + "\nPouvoir : " + this.getNomPouvoir();
	}
	
	

}


