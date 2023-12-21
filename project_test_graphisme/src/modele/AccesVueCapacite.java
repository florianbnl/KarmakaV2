package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class AccesVueCapacite implements Serializable{
	
	private PropertyChangeSupport diffuseur;
	
	public PropertyChangeSupport getDiffuseur() {
		return diffuseur;
	}

	public void setDiffuseur(PropertyChangeSupport diffuseur) {
		this.diffuseur = diffuseur;
	}

	public AccesVueCapacite() {
		this.diffuseur = new PropertyChangeSupport(this);
	}
	
	public void ajouterUnSouscripteur(PropertyChangeListener pcl) {
		this.diffuseur.addPropertyChangeListener(pcl);
	}
	
	public void ajouterUnSouscripteur(String propriete, PropertyChangeListener pcl) {
		this.diffuseur.addPropertyChangeListener(propriete, pcl);
	}

	public abstract void vueBouton1(Carte c, Joueur joueurAttack);
	public abstract void vueBouton2(Carte c, Joueur joueurAttack);
}
