package modele;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import Controleur.ControleurCapacite;
import modele.Strategie.Strategie;

/**
 * Classe Joueur spécifie AccesVueCapacite et implémente Serializable
 */
public class Joueur extends AccesVueCapacite implements Serializable {
	
	private String nomJoueur;
	private EchelleKarmique echelleKarmique;
	private Oeuvres oeuvres;
	private VieFuture vieFuture;
	private Pile pile;
	private Main main;
	private AnneauxKarmiques anneauxKarmique;
	private Strategie strategie;
	private final PlateauJeu plateauJeu;
	private Color colorText;
	
	/**
	 * Constructeur classe Joueur
	 * 
	 * @param nomJoueur
	 * type String: le nom de joueur
	 * 
	 * @param strategieJoueur
	 * type Strategie: la strategie du joueur
	 * 
	 * @param plateauJeu
	 * type PlateauJeu: le plateau de jeu
	 * 
	 * @param color
	 * type Color: la couleur du joueur
	 */
	public Joueur(String nomJoueur, Strategie strategieJoueur, PlateauJeu plateauJeu, Color color) {
		super();
		this.colorText = color;
		this.anneauxKarmique = new AnneauxKarmiques();
		this.plateauJeu = plateauJeu;
		if (nomJoueur.compareTo("")==0) {
			this.nomJoueur = "joueur";
		} else {
			this.nomJoueur = nomJoueur;
		}
		this.echelleKarmique = new EchelleKarmique();
		this.oeuvres = new Oeuvres();
		this.vieFuture = new VieFuture();
		this.setPile(new Pile());
		this.setMain(new Main());
		this.setStrategie(strategieJoueur);
	}
	
	/**
	 * Méthode aGagner
	 * Permet de savoir si le joueur a gagné la partie
	 */
	public boolean aGagner() {
		if (this.echelleKarmique.getEchelleVieJoueur() != EchelleKarmiqueEnum.Transcendance) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode reincarnation
	 * Permet de savoir si le joueur a le bon nombre de point pour augmenter dans l'echelle Karmique
	 */
	public void reincarnation(){
		int sommeValeurCarte = this.getOeuvres().compterValeurCarte();
		if (sommeValeurCarte > this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum()){
			this.getEchelleKarmique().updateEchelle();
		}
		else if ((sommeValeurCarte + this.getAnneauxKarmique().getAnneauxKarmiques()) > this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum()){
			this.getAnneauxKarmique().enleverAnneauxJoueur(this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum() - sommeValeurCarte);
			this.getEchelleKarmique().updateEchelle();
		}
		else {
			this.getAnneauxKarmique().ajouterAnneauxJoueur();
		}
		this.renaissance();
	}

	/**
	 * Méthode renaissance
	 * Permet de remettre a zero les cartes du joueur après sa réincarnation
	 */
	public void renaissance(){
		this.getPlateauJeu().getFosse().defausserPlusieursCartes(this.getOeuvres().getOeuvresCarte());
		this.getOeuvres().enleverPlusieursCartes();
		this.getMain().ajouterPlusieursCartes(this.getVieFuture().getVieFutureCarte());
		this.getVieFuture().enleverPlusieursCartes();
		while ((this.getMain().getMainCarte().size() + this.getPile().getPileCartes().size()) < 6){
			this.getPile().ajouterCarte(this.getPlateauJeu().getSource().distribuerUneCarte());
		}
	}


	/**
	 * Méthode tourJoueur
	 * Permet de lancer le tour du joueur pour savoir s'il doit se réincarner ou s'il doit piocher une carte ou aucun des deux
	 */
	public void tourJoueur() {
		if (this.main.isEmpty() && this.pile.isEmpty()){
			this.reincarnation();
		} else {
			if (!this.pile.isEmpty()){
				this.main.ajouterCarte(this.pile.piocherCarte());
			}
		}
				
	}
	
	/**
	 * Méthode utiliserCarte
	 * Permet d'utiliser une carte
	 * 
	 * @param positionCarte
	 * type Integer: la position de la carte à jouer
	 * 
	 * @param utilisationCarte
	 * type Integer: pour quelle méthode utiliser la carte
	 */
	public void utiliserCarte(int positionCarte, int utilisationCarte) {
		switch (utilisationCarte) {
		case 0:
			this.getMain().getMainCarte().get(positionCarte).jouerPoints(this);
			this.getDiffuseur().firePropertyChange("joueurSuivant", this.getPlateauJeu().getJoueurs().get((this.getPlateauJeu().getJoueurs().indexOf(this) + 1) % 2), this);
			break;
		case 1:
			this.getMain().getMainCarte().get(positionCarte).jouerFuture(this);
			this.getDiffuseur().firePropertyChange("joueurSuivant", this.getPlateauJeu().getJoueurs().get((this.getPlateauJeu().getJoueurs().indexOf(this) + 1) % 2), this);
			break;
		case 2:
			this.getMain().getMainCarte().get(positionCarte).jouerPouvoirs(plateauJeu, this, this.getPlateauJeu().getJoueurs().get((this.getPlateauJeu().getJoueurs().indexOf(this) + 1) % 2));
			break;
		}
	}
	
	/**
	 * Méthode getAnneauxKarmique
	 * getter de l'attribut anneauxKarmique
	 */
	public AnneauxKarmiques getAnneauxKarmique() {
		return this.anneauxKarmique;
	}


	/**
	 * Méthode getNomJoueur
	 * getter de l'attribut nomJoueur
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}


	/**
	 * Méthode setNomJoueur
	 * setter de l'attribut nomJoueur
	 * 
	 * @param nomJoeur
	 * type String: le nom du joueur
	 */
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}


