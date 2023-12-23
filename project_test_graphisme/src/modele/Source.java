package modele;

import modele.Cartes.Bleu.*;
import modele.Cartes.Mosaique.Incarnation;
import modele.Cartes.Mosaique.Mimetisme;
import modele.Cartes.Rouge.*;
import modele.Cartes.Verte.*;

import java.io.Serializable;
import java.util.*;

/**
 * Classe Source implémente Serializable
 */
public class Source implements Serializable{

	private ArrayList<Carte> sourceCarte;
	private PlateauJeu plateauJeu;
	
	/**
	 * Constructeur classe Source
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 */
	public Source(PlateauJeu plateauJeu) {
		this.plateauJeu = plateauJeu;
		this.sourceCarte = new ArrayList<Carte>();
		for (int i=0; i<3; i++) {
			//Ajout carte couleur bleue qui sont 3 fois
			this.sourceCarte.add(new Transmigration());
			this.sourceCarte.add(new CoupDOeil());
			this.sourceCarte.add(new RevesBrises());
			this.sourceCarte.add(new Deni());
			this.sourceCarte.add(new Destinee());
			
			//Ajout carte couleur verte qui sont 3 fois
			this.sourceCarte.add(new Sauvetage());
			this.sourceCarte.add(new Lendemain());
			this.sourceCarte.add(new Longevite());
			this.sourceCarte.add(new Recyclage());
			this.sourceCarte.add(new Semis());
			
			//Ajout carte couleur rouge qui sont 3 fois
			this.sourceCarte.add(new DernierSouffle());
			this.sourceCarte.add(new Crise());
			this.sourceCarte.add(new Fournaise());
			this.sourceCarte.add(new Panique());
			this.sourceCarte.add(new Roulette());
			
			//Ajout carte couleur Mosaïque Incarnation
			this.sourceCarte.add(new Incarnation());
			
			
			if (i<2) {
				//Ajout carte couleur bleue qui sont 2 fois
				this.sourceCarte.add(new Vol());
				this.sourceCarte.add(new Duperie());
				
				//Ajout carte couleur verte qui sont 2 fois
				this.sourceCarte.add(new Voyage());
				this.sourceCarte.add(new Jubile());
				
				//Ajout carte couleur rouge qui sont 2 fois
				this.sourceCarte.add(new Vengeance());
				this.sourceCarte.add(new Bassesse());
				
				//Ajout carte couleur Mosaïque qui sont 2 fois
				this.sourceCarte.add(new Mimetisme());
			}
		}
		//Rajoute les 2 cartes manquantes de Incarnation
		this.sourceCarte.add(new Incarnation());
		this.sourceCarte.add(new Incarnation());
		this.melanger();
		//this.afficherCartes();
	}

	/**
	 * Méthode getSourceCarte
	 * getter de l'attribut sourceCarte
	 */
	public ArrayList<Carte> getSourceCarte() {
		return sourceCarte;
	}

	/**
	 * Méthode ajouterCarteSource
	 * Permet d'ajouter plusieurs cartes à la source
	 * 
	 * @param cartes
	 * type ArrayList<Carte>: les cartes à ajouter
	 */
	public void ajouterCarteSource(ArrayList<Carte> cartes) {
		this.sourceCarte.addAll(cartes);
	}
	
	/**
	 * Méthode ajouterCarteDessus
	 * Permet d'ajouter une carte au début de la source
	 * 
	 * @param c
	 * type Carte: la carte à ajouter
	 */
	public void ajouterCarteDessus(Carte c) {
		this.sourceCarte.addFirst(c);
	}
	
	/**
	 * Méthode melanger
	 * Permet de mélanger la source
	 */
	public void melanger() {
		Collections.shuffle(sourceCarte);
	}
	
	/**
	 * Méthode distribuerUneCarte
	 * Permet de distribuer une carte de la source
	 */
	public Carte distribuerUneCarte(){ 
		if (this.getSourceCarte().isEmpty()){
			this.ajouterCarteSource(this.plateauJeu.getFosse().enleverCartes());
		}
		// on retire la carte du dessus du tas de cartes
		return this.sourceCarte.remove(0);
		
	}

	/**
	 * Méthode recupererCarte
	 * Permet de prendre une carte précise dans la source
	 * 
	 * @param nomCarte
	 * type String: le nom de la carte à récupérer
	 */
	public Carte recupererCarte(String nomCarte){
		for (Carte c: this.getSourceCarte()){
			if (nomCarte.compareTo(c.getNomCarte()) == 0){
				this.getSourceCarte().remove(c);
				return c;
			}
		}
		return null;
	}
	

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui défine l'objet 
	 */
	public String toString() {
		str = "Source:\n"
		Iterator<Carte> it = this.sourceCarte.iterator();
		while (it.hasNext()) {
			str += it.next() + ";\n"
		}
	}

}
