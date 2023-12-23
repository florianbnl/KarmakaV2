package modele;

import java.io.Serializable;

/**
 * Classe AnneauxKarmique
 */
public class AnneauxKarmiques implements Serializable {
	
	private static int anneauxSurPlateau = 12;
	private int anneauxJoueur;

	/**
	 * Constructeur classe AnneauxKarmique
	 */
	public AnneauxKarmiques(){
		this.anneauxJoueur = 0;
	}
	
	/**
	 * Méthode ajouterAnneauxSurPlateau
	 * Permet d'ajouter un anneau sur le plateau
	 * 
	 * @param nbAnneaux
	 * type Integer : le nombre d'anneaux a ajouter sur le plateau
	 */
	public static void ajouterAnneauxSurPlateau(int nbAnneaux) {
		anneauxSurPlateau += nbAnneaux;
	}
	
	/**
	 * Méthode enleverAnneauxSurPlateau
	 * Permet d'enlever un anneau sur le plateau s'il en reste assez
	 */
	public static boolean enleverAnneauxSurPlateau() {
		if (anneauxSurPlateau > 0) {
			anneauxSurPlateau -= 1;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode enleverAnneauxSurPlateau
	 * Permet d'enlever nbAnneaux sur le plateau s'il en reste assez
	 * 
	 * @param nbAnneaux
	 * type Integer : le nombre d'anneaux à enlever
	 */
	public static boolean enleverAnneauxSurPlateau(int nbAnneaux){
		if (anneauxSurPlateau >= nbAnneaux){
			anneauxSurPlateau -= nbAnneaux;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Méthode ajouterAnneauxJoueur
	 * Permet d'ajouter un anneau à un joueur s'il en reste sur le plateau
	 */
	public void ajouterAnneauxJoueur() {
		if (enleverAnneauxSurPlateau()) {
			System.out.println("Tu reçois un anneaux karmique");
			this.anneauxJoueur ++;
		} else {
			System.out.println("Il n'y a plus d'anneaux karmique");
		}
	}

	/**
	 * Méthode ajouterAnneauxJoueur
	 * Permet d'ajouter un ou plusieurs anneaux à un jouer s'il en reste sur le plateau
	 * 
	 * @param nbAnneaux
	 * type Integer: le nombre d'anneau à ajouter au joueur
	 */
	public void ajouterAnneauxJoueur(int nbAnneaux){
		if (AnneauxKarmiques.enleverAnneauxSurPlateau(nbAnneaux)){
			this.anneauxJoueur += nbAnneaux;
		}
		else {
			while (anneauxSurPlateau != 0 && nbAnneaux != 0) {
				if (this.enleverAnneauxSurPlateau()) {
					this.anneauxJoueur += 1;
					nbAnneaux --;
				}
				
			}
		}
	}
	
	/**
	 * Méthode enleverAnneauxJoueur
	 * Permet d'enlever nbAnneaux anneaux au joueur
	 * 
	 * @param nbAnneaux
	 * type Integer: le nombre d'anneaux à enlever au joueur
	 */
	public void enleverAnneauxJoueur(int nbAnneaux) {
		if (this.anneauxJoueur >= nbAnneaux) {
			this.anneauxJoueur -= nbAnneaux;
			ajouterAnneauxSurPlateau(nbAnneaux);			
		}
	}

	/**
	 * Méthode getAnneauxKarmiques
	 * getter de l'attribue anneauxJoueur
	 */
	public int getAnneauxKarmiques(){
		return this.anneauxJoueur;
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		return String.valueOf(this.anneauxJoueur);
	}
}