	/**
	 * Méthode getEchelleKarmique
	 * getter de l'attribut echelleKarmique
	 */
	public EchelleKarmique getEchelleKarmique() {
		return echelleKarmique;
	}

	/**
	 * Méthode setEchelleKarmique
	 * setter de l'attribut echelleKarmique
	 * 
	 * @param echelleKarmique
	 * type EchelleKarmique:
	 */
	public void setEchelleKarmique(EchelleKarmique echelleKarmique) {
		this.echelleKarmique = echelleKarmique;
	}


	/**
	 * Méthode getOeuvres
	 * getter de l'attribut oeuvres
	 */
	public Oeuvres getOeuvres() {
		return oeuvres;
	}


	/**
	 * Méthode setOeuvres 
	 * setter de l'attribut oeuvres
	 * 
	 * @param oeuvres
	 * type Oeuvres:
	 */
	public void setOeuvres(Oeuvres oeuvres) {
		this.oeuvres = oeuvres;
	}


	/**
	 * Méthode getVieFuture
	 * getter de l'attribut vieFuture
	 */
	public VieFuture getVieFuture() {
		return vieFuture;
	}


	/**
	 * Méthode setVieFuture
	 * setter de l'attribut vieFuture
	 * 
	 * @param vieFuture
	 * type VieFuture:
	 */
	public void setVieFuture(VieFuture vieFuture) {
		this.vieFuture = vieFuture;
	}

	/**
	 * Méthode getPile
	 * getter de l'attribut pile
	 */
	public Pile getPile() {
		return pile;
	}


	/**
	 * Méthode setPile
	 * setter de l'attribut pile
	 * 
	 * @param pile
	 * type Pile:
	 */
	public void setPile(Pile pile) {
		this.pile = pile;
	}

	/**
	 * Méthode setMain
	 * setter de l'attribut main
	 * 
	 * @param main
	 * type Main:
	 */
	public void setMain(Main main) {
		this.main = main;
	}


	/**
	 * Méthode getStrategie
	 * getter de l'attribut strategie
	 */
	public Strategie getStrategie() {
		return this.strategie;
	}

	/**
	 * Méthode getMain
	 * getter de l'attribut main
	 */
	public Main getMain(){
		return this.main;
	}

	/**
	 * Méthode setStrategie
	 * setter de l'attribut strategie
	 * 
	 * @param strategie
	 * type Strategie:
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	/**
	 * Méthode getPlateaujeu
	 * getter de l'attribut plateauJeu
	 */
	public PlateauJeu getPlateauJeu(){
		return this.plateauJeu;
	}

	/**
	 * Méthode getCoueleurText
	 * getter de l'attribut colorText
	 */
	public Color getCouleurText(){
		return this.colorText;
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caracère qui définie l'objet
	 */
	public String toString(){
		String joueuString = "Joueur\n";
		joueuString += "Nom: " + this.getNomJoueur() + "\n";
		joueuString += this.getEchelleKarmique().toString() + "\n";
		joueuString += "Oeuvre:" + this.getOeuvres().toString() + "\n";
		joueuString += "Future:" + this.getVieFuture().toString() + "\n";
		joueuString += "Pile: " + this.getPile().toString() + "\n";
		joueuString += "Main: " + this.getMain().toString() + "\n";
		joueuString += this.getAnneauxKarmique().toString() + "\n";
		joueuString += this.getStrategie().vraiJoueur() + "\n";
		return joueuString;
	}

	/**
	 * Méthode vueBouton1
	 * Permet de mettre la carte choisie a la fosse lorsque le bouton 1 est appuyé
	 * 
	 * @param c
	 * type Carte : la carte Sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		// Fosse
		this.plateauJeu.getFosse().defausserCarte(c);
	}

	/**
	 * Méthode vueBouton2
	 * Permet de mettre la carte choisie dans la vie future lorsque le bouton 2 est appuyé
	 * 
	 * @param c
	 * type Carte : la carte Sélectionner
	 * 
	 * @param joueurAttack
	 * type Joueur: le joueur qui réalise le tour
	 */
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		// Vie Future
		this.getVieFuture().ajouterCarte(c);		
	}
}
