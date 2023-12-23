package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import Vue.VueCapacite;

/**
 * Classe abstraite Carte spécifie AccesVueCapacite implémente Serializable
 */
public abstract class Carte extends AccesVueCapacite implements Serializable{
	private ValeurCarteEnum valeurCarte;
	private String nomCarte;
	private CouleurCarteEnum couleur;
	private String nomPouvoir;
	
	/**
	 * Constructeur classe Carte
	 * 
	 * @param valeurCarte
	 * type ValeurCarteEnum: la valeur de la carte
	 * 
	 * @param nomCarte
	 * type String: le nom de la carte
	 * 
	 * @param couleur
	 * type CouleurCarteEnum: la couleur de la carte
	 * 
	 * @param nomPouvoir
	 * type String: le nom du pouvoir de la carte
	 */
	public Carte(ValeurCarteEnum valeurCarte, String nomCarte, CouleurCarteEnum couleur, String nomPouvoir) {
		super();
		this.valeurCarte = valeurCarte;
		this.nomCarte = nomCarte;
		this.couleur = couleur;
		this.setNomPouvoir(nomPouvoir);
	}
	
	/**
	 * Méthode getValeurCarte
	 * getter de l'attribut valeurCarte
	 */
	public ValeurCarteEnum getValeurCarte() {
		return valeurCarte;
	}
	
	/**
	 * Méthode setValeurCarte
	 * setter de l'attribut valeurCarte
	 * 
	 * @param valeurCarte
	 * type ValeurCarteEnum: la valeur de la carte
	 */
	public void setValeurCarte(ValeurCarteEnum valeurCarte) {
		this.valeurCarte = valeurCarte;
	}
	
	/**
	 * Méthode getNomCarte
	 * getter de l'attribut nomCarte
	 */
	public String getNomCarte() {
		return nomCarte;
	}

	/**
	 * Méthode setNomCarte
	 * setter de l'attribut nomCarte
	 * 
	 * @param nomCarte
	 * type String: le nom de la carte
	 */
	public void setNomCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	/**
	 * Méthode getCouleur
	 * getter de l'attribut couleur
	 */
	public CouleurCarteEnum getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Méthode jouerPoints
	 * Permet d'utiliser une carte pour ses points
	 * 
	 * @param joueur
	 * type Joueur: le joueur qui utilise la carte
	 */
	public void jouerPoints(Joueur joueur) {
		joueur.getOeuvres().ajouterCarteDebut(this);
		joueur.getMain().getMainCarte().remove(this);
	}
	
	/**
	 * Méthode jouerPouvoir
	 * Permet d'utiliser une carte pour son pouvoir
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public void jouerPouvoirs(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getMain().getMainCarte().remove(this);
		this.capacite(plateauJeu, joueurAttack, joueurVise);
		this.apresUtilisationCapacite(plateauJeu, joueurAttack, joueurVise);
		
	}

	/**
	 * Méthode jouerFuture
	 * Permet d'utiliser une carte pour sa vie future
	 * 
	 * @param joueur
	 * type Joueur: le joueur qui utilise la carte
	 */
	public void jouerFuture(Joueur joueur){
		joueur.getVieFuture().ajouterCarte(this);
		joueur.getMain().getMainCarte().remove(this);
	}

	/**
	 * Méthode abstraite capacite
	 * Permet d'utiliser la capacite de la carte
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 * 
	 */
	public abstract void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise);
	
	
	/**
	 * Méthode apresUtilisationCapacite
	 * Permet de réaliser des actions après l'utilisation d'une capacité
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getDiffuseur().firePropertyChange("joueurSuivant", joueurAttack, joueurVise);
	}
	
	/**
	 * Méthode vueBouton1
	 * Permet de réaliser l'action lorsque le bouton 1 est appuyé dans VueCapacite
	 * 
	 * @param c
	 * type Carte: la carte sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		this.getDiffuseur().firePropertyChange("VueCapacite", null , c);
	}
	
	/**
	 * Méthode vueBouton2
	 * Permet de réaliser l'action lorsque le bouton 2 est appuyé dans VueCapacite
	 * 
	 * @param c
	 * type Carte: la carte sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		this.getDiffuseur().firePropertyChange("VueCapacite", null , c);
	}
	
	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString() {
		return "\nValeur : " + this.getValeurCarte() + "\nCarte : " + this.nomCarte; 
	}


	/**
	 * Méthode getNomPouvoir
	 * getter de l'attribut nomPouvoir
	 */
	public String getNomPouvoir() {
		return nomPouvoir;
	}

	/**
	 * Méthode setNomPouvoir
	 * setter de l'attribut nomPouvoir
	 */
	public void setNomPouvoir(String nomPouvoir) {
		this.nomPouvoir = nomPouvoir;
	}
}
