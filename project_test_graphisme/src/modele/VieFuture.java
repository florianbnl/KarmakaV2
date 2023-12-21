package modele;

import java.io.Serializable;
import java.util.*;

public class VieFuture implements Serializable{
	
	private ArrayList<Carte> vieFutureCarte;
	
	public VieFuture() {
		this.vieFutureCarte = new ArrayList<Carte>();
	}

    public void ajouterCarte(Carte carte) {
		this.vieFutureCarte.add(carte);
	}

	public ArrayList<Carte> getVieFutureCarte() {
		return this.vieFutureCarte;
	}

	public void enleverPlusieursCartes(){
		this.getVieFutureCarte().removeAll(this.vieFutureCarte);
	}

	public void enleverCartes(Carte carte){
		this.getVieFutureCarte().remove(carte);
	}

	public Carte getFirst(){
		return this.getVieFutureCarte().get(0);
	}

	public void removeFirst(){
		this.getVieFutureCarte().remove(0);
	}

	public String toString(){
		String vieFutureString = "";
		Iterator<Carte> it = this.vieFutureCarte.iterator();
		while (it.hasNext()){
			vieFutureString += it.next().getNomCarte() + ";";
		}
		return vieFutureString;
	}

	
}
