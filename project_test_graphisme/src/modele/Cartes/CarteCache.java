package modele.Cartes;

import modele.Carte;
import modele.CouleurCarteEnum;
import modele.Joueur;
import modele.PlateauJeu;
import modele.ValeurCarteEnum;

/**
 * Classe CarteCache spécialise Carte
 * Permet d'afficher des cartes à l'utilisateur qui ne sont pas du jeu
 */
public class CarteCache extends Carte {

	/**
	 * Constructeur classe CarteCache
	 */
	public CarteCache(ValeurCarteEnum valeurCarte, String nomCarte, CouleurCarteEnum couleur, String nomPouvoir) {
		super(valeurCarte, nomCarte, couleur, nomPouvoir);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur classe CarteCache
	 * 
	 * @param numCarte
	 * type String: le numéro de la carte que l'on veut afficher
	 */
	public CarteCache(String numCarte) {
		super(null, "CarteCache" , null, numCarte);
	}

	/**
	 * Méthode capacite
	 * Permet d'utiliser la capacite de la carte CarteCache
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 * 
	 */
	public void capacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode apresUtilisationCapacite
	 * Permet de réaliser des actions après l'utilisation d'une capacité
	 * 
	 * @param plateauJeu
	 * type PlateauJeu
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui utilise la carte
	 * 
	 * @param joueurVise
	 * type Joueur: le joueur qui se fait attaquer
	 */
	public void apresUtilisationCapacite(PlateauJeu plateauJeu, Joueur joueurAttack, Joueur joueurVise) {
		// TODO Auto-generated method stub
		
	}

}
