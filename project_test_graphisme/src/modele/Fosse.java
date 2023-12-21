package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Fosse implements Serializable {
	
	private ArrayList<Carte> fosseCarte;
	
	public ArrayList<Carte> getFosseCarte() {
		return fosseCarte;
	}

	public void setFosseCarte(ArrayList<Carte> fosseCarte) {
		this.fosseCarte = fosseCarte;
	}

	public Fosse() {
		this.fosseCarte = new ArrayList<Carte>();
	}
	
	public ArrayList<Carte> enleverCartes(){
		ArrayList<Carte> tamps = this.fosseCarte;
		this.fosseCarte.removeAll(fosseCarte);
		return tamps;
	}

	public void defausserPlusieursCartes(ArrayList<Carte> carte) {
		this.fosseCarte.addAll(carte);
	}

	public void defausserCarte(Carte carte){
		this.fosseCarte.add(carte);
	}
	
	public boolean isEmpty() {
		return this.fosseCarte.isEmpty();
	}

	public String toString(){
		String fosseString = "Fosse\n";
		Iterator<Carte> it = this.fosseCarte.iterator();
		while (it.hasNext()){
			fosseString += it.next().getNomCarte() + ";";
		}
		return fosseString;
	}

}
