package t;

import java.util.Scanner;

public class profil {
	enum genre {
		FEMININ, MASCULAIN;
	}
	String nom;
	String prenom;
	int age;
	int numero;
	String pays;
	genre type;
	void Saisie_Profil() {
		
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
			type = genre.valueOf("MASCULAIN");
		}
		else if(k == 2) {
			type = genre.valueOf("FEMININ");
		}
		} while(k != 1 || k != 2);
	}
	 
	
}
