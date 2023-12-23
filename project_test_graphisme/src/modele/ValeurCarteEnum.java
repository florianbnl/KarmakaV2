package modele;

/**
 * Enumération ValeurCarteEnum
 */
public enum ValeurCarteEnum {
	UN(1), 
	DEUX(2), 
	TROIS(3);
	
	private int valeur;
	
	/**
	 * Constructeur enumération ValeurCarteEnum
	 * 
	 * @param valeur
	 * type Integer: la valeur de la carte
	 */
	ValeurCarteEnum(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Méthode getValeurCarteEnum
	 * getter de l'attribut valeur
	 */
	public int getValeurCarteEnum(){
		return this.valeur;
	}

}
