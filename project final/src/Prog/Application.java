package Prog;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import t.AdrEmail;
import t.AdrProf;
import t.BoiteMsg;
import t.Message;
import t.Profil;

public class Application {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("**********BIENVENU AU PROJET FINAL DU MODULE POO*************");
		System.out.println("------Voulez vous ?------");
		int choix;
		HashMap<String ,AdrEmail> adresses = new HashMap<String ,AdrEmail>() ;
		do {
			choix = menu();
			

			switch (choix) {
				case 1:
					int j ;
					int i ;
					do {
						do {
							System.out.println("----Veuillez Choisir votre Address Email ----");
							System.out.println("---1- Address Email Normal ----");
							System.out.println("---2- Address Email Professionnel ----");
							j = scanner.nextInt();
							if(j == 1) {
								AdrEmail adr = new AdrEmail();
								adr.saisie();
//								System.out.println("----------Creation du profil--------------");
//								adr.getProfil().saisieProfil();
								adresses.put(adr.toString(), adr) ;
								
							}				
							else if(j == 2) {
								AdrProf adr = new AdrProf();
								adr.saisie();
								System.out.println("----------Creation du profil--------------");
								adr.getProfil().saisieProfil();
								adresses.put(adr.toString(), adr) ;
								
							}				
						} while(j != 1 && j != 2);
						System.out.println("--1--insérer d'autre Adresses");
						System.out.println("--0--Quitter.");						
						i = scanner.nextInt();
					}while(i != 0);
					break;
					
					
				case 2:	
					
				case 3:
					System.out.println("----Veuillez specifier quelle categorie vous voulez afficher----");
					int choice;
					do {
						System.out.println("--1--Pour Adresse Email Standard");
						System.out.println("--2--Pour adresse Email Professionnel");
						choice = scanner.nextInt();
						
						if(choice == 1) {
							for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
								if(entry.getValue() instanceof AdrEmail) {
									System.out.println("----" + entry.getKey());
								}
							}
						} else
						if(choice == 2) {
							for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
								if(entry.getValue() instanceof AdrProf) {
									System.out.println("----" + entry.getKey());
								}
							}
						}
						System.out.println("Fin d'adresses");
					}while(choice<1 || choice>2);
					
					break;
					
					

					
				case 4:
					BoiteMsg BoiteMsg = new BoiteMsg();
					Integer k = null;
					if(k == null) {
						System.out.println("Donner votre taille de la boite");
						k = scanner.nextInt();
					}
					BoiteMsg.setCapacité(k);
					BoiteMsg.
					
				case 5:
					
				case 6:
					
				case 7:
					
				case 8: 
				System.out.println("Its the end");
				break;
				
				
			}
		}while(choix != 8);
		
		
		
		
		
		
		
		
		

	}

	
	public static int menu() {
		int z;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("1. Créer des adresses e-mail (avec les profils)");
			System.out.println("2. Ajouter/suppr/modifier une adresse");
			System.out.println("3. Afficher des adresses créées (par catégorie)");
			System.out.println("4. Création des boites e-mail correspondantes");
			System.out.println("5. Ajouter une boite");
			System.out.println("6. Afficher des boites e-mails (avec leur contenu)");
			System.out.println("7. Gestion des boites e-mails");
			System.out.println("8. Quitter");		
			z = scanner.nextInt();	
		}while(z<1 || z>8);
		return z;
		
	}
	
	
}
