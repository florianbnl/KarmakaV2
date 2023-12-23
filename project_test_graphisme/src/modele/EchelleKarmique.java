package modele;

import java.io.Serializable;

/**
 * Classe EchelleKarmique implémente Serializable
 */
public class EchelleKarmique implements Serializable {

	private EchelleKarmiqueEnum echelleVieJoueur;
	
	/**
	 * Constructeur classe EchelleKarmique
	 */
	public EchelleKarmique() {
		this.setEchelleVieJoueur(EchelleKarmiqueEnum.Boursier);
	}

	/**
	 * Méthode updateEchelle
	 * Permet de mettre à jour l'echelle karmique du joueur
	 */
	public void updateEchelle() {
		int stamp = 0;
		for (EchelleKarmiqueEnum value: EchelleKarmiqueEnum.values()){
			if (stamp == 1){
				this.echelleVieJoueur = value;
			}
			if (value == this.echelleVieJoueur){
				stamp++;
			}
		}
	}

	/**
	 * Méthode getEchelleVieJoueur
	 * getter de l'attribut echelleVieJoueur
	 */
	public EchelleKarmiqueEnum getEchelleVieJoueur() {
		return echelleVieJoueur;
	}

	/**
	 * Méthode setEchelleVieJoueur
	 * setter de l'attribut echelleVieJoueur
	 * 
	 * @param echelleVieJoueur
	 * type EchelleKarmiqueEnum: insérer une échelle à un joueur
	 */
	public void setEchelleVieJoueur(EchelleKarmiqueEnum echelleVieJoueur) {
		this.echelleVieJoueur = echelleVieJoueur;
	}

	/**
	 * Méthode toString
	 * Retourne une chaine de caractère qui définie l'objet
	 */
	public String toString(){
		return this.echelleVieJoueur.getNom();
	}
}
