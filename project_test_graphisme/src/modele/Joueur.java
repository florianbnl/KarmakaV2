package modele;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import Controleur.ControleurCapacite;
import modele.Strategie.Strategie;

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
	
	//Création d'un joueur quand on commence une nouvelle partie
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
	
	public boolean aGagner() {
		if (this.echelleKarmique.getEchelleVieJoueur() != EchelleKarmiqueEnum.Transcendance) {
			return true;
		} else {
			return false;
		}
	}

	public void reincarnation(){
		int sommeValeurCarte = this.getOeuvres().compterValeurCarte();
		if (sommeValeurCarte >= this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum()){
			this.getEchelleKarmique().updateEchelle();
		}
		else if ((sommeValeurCarte + this.getAnneauxKarmique().getAnneauxKarmiques()) >= this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum()){
			this.getAnneauxKarmique().enleverAnneauxJoueur(this.getEchelleKarmique().getEchelleVieJoueur().getEchelleKarmiqueEnum() - sommeValeurCarte);
			this.getEchelleKarmique().updateEchelle();
		}
		else {
			this.getAnneauxKarmique().ajouterAnneauxJoueur();
		}
		this.renaissance();
	}

	public void renaissance(){
		this.getPlateauJeu().getFosse().defausserPlusieursCartes(this.getOeuvres().getOeuvresCarte());
		this.getOeuvres().enleverPlusieursCartes();
		this.getMain().ajouterPlusieursCartes(this.getVieFuture().getVieFutureCarte());
		this.getVieFuture().enleverPlusieursCartes();
		while ((this.getMain().getMainCarte().size() + this.getPile().getPileCartes().size()) < 6){
			this.getPile().ajouterCarte(this.getPlateauJeu().getSource().distribuerUneCarte());
		}
	}


	public void tourJoueur() {
		if (this.main.isEmpty() && this.pile.isEmpty()){
			this.reincarnation();
		} else {
			if (!this.pile.isEmpty()){
				this.main.ajouterCarte(this.pile.piocherCarte());
			}
		}
				
	}
	
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
	
	public AnneauxKarmiques getAnneauxKarmique() {
		return this.anneauxKarmique;
	}


	public String getNomJoueur() {
		return nomJoueur;
	}


	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}


	public EchelleKarmique getEchelleKarmique() {
		return echelleKarmique;
	}


	public void setEchelleKarmique(EchelleKarmique echelleKarmique) {
		this.echelleKarmique = echelleKarmique;
	}


	public Oeuvres getOeuvres() {
		return oeuvres;
	}


	public void setOeuvres(Oeuvres oeuvres) {
		this.oeuvres = oeuvres;
	}


	public VieFuture getVieFuture() {
		return vieFuture;
	}


	public void setVieFuture(VieFuture vieFuture) {
		this.vieFuture = vieFuture;
	}


	public Pile getPile() {
		return pile;
	}


	public void setPile(Pile pile) {
		this.pile = pile;
	}


	public void setMain(Main main) {
		this.main = main;
	}


	public Strategie getStrategie() {
		return this.strategie;
	}

	public Main getMain(){
		return this.main;
	}


	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	public PlateauJeu getPlateauJeu(){
		return this.plateauJeu;
	}

	public Color getCouleurText(){
		return this.colorText;
	}

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

	@Override
	public void vueBouton1(Carte c, Joueur joueurAttack) {
		// Fosse
		this.plateauJeu.getFosse().defausserCarte(c);
	}

	@Override
	public void vueBouton2(Carte c, Joueur joueurAttack) {
		// Vie Future
		this.getVieFuture().ajouterCarte(c);		
	}
}
