package modele;

import java.io.Serializable;
import java.util.*;

public class Main implements Serializable{
	
	private ArrayList<Carte> mainCarte;
	
	public Main() {
		this.mainCarte = new ArrayList<Carte>();
	}
		
	public void ajouterCarte(Carte carte) {
		this.mainCarte.add(carte);
	}

	public void ajouterPlusieursCartes(ArrayList<Carte> cartes){
		this.mainCarte.addAll(cartes);
	}

	public ArrayList<Carte> getMainCarte(){
		return mainCarte;
	}
	
	public boolean isEmpty() {
		if (this.mainCarte.isEmpty()){
			return true;
		} else {
			return false;
		}
	} 

	public void afficherCarte(){
		Iterator<Carte> it = this.mainCarte.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}

	public String toString(){
		String mainString = "";
		Iterator<Carte> it = this.mainCarte.iterator();
		while (it.hasNext()){
			mainString += it.next().getNomCarte() + ";";
		}
		return mainString;
	}
}
