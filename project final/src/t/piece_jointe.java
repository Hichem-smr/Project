package t;

import java.util.Scanner;



public class piece_jointe {
	String nom;
	long taille;
	public piece_jointe(String nom, int taille) {
		this.nom = nom;
		this.taille = taille;
	}
	public piece_jointe() {
		
	}
	void saisie() throws MoreThan10485760 {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----Veuillez inserez votre Nom de piece-jointe----");
		nom = scanner.nextLine();
		System.out.println("----Veuillez inserez votre taille de piece-jointe En octets----");
		System.out.println("****HINT : ça ne doit pas dépasser 10 Mo (10485760 octets)");
		taille = scanner.nextLong(); scanner.nextLine() ;
		if(taille > 10485760) {
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
		return (nom+"-"+taille+"byte");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		piece_jointe other = (piece_jointe) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.contentEquals(other.nom))
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}
	
	
	public int hashCode() {
		return 0;
	}
	
}
