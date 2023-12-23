package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe Pile implémente Serializable
 */
public class Pile implements Serializable{
	
	private ArrayList<Carte> pileCarte;
	
	/**
	 * Construteur classe Pile
	 */
	public Pile() {
		this.pileCarte = new ArrayList<Carte>();
	}

	/**
	 * Méthode getPileCartes
	 * getter de l'attribut pileCarte
	 */
	public ArrayList<Carte> getPileCartes(){
		return this.pileCarte;
	}
	
	/**
	 * Méthode piocherCarte
	 * Permet de piocher la première carte de la pile
	 */
    public Carte piocherCarte() {
		Carte c;
		c = this.pileCarte.remove(0);
		return c;
	}
	
    /**
     * Méthode ajouterCarte
     * Permet d'ajouter une carte dans la pile
     */
	public void ajouterCarte(Carte carte) {
		this.pileCarte.add(carte);
	}

	/**
	 * Méthode isEmpty
	 * Permet de connaitre si la pile est vide
	 */
	public boolean isEmpty(){
		if (this.pileCarte.isEmpty()){
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		String pileString = "";
		Iterator<Carte> it = this.pileCarte.iterator();
		while (it.hasNext()){
			pileString += it.next().getNomCarte() + ";";
		}
		return pileString;
	}
}
