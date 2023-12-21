package modele.Strategie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;

public abstract class Strategie implements Serializable{

	private String msg ="";

	public Strategie(){

	}

	
	public abstract void strategie(Joueur joueur);

	public abstract boolean vraiJoueur();

	public ArrayList<Integer> choixBot(int taille, int nbNumeroARecuperer){
		ArrayList<Integer> numero = new ArrayList<Integer>();
		if (taille != 0) {
			Random rd = new Random();
			int  nombre= rd.nextInt(taille);
			while (numero.size() <= nbNumeroARecuperer && numero.size() < taille) {
				if (!numero.contains(nombre)) {
					numero.add(nombre);
				}
				nombre = rd.nextInt(taille);
			}
		}
		return numero;
	}
	
	public abstract void seFaireAttaquer(Carte carte, Joueur joueurVise, PlateauJeu plateauJeu);

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
