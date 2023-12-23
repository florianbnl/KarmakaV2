package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe Fosse implémente Serializable
 */
public class Fosse implements Serializable {
	
	private ArrayList<Carte> fosseCarte;

	/**
	 * Constructeur classe Fosse
	 */
	public Fosse() {
		this.fosseCarte = new ArrayList<Carte>();
	}
	
	/**
	 * Méthode enleverCartes
	 * Permet d'enlever toute les cartes de la fosse
	 */
	public ArrayList<Carte> enleverCartes(){
		ArrayList<Carte> tamps = this.fosseCarte;
		this.fosseCarte.removeAll(fosseCarte);
		return tamps;
	}

	/**
	 * Méthode defausserPlusieursCartes
	 * Permet de rajouter plusieurs cartes dans la fosse
	 * 
	 * @param carte
	 * type ArrayList<Carte>: les cartes à mettre à la fosse
	 */
	public void defausserPlusieursCartes(ArrayList<Carte> carte) {
		this.fosseCarte.addAll(carte);
	}

	/**
	 * Méthode defausserCarte
	 * Permet de rajouter une carte dans la fosse
	 * 
	 * @param carte
	 * type Carte: la carte à mettre à la fosse
	 */
	public void defausserCarte(Carte carte){
		this.fosseCarte.add(carte);
	}
	
	/**
	 * Méthode isEmpty
	 * Permet de connaitre si la fosse est vide
	 */
	public boolean isEmpty() {
		return this.fosseCarte.isEmpty();
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		String fosseString = "Fosse\n";
		Iterator<Carte> it = this.fosseCarte.iterator();
		while (it.hasNext()){
			fosseString += it.next().getNomCarte() + ";";
		}
		return fosseString;
	}
	
	/**
	 * Méthode getFosseCarte
	 * getter de l'attribut fosseCarte
	 */
	public ArrayList<Carte> getFosseCarte() {
		return fosseCarte;
	}

	
	/**
	 * Méthode setFosseCarte
	 * setter de l'attribut fosseCarte
	 * 
	 * @param fosseCarte
	 * type ArrayList<Carte>:
	 */
	public void setFosseCarte(ArrayList<Carte> fosseCarte) {
		this.fosseCarte = fosseCarte;
	}

}
