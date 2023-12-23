package modele;

import java.io.Serializable;
import java.util.*;

/**
 * Classe Oeuvres implémente Serializable
 */
public class Oeuvres implements Serializable {
	
	private ArrayList<Carte> oeuvresCarte;
	
	/**
	 * Constructeur classe Oeuvres
	 */
	public Oeuvres() {
		this.oeuvresCarte = new ArrayList<Carte>();
	}

	/**
	 * Méthode enleverCarte
	 * Permet d'enlever une carte de l'oeuvre
	 * 
	 * @param carte
	 * type Carte: la carte à enlever
	 */
	public void enleverCarte(Carte carte){
		this.oeuvresCarte.remove(carte);
	}

	/**
	 * Méthode enleverPlusieursCartes
	 * Permet d'enlever toutes les cartes de l'oeuvre
	 */
	public void enleverPlusieursCartes(){
		this.oeuvresCarte.removeAll(this.oeuvresCarte);
	}
	
	/**
	 * Méthode ajouterCarte
	 * Permet d'ajouter une carte de l'oeuvre
	 * 
	 * @param carte
	 * type Carte: la carte à ajouter
	 */
	public void ajouterCarte(Carte carte) {
		this.oeuvresCarte.add(carte);
	}

	/**
	 * Méthode ajouterCarteDebut
	 * Permet d'ajouter une carte au début de l'oeuvre
	 * 
	 * @param carte
	 * type Carte: la carte à ajouter
	 */
	public void ajouterCarteDebut(Carte carte) {
		this.oeuvresCarte.add(0, carte);
	}

	/**
	 * Méthode compterValeurCarte
	 * Permet d'additionner les points des couleurs et renvoie la valeur la plus élevé
	 */
	public  int compterValeurCarte(){
		ArrayList<Integer> valeurParCouleur = new ArrayList<Integer>();
		valeurParCouleur.add(0);
		valeurParCouleur.add(0);
		valeurParCouleur.add(0);
		for(Carte carte: this.oeuvresCarte){
			if ((carte.getCouleur() == CouleurCarteEnum.Bleu) || (carte.getCouleur() == CouleurCarteEnum.Mosaique)){
				valeurParCouleur.set(0, carte.getValeurCarte().getValeurCarteEnum() + valeurParCouleur.get(0));
			} 
			else if ((carte.getCouleur() == CouleurCarteEnum.Rouge) || (carte.getCouleur() == CouleurCarteEnum.Mosaique)){
				valeurParCouleur.set(1, carte.getValeurCarte().getValeurCarteEnum() + valeurParCouleur.get(1));
			} 
			else if ((carte.getCouleur() == CouleurCarteEnum.Vert) || (carte.getCouleur() == CouleurCarteEnum.Mosaique)){
				valeurParCouleur.set(2, carte.getValeurCarte().getValeurCarteEnum() + valeurParCouleur.get(2));
			}
		}
			Collections.sort(valeurParCouleur);
			return valeurParCouleur.get(valeurParCouleur.size()-1);
		
	}

	/**
	 * Méthode getOeuvresCarte
	 * getter de l'attribut oeuvresCarte
	 */
	public ArrayList<Carte> getOeuvresCarte(){
		return this.oeuvresCarte;
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		String oeuvreString = "";
		Iterator<Carte> it = this.oeuvresCarte.iterator();
		while (it.hasNext()){
			oeuvreString += it.next().getNomCarte() + ";";
		}
		return oeuvreString;
	}
}
