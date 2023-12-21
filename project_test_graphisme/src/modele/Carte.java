package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import Vue.VueCapacite;

public abstract class Carte extends AccesVueCapacite implements Serializable{
	private ValeurCarteEnum valeurCarte;
	private String nomCarte;
	private CouleurCarteEnum couleur;
	private String nomPouvoir;
	
	
	public Carte(ValeurCarteEnum valeurCarte, String nomCarte, CouleurCarteEnum couleur, String nomPouvoir) {
		super();
		this.valeurCarte = valeurCarte;
		this.nomCarte = nomCarte;
		this.couleur = couleur;
		this.setNomPouvoir(nomPouvoir);
	}
	
	public ValeurCarteEnum getValeurCarte() {
		return valeurCarte;
	}
	
	public void setValeurCarte(ValeurCarteEnum valeurCarte) {
		this.valeurCarte = valeurCarte;
	}
	
	public String getNomCarte() {
		return nomCarte;
	}

	public void setNomCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	public CouleurCarteEnum getCouleur(){
		return this.couleur;
	}
	
	public void jouerPoints(Joueur joueur) {
		joueur.getOeuvres().ajouterCarteDebut(this);
		joueur.getMain().getMainCarte().remove(this);
	}
	
	public void jouerPouvoirs(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getMain().getMainCarte().remove(this);
		this.capacite(plateauJeu, joueurAttack, joueurVise);
		this.apresUtilisationCapacite(plateauJeu, joueurAttack, joueurVise);
		
	}

	public void jouerFuture(Joueur joueur){
		joueur.getVieFuture().ajouterCarte(this);
		joueur.getMain().getMainCarte().remove(this);
	}

	public abstract void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise);
	
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		joueurAttack.getDiffuseur().firePropertyChange("joueurSuivant", joueurAttack, joueurVise);
	}
	
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		this.getDiffuseur().firePropertyChange("VueCapacite", null , c);
	}
	
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		this.getDiffuseur().firePropertyChange("VueCapacite", null , c);
	}
	
	public String toString() {
		return "\nValeur : " + this.getValeurCarte() + "\nCarte : " + this.nomCarte; 
	}
	
	public static void main(String[] args) {
	}

	public String getNomPouvoir() {
		return nomPouvoir;
	}

	public void setNomPouvoir(String nomPouvoir) {
		this.nomPouvoir = nomPouvoir;
	}
}
