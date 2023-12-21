package modele;

import java.io.Serializable;

public class AnneauxKarmiques implements Serializable {
	
	private static int anneauxSurPlateau = 12;
	private int anneauxJoueur;

	AnneauxKarmiques(){
		this.anneauxJoueur = 0;
	}
	
	public static void ajouterAnneauxSurPlateau(int nbAnneaux) {
		anneauxSurPlateau += nbAnneaux;
	}
	
	public static boolean enleverAnneauxSurPlateau() {
		if (anneauxSurPlateau > 0) {
			anneauxSurPlateau -= 1;
			return true;
		} else {
			return false;
		}
	}

	public static boolean enleverAnneauxSurPlateau(int nbAnneaux){
		if (anneauxSurPlateau >= nbAnneaux){
			anneauxSurPlateau -= nbAnneaux;
			return true;
		} else {
			return false;
		}
	}
	
	public void ajouterAnneauxJoueur() {
		if (enleverAnneauxSurPlateau()) {
			System.out.println("Tu reçois un anneaux karmique");
			this.anneauxJoueur ++;
		} else {
			System.out.println("Il n'y a plus d'anneaux karmique");
		}
	}

	public void ajouterAnneauxJoueur(int nbAnneaux){
		if (AnneauxKarmiques.enleverAnneauxSurPlateau(nbAnneaux)){
			this.anneauxJoueur += nbAnneaux;
		}
	}
	
	public void enleverAnneauxJoueur(int nbAnneaux) {
		if (this.anneauxJoueur >= nbAnneaux) {
			this.anneauxJoueur -= nbAnneaux;
			ajouterAnneauxSurPlateau(nbAnneaux);			
		}
	}

	public int getAnneauxKarmiques(){
		return this.anneauxJoueur;
	}

	public String toString(){
		return String.valueOf(this.anneauxJoueur);
	}
}
