package modele;

public enum CouleurCarteEnum{

	Rouge(1),
    Bleu(2),
    Vert(3),
    Mosaique(4);
	
	private int valeur;
	
	CouleurCarteEnum(int valeur) {
		this.valeur = valeur;
	}
	
	public int getCouleurCarteEnum(){
		return this.valeur;
	}
}
