package modele.Strategie;

import java.util.ArrayList;
import java.util.Random;

import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;

public class Defensive extends Strategie {

	public Defensive() {
	}

	public boolean vraiJoueur(){
		return false;
	}

	public void strategie(Joueur joueur) {
		Random random = new Random();
		joueur.getStrategie().setMsg("");
		int nbAleatoire;
		//Si la pile est vide, le bot doit jouer obligatoirement une carte sinon il il y a 1/10 que le joueur ne joue pas
		if (joueur.getPile().isEmpty()){
			nbAleatoire = 0;
		} else {
			nbAleatoire = random.nextInt(10);
		}
		if (nbAleatoire == 9){
			joueur.getStrategie().setMsg(joueur.getNomJoueur() + " ne joue pas de carte");
		} 
		else {
			nbAleatoire = random.nextInt(joueur.getMain().getMainCarte().size());
			int jouerCartePour = random.nextInt(5);
			switch (jouerCartePour) {
				case 0, 1:
					joueur.getStrategie().setMsg(joueur.getNomJoueur() + " utilise la carte " + joueur.getMain().getMainCarte().get(nbAleatoire).getNomCarte() + " pour ses points");
					System.out.println(joueur.getStrategie().getMsg()+ "dans strat");
					joueur.utiliserCarte(nbAleatoire, jouerCartePour);
					break;
				case 2:
					joueur.getStrategie().setMsg(joueur.getNomJoueur() + " utilise " + joueur.getMain().getMainCarte().get(nbAleatoire).getNomCarte() + " pour son pouvoir");
					System.out.println(joueur.getStrategie().getMsg() + "dans strat");
					joueur.utiliserCarte(nbAleatoire, jouerCartePour);
					break;
				case 3, 4:
					joueur.getStrategie().setMsg(joueur.getNomJoueur() + " met une carte pour sa vie future");
					System.out.println(joueur.getStrategie().getMsg()+ "dans strat");
					joueur.utiliserCarte(nbAleatoire, jouerCartePour);
					break;
				default:
					break;
			}
		}
	}

	public boolean choixBot() {
		Random aleatoire = new Random();
		boolean ON = aleatoire.nextBoolean();
		return ON;
	}

	public void seFaireAttaquer(Carte carte, Joueur joueurVise, PlateauJeu plateauJeu) {
		ArrayList<Integer> On = super.choixBot(2, 1);
		switch (On.getFirst()) {
		case 0: {
			joueurVise.getVieFuture().ajouterCarte(carte);
			break;
		}
		case 1: {
			plateauJeu.getFosse().defausserCarte(carte);
		}
		}
		
	}


}
