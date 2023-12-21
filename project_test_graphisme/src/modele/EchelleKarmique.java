package modele;

import java.io.Serializable;

public class EchelleKarmique implements Serializable {

	private EchelleKarmiqueEnum echelleVieJoueur;
	
	public EchelleKarmique() {
		this.setEchelleVieJoueur(EchelleKarmiqueEnum.Boursier);
	}

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

	public EchelleKarmiqueEnum getEchelleVieJoueur() {
		return echelleVieJoueur;
	}

	public void setEchelleVieJoueur(EchelleKarmiqueEnum echelleVieJoueur) {
		this.echelleVieJoueur = echelleVieJoueur;
	}

	public String toString(){
		return this.echelleVieJoueur.getNom();
	}
}
