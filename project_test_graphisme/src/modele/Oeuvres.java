package modele;

import java.io.Serializable;
import java.util.*;

public class Oeuvres implements Serializable {
	
	private ArrayList<Carte> oeuvresCarte;
	
	public Oeuvres() {
		this.oeuvresCarte = new ArrayList<Carte>();
	}

	public void enleverCarte(Carte carte){
		this.oeuvresCarte.remove(carte);
	}

	public void enleverPlusieursCartes(){
		this.oeuvresCarte.removeAll(this.oeuvresCarte);
	}
	
	public void ajouterCarte(Carte carte) {
		this.oeuvresCarte.add(carte);
	}

	public void ajouterCarteDebut(Carte carte) {
		this.oeuvresCarte.add(0, carte);
	}

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

	public ArrayList<Carte> getOeuvresCarte(){
		return this.oeuvresCarte;
	}

	public String toString(){
		String oeuvreString = "";
		Iterator<Carte> it = this.oeuvresCarte.iterator();
		while (it.hasNext()){
			oeuvreString += it.next().getNomCarte() + ";";
		}
		return oeuvreString;
	}
}
