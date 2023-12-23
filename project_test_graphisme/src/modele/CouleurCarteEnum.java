package modele;

/**
 * Enumeration CouleurCarteEnum
 */
public enum CouleurCarteEnum{

	Rouge(1),
    Bleu(2),
    Vert(3),
    Mosaique(4);
	
	private int valeur;
	
	/**
	 * Constructeur enumeration CouleurCarteEnum
	 * 
	 * @param valeur
	 * type Integer: la valeur de la couleur
	 */
	public CouleurCarteEnum(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Méthode getCouleurCarteEnum
	 * getter de l'attribut valeur
	 */
	public int getCouleurCarteEnum(){
		return this.valeur;
	}
}
