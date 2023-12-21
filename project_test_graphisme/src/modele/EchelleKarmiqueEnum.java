package modele;


public enum EchelleKarmiqueEnum {

	Boursier(3, "Boursier"), 
	Serpent(4, "Serpent"), 
	Loup(5, "Loup"), 
	Singe(6, "Singe"), 
	Transcendance(7, "Transcendance");
	
	private int valeur;
	private String nom;
	
	EchelleKarmiqueEnum(int valeur, String nom) {
		this.valeur = valeur;
		this.nom = nom;
	}
	
	public int getEchelleKarmiqueEnum(){
		return this.valeur;
	}

	public String getNom(){
		return this.nom;
	}
}
