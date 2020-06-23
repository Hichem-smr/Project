package t;

import java.util.Scanner;

public class Profil {
	enum Genre {
		FEMININ, MASCULAIN;
	}
	private String nom;
	private String prenom;
	private int age;
	private int numero;
	private String pays;
	private Genre genre;
	
	
	public void Saisie_Profil() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----Veuillez insérez votre Nom du profil----");
		nom = scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Prenom du profil----");
		prenom = scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Age----");
		age = scanner.nextInt();
		
		System.out.println("----Veuillez insérez votre Numero de telephone----");
		numero = scanner.nextInt();
		
		System.out.println("----Veuillez insérez votre Pays----");
		pays = scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Genre----");
		int k;
		
		do { 
			System.out.println("----1 Pour masculain----");
			System.out.println("----2 Pour feminin----");
			k = scanner.nextInt();
			if(k == 1) {
				genre = Genre.valueOf("MASCULAIN");
			}
			else if(k == 2) {
				genre = Genre.valueOf("FEMININ");
			}
		} while(k != 1 || k != 2);
	}
	 
	
	public boolean equals(Object o) {
		
		if (o==null)
			return false ;
		
		if(!(o instanceof Profil))
			return false ;
		
		if(numero == ((Profil)o).numero)
			return true ;
		
		return false; 
	}
}
