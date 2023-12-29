package modele.Strategie;

import java.util.ArrayList;
import java.util.Iterator;

import Controleur.ControleurCapacite;
import Vue.VueCapacite;
import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;
import modele.Terminal;

/**
 * Classe JoueurReel spécialise Strategie
 */
public class JoueurReel extends Strategie{

	/**
	 * Constructeur classe JoueurReel
	 */
	public JoueurReel() {
	}

	/**
	 * Méthode vraiJoueur
	 * Renvoie que le joueur est réel
	 */
	public boolean vraiJoueur(){
		return true;
	}

	/**
	 * Méthode strategie
	 * Permet de lancer la strategie du joueur
	 * 
	 * @param joueur
	 * type Joueur: le joueur qui réalise la stratégie
	 */
	public void strategie(Joueur joueur) {
		/*Terminal terminal = new Terminal();
				Iterator<Carte> it = joueur.getMain().getMainCarte().iterator();
				int numCarte = 1;
				while (it.hasNext()){
					System.out.println(joueur.getCouleurText() + "Carte " + numCarte + ":\n" + it.next() + Joueur.RESET);
					numCarte++;
				}
				int num;
				if (!joueur.getPile().isEmpty()){
					num = Integer.valueOf(terminal.poserQuestion(joueur.getCouleurText() + "Choisie le numéro de la carte que tu veux jouer ou mets 0 pour ne pas jouer de carte mais que si t'as pile est encore pleine" + Joueur.RESET));
				}
				else {
					num = Integer.valueOf(terminal.poserQuestion(joueur.getCouleurText() + "Choisie le numéro de la carte que tu veux jouer. T'as Pile est vide" + Joueur.RESET));
				}
				if (num != 0){
					num--;
					System.out.println(joueur.getCouleurText() + "Tu as choisie la carte :" + joueur.getMain().getMainCarte().get(num) + Joueur.RESET);
					String utilisation = terminal.poserQuestion(joueur.getCouleurText() + "Tu veux utiliser la carte pour : \n1-Ses Points; \n2-Son pouvoir; \n3-Votre future; \n" + Joueur.RESET);
					switch (utilisation) {
						case "1":
							joueur.getMain().getMainCarte().get(num).jouerPoints(joueur);
							break;
						case "2":
							joueur.getMain().getMainCarte().get(num).jouerPouvoirs(joueur.getPlateauJeu(), joueur, joueur.getPlateauJeu().getJoueurs().get((joueur.getPlateauJeu().getJoueurs().indexOf(joueur) + 1) % 2));
							break;
						case "3":
							joueur.getMain().getMainCarte().get(num).jouerFuture(joueur);
							break;
						default:
							break;
				}
			}*/
	}

	/**
	 * Méthode seFaireAttaquer
	 * Permet de réaliser le choix lors du coût Karmique
	 * 
	 * @param carte
	 * type Carte: la carte que le joueur rival à jouer pour son pouvoir
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public void seFaireAttaquer(Carte carte, Joueur joueurVise, PlateauJeu plateauJeu) {
		ArrayList<Carte> vueCarte = new ArrayList<Carte>();
		vueCarte.add(carte);
		ArrayList<String> nomBouton = new ArrayList<String>();
		nomBouton.add("Pour la fosse");
		nomBouton.add("Pour la vie Future");
		System.out.println("On appelle seFaireAttaquer sur " + joueurVise.getNomJoueur() + " avec la carte " + carte.getNomCarte());
		VueCapacite vc = new VueCapacite(joueurVise, vueCarte, nomBouton, null, 1, joueurVise.getCouleurText(), plateauJeu.getJoueurs().get((plateauJeu.getJoueurs().indexOf(joueurVise) + 1) % 2));
		
	}
}
