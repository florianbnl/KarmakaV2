package modele;

import modele.Strategie.Strategie;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Classe PlateauJeu implémente Serializable
 */
public class PlateauJeu implements Serializable {

	private static PlateauJeu instance = null;

	private Fosse fosse;
	private Source source;
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Méthode getInstance
	 * Permet de récupérer ou créer l'instance de la classe (Singletron)
	 * 
	 * @param nomJoueur1
	 * type String: le nom du joueur 1
	 * 
	 * @param nomJoueur2
	 * type String: le nom du joueur 2
	 * 
	 * @param strategieJoueur1
	 * type Strategie: la stratégie du joueur 1
	 * 
	 * @param strategieJoueur2
	 * type Strategie: la stratégie du joueur 2
	 */
	public static PlateauJeu getInstance(String nomJoueur1, String nomJoueur2, Strategie strategieJoueur1, Strategie strategieJoueur2) {
		if (instance == null) {
			instance = new PlateauJeu(nomJoueur1, nomJoueur2, strategieJoueur1, strategieJoueur2);
		}
		return instance;
	}
	
	/**
	 * Constructeur classe PlateauJeu
	 * 
	 * @param nomJoueur1
	 * type String: le nom du joueur 1
	 * 
	 * @param nomJoueur2
	 * type String: le nom du joueur 2
	 * 
	 * @param strategieJoueur1
	 * type Strategie: la stratégie du joueur 1
	 * 
	 * @param strategieJoueur2
	 * type Strategie: la stratégie du joueur 2
	 */
	public PlateauJeu(String nomJoueur1, String nomJoueur2, Strategie strategieJoueur1, Strategie strategieJoueur2) {
			this.joueurs = new ArrayList<Joueur>();
			if (this.choixJoueurCommence()) {
				this.joueurs.add(new Joueur(nomJoueur1, strategieJoueur1, this, new Color(0,0,150,100)));
				this.joueurs.add(new Joueur(nomJoueur2, strategieJoueur2, this, new Color(150,0,0,100)));
			} else {
				this.joueurs.add(new Joueur(nomJoueur2, strategieJoueur2, this, new Color(150,0,0,100)));
				this.joueurs.add(new Joueur(nomJoueur1, strategieJoueur1, this, new Color(0,0,150,100)));
			}
			
			this.fosse = new Fosse();
			this.source = new Source(this);
			this.distribuerCarteDebutJeu();
			System.out.println(this);
		this.joueurs.get(0).tourJoueur();
	}

	/**
	 * Méthode getInstance
	 * getter de l'instance de la classe
	 */
	public static PlateauJeu getInstance() {
		return instance;
	}
	
	/**
	 * Méthode setInstance
	 * setter de l'instance de la classe
	 * 
	 * @param instance
	 * type PlateauJeu
	 */
	public static void setInstance(PlateauJeu instance) {
		PlateauJeu.instance = instance;
	}

	/**
	 * Méthode getFosse
	 * getter de l'attribut fosse
	 */
	public Fosse getFosse() {
		return fosse;
	}

	/**
	 * Méthode getSource
	 * getter de l'attribut source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * Méthode getJoueurs
	 * getter de l'attribut joueurs
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Méthode distribuerCarteDebutJeu
	 * Permet de distribuer les cartes à chaques joueurs
	 */
	public void distribuerCarteDebutJeu() {
		//Distribution de 4 cartes dans la Main de chaque joueur
		for (int i=0; i<4; i++) {
				Iterator<Joueur> it = this.joueurs.iterator();
				while (it.hasNext()) {
					it.next().getMain().ajouterCarte(this.source.distribuerUneCarte());
				}
			}
		// distribution de 2 cartes dans la Pile du joueur
			for (int i=0; i<2; i++) {
				Iterator<Joueur> it = this.joueurs.iterator();
				while (it.hasNext()) {
					it.next().getPile().ajouterCarte(this.source.distribuerUneCarte());
				}
			}
	}

	/**
	 * Méthode choixJoueurCommence
	 * Permet de connaitre quelle joueur commence
	 */
	public boolean choixJoueurCommence() {
		Random aleatoire = new Random();
		boolean ON = aleatoire.nextBoolean();
		return ON;
	}

	/**
	 * Méthode enregistrerPlateauJeu
	 * Permet d'enregistrer une partie
	 */
	public void enregistrerPlateauJeu(){
		File path = new File("./src/modele/sauvegarder.txt");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
			oos.writeObject(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		String plateauJeuString = "";
		plateauJeuString += this.getJoueurs().get(0).toString() + "\n";
		plateauJeuString += this.getJoueurs().get(1).toString() + "\n";
		plateauJeuString += this.getFosse().toString();
		return plateauJeuString;
	}
	
}
