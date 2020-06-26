package t;

import java.util.Scanner;



public class PieceJointe {
	private String nom;
	private long taille;
	PieceJointe(String nom, int taille) {
		this.nom = nom;
		this.taille = taille;
	}
	public PieceJointe() {
		
	}
	public void saisie() throws MoreThan10485760 {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----Veuillez inserez votre Nom de piece-jointe----");
		nom = scanner.nextLine();
		System.out.println("----Veuillez inserez votre taille de piece-jointe En Octets----");
		System.out.println("****HINT : ça ne doit pas dépasser 10 Mo (10000000 octets)");
		taille = scanner.nextLong();
		if(taille > 10000000) {
			throw( new MoreThan10485760("**Votre taille depasse La taille maximum**"));
		}
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	@Override
	public String toString() {
		return "piece_jointe [nom=" + nom + ", taille=" + taille + "]\n ";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieceJointe other = (PieceJointe) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (taille != other.taille)
			return false;
		
		
		return true;
	}
	
}
