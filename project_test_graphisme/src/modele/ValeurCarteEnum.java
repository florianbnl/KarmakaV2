package modele;

public enum ValeurCarteEnum {
	UN(1), 
	DEUX(2), 
	TROIS(3);
	
	private int valeur;
	
	ValeurCarteEnum(int valeur) {
		this.valeur = valeur;
	}
	
	public int getValeurCarteEnum(){
		return this.valeur;
	}

}
