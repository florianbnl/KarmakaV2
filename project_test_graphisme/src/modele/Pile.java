package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Pile implements Serializable{
	
	private ArrayList<Carte> pileCarte;
	
	public Pile() {
		this.pileCarte = new ArrayList<Carte>();
	}

	public ArrayList<Carte> getPileCartes(){
		return this.pileCarte;
	}
    public Carte piocherCarte() {
		Carte c;
		c = this.pileCarte.remove(0);
		return c;
	}
	
	public void ajouterCarte(Carte carte) {
		this.pileCarte.add(carte);
	}

	public boolean isEmpty(){
		if (this.pileCarte.isEmpty()){
			return true;
		} else{
			return false;
		}
	}

	public String toString(){
		String pileString = "";
		Iterator<Carte> it = this.pileCarte.iterator();
		while (it.hasNext()){
			pileString += it.next().getNomCarte() + ";";
		}
		return pileString;
	}
}
