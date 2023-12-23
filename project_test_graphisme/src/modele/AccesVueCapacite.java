package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Classe abstrète AccesVueCapacite qui implémente Serializable
 * Permet d'avoir une classe qui peut être observer par d'autre classe et qui peut accéder à la vue VueCapacite
 */
public abstract class AccesVueCapacite implements Serializable{
	
	private PropertyChangeSupport diffuseur;

	/**
	 * Construteur classe AccesVueCapacite
	 * initialise le diffuseur
	 */
	public AccesVueCapacite() {
		this.diffuseur = new PropertyChangeSupport(this);
	}
	
	/**
	 * Méthode ajouterUnSouscripteur
	 * Permet d'ajouter un listener a diffuseur
	 * 
	 * @param pcl
	 * type PropertyChangeListener : le listener
	 */
	public void ajouterUnSouscripteur(PropertyChangeListener pcl) {
		this.diffuseur.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Méthode ajouterUnSouscripteur
	 * Permet d'ajouter un listener a diffuseur selon un propriété
	 * 
	 * @param propriete
	 * type String qui est le nom de la propriété sur lequel veut être alerter le listener
	 * 
	 * @param pcl
	 * type PropertyChangeListener : le listener
	 */
	public void ajouterUnSouscripteur(String propriete, PropertyChangeListener pcl) {
		this.diffuseur.addPropertyChangeListener(propriete, pcl);
	}
	
	/**
	 * Méthode getDiffuseur
	 * getter de l'attribut diffuseur
	 */
	public PropertyChangeSupport getDiffuseur() {
		return diffuseur;
	}

	/**
	 * Méthode setDiffuseur
	 * setter de l'attribut diffuseur
	 */
	public void setDiffuseur(PropertyChangeSupport diffuseur) {
		this.diffuseur = diffuseur;
	}

	/**
	 * Méthode abstraite vueBouton1
	 * Permet de réaliser une action lorsque le bouton 1 de la vueCapacite est actionné
	 * 
	 * @param c
	 * type Carte : la carte Sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public abstract void vueBouton1(Carte c, Joueur joueurAttack);
	
	/**
	 * Méthode abstraite vueBouton2
	 * Permet de réaliser une action lorsque le bouton 2 de la vueCapacite est actionné
	 * 
	 * @param c
	 * type Carte : la carte Sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public abstract void vueBouton2(Carte c, Joueur joueurAttack);
}
