package modele.Strategie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import modele.Carte;
import modele.Joueur;
import modele.PlateauJeu;

/**
 * Classe Strategie implémente Serializable
 */
public abstract class Strategie implements Serializable{

	private String msg ="";

	/**
	 * Constructeur classe Strategie
	 */
	public Strategie(){

	}

	/**
	 * Méthode abstraite strategie
	 * Permet de lancer la strategie du joueur
	 * 
	 * @param joueur
	 * type Joueur: le joueur qui réalise la stratégie
	 */
	public abstract void strategie(Joueur joueur);

	/**
	 * Méthode abstraite vraiJoueur
	 * Renvoie si un joueur est un bot ou non
	 */
	public abstract boolean vraiJoueur();

	/**
	 * Méthode choixBot
	 * Permet de faire un choix aléatoire pour le bot
	 * 
	 * @param taille
	 * type Integer: la borne supérieur du random
	 * 
	 * @param nbNumeroARcuperer
	 * type Integer: le nombre de valeur différente que l'on veut récupérer
	 */
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
	
	/**
	 * Méthode abstraite seFaireAttaquer
	 * Permet de réaliser le choix lors du coût Karmique
	 * 
	 * @param carte
	 * type Carte: la carte que le joueur rival à jouer pour son pouvoir
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public abstract void seFaireAttaquer(Carte carte, Joueur joueurVise, PlateauJeu plateauJeu);

	/**
	 * Méthode getMsg
	 * getter de l'attribut msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Méthode setMsg
	 * setter de l'attribut msg
	 * 
	 * @param msg
	 * type String: la chaine de caractère à mettre dans msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
