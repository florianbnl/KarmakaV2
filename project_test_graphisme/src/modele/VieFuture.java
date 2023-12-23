package modele;

import java.io.Serializable;
import java.util.*;

/**
 * Classe VieFuture implémente Serializable
 */
public class VieFuture implements Serializable{
	
	private ArrayList<Carte> vieFutureCarte;
	
	/**
	 * Constructeur classe VieFuture
	 */
	public VieFuture() {
		this.vieFutureCarte = new ArrayList<Carte>();
	}

	/**
	 * Méthode ajouterCarte
	 * Permet d'ajouter une carte à la vie future
	 * 
	 * @param carte
	 * type Carte: la carte à ajouter
	 */
    public void ajouterCarte(Carte carte) {
		this.vieFutureCarte.add(carte);
	}

    /**
     * Méthode getVieFutureCarte
     * getter de l'attribut vieFutureCarte
     */
	public ArrayList<Carte> getVieFutureCarte() {
		return this.vieFutureCarte;
	}

	/**
	 * Méthode enleverPlusieursCarte
	 * Permet d'enlever toutes les cartes de la vie future
	 */
	public void enleverPlusieursCartes(){
		this.getVieFutureCarte().removeAll(this.vieFutureCarte);
	}

	/**
	 * Méthode enleverCartes
	 * Permet d'enlever une carte de la vie future
	 * 
	 * @param carte
	 * type Carte: la carte à enlever
	 */
	public void enleverCartes(Carte carte){
		this.getVieFutureCarte().remove(carte);
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère pour définir l'objet
	 */
	public String toString(){
		String vieFutureString = "";
		Iterator<Carte> it = this.vieFutureCarte.iterator();
		while (it.hasNext()){
			vieFutureString += it.next().getNomCarte() + ";";
		}
		return vieFutureString;
	}

	
}
