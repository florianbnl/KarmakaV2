package modele;

import java.io.File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import modele.Strategie.*;

/**
 * Classe Partie
 */
public class Partie {
	
	private PlateauJeu plateauJeu;
	
	/**
	 * Constructeur classe Partie
	 * Permet de créer une nouvelle partie
	 * 
	 * @param nomJoueur1
	 * type String: le nom du joueur 1
	 * 
	 * @param nomJoueur2
	 * type String: le nom du joueur 2
	 * 
	 * @param strategieJoueur2
	 * type Strategie: la strategie du joueur 2
	 */
	public Partie(String nomJoueur1, String nomJoueur2, Strategie strategieJoueur2) {
		this.plateauJeu = PlateauJeu.getInstance(nomJoueur1, nomJoueur2, new JoueurReel(), strategieJoueur2);
	}

	/**
	 * Constructeur classe Partie
	 * Permet de reprendre une partie enregistrée
	 */
	public Partie(){
		File path = new File("./src/modele/sauvegarder.txt");
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
			this.plateauJeu = (PlateauJeu) ois.readObject();
			PlateauJeu.setInstance(this.plateauJeu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode getPlateauJeu
	 * getter de l'attribut plateauJeu
	 */
	public PlateauJeu getPlateauJeu() {
		return plateauJeu;
	}

	/**
	 * Méthode setPlateauJeu
	 * setter de l'attribut pleauJeu
	 * 
	 * @param plateauJeu
	 * type PlateauJeu:
	 */
	public void setPlateauJeu(PlateauJeu plateauJeu) {
		this.plateauJeu = plateauJeu;
	}

}
