package modele;

import java.io.File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import modele.Strategie.*;

public class Partie {
	
	private PlateauJeu plateauJeu;
	
	public PlateauJeu getPlateauJeu() {
		return plateauJeu;
	}

	public void setPlateauJeu(PlateauJeu plateauJeu) {
		this.plateauJeu = plateauJeu;
	}

	public Partie(String nomJoueur1, String nomJoueur2, Strategie strategieJoueur2) {
		this.plateauJeu = PlateauJeu.getInstance(nomJoueur1, nomJoueur2, new JoueurReel(), strategieJoueur2);
	}

	public Partie(){
		File path = new File("./src/modele/sauvegarder.txt");
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
			this.plateauJeu = (PlateauJeu) ois.readObject();
			PlateauJeu.setInstance(this.plateauJeu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
