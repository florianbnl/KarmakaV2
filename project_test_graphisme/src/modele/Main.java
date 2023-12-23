package modele;

import java.io.Serializable;
import java.util.*;

/**
 * Classe Main implémente Serializable
 */
public class Main implements Serializable{
	
	private ArrayList<Carte> mainCarte;
	
	/**
	 * Constructeur classe Main
	 */
	public Main() {
		this.mainCarte = new ArrayList<Carte>();
	}
	
	/**
	 * Méthode ajouterCarte
	 * Permet d'ajouter une carte dans la main
	 * 
	 * @param carte
	 * type Carte: la carte à ajouter
	 */
	public void ajouterCarte(Carte carte) {
		this.mainCarte.add(carte);
	}

	/**
	 * Méthode ajouterPlusieursCartes
	 * Permet d'ajouter plusieurs cartes dans la main
	 * 
	 * @param cartes
	 * type ArrayList<Carte>: les cartes à ajouter
	 */
	public void ajouterPlusieursCartes(ArrayList<Carte> cartes){
		this.mainCarte.addAll(cartes);
	}

	/**
	 * Méthode getMainCarte
	 * getter de l'attribut mainCarte
	 */
	public ArrayList<Carte> getMainCarte(){
		return mainCarte;
	}
	
	/**
	 * Méthode isEmpty
	 * Permet de connaitre si la main est vide
	 */
	public boolean isEmpty() {
		if (this.mainCarte.isEmpty()){
			return true;
		} else {
			return false;
		}
	} 

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		String mainString = "";
		Iterator<Carte> it = this.mainCarte.iterator();
		while (it.hasNext()){
			mainString += it.next().getNomCarte() + ";";
		}
		return mainString;
	}
}
