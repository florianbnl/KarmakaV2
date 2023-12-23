package modele;

/**
 * Enumération EchelleKarmiqueEnum
 */
public enum EchelleKarmiqueEnum {

	Boursier(3, "Boursier"), 
	Serpent(4, "Serpent"), 
	Loup(5, "Loup"), 
	Singe(6, "Singe"), 
	Transcendance(7, "Transcendance");
	
	private int valeur;
	private String nom;
	
	/**
	 * Constructeur classe EchelleKarmiqueEnum
	 * 
	 * @param valeur
	 * type Integer: la valeur du niveau pour savoir si le joueur peut monter dans l'echelle
	 * 
	 * @param nom
	 * type String: le nom du niveau
	 */
	EchelleKarmiqueEnum(int valeur, String nom) {
		this.valeur = valeur;
		this.nom = nom;
	}
	
	/**
	 * Méthode getEchhelleKarmiqueEnum
	 * getter de l'attribut valeur
	 */
	public int getEchelleKarmiqueEnum(){
		return this.valeur;
	}

	/**
	 * Méthode getNom
	 * getter de l'attribut nom
	 */
	public String getNom(){
		return this.nom;
	}
}
