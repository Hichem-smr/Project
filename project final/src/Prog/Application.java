package Prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


import t.AdrEmail;
import t.AdrProf;
import t.BoiteMsg;
import t.Message;
import t.Profil;

public class Application {

	public static HashMap<String ,AdrEmail> adresses = new HashMap<String ,AdrEmail>() ;
	public static HashSet<Profil> Profiles = new HashSet<Profil>() ;

	public static void creerAdr() {
		
		Scanner scanner = new Scanner(System.in) ;
		int j ;
		int i ;
		do {
			do {
				System.out.println("----Veuillez Choisir votre Address Email ----");
				System.out.println("---1- Address Email Normal ----");
				System.out.println("---2- Address Email Professionnel ----");
				j = scanner.nextInt();
				if(j == 1) {
					AdrEmail adr = new AdrEmail() ;
					adr.saisie();
					System.out.println("----------Creation du profil--------------");
					Profil profil = new Profil();
					profil.saisieProfil();
					profil.getAdresses().put(adr.toString(), adr);
					Profiles.add(profil);
					
				}				
				else if(j == 2) {
					AdrProf adr = new AdrProf();
					adr.saisie();
					System.out.println("----------Creation du profil--------------");
					Profil profil = new Profil();
					profil.saisieProfil();
					profil.getAdresses().put(adr.toString(), adr);
					Profiles.add(profil);
				}				
			} while(j != 1 && j != 2);
			System.out.println("--1--insérer d'autre Adresses & Profils");
			System.out.println("--0--Quitter.");						
			i = scanner.nextInt();
		}while(i != 0);
	}
	
	
	public static void ajouterAdr() {
		int i , j ;
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("------Voulez vous ?------");
		int choicce;
		System.out.println("--1--Ajouter Une Adresse Email");
		System.out.println("--2--Modifier Une Adresse Email");
		System.out.println("--3--Supprimer Une Adresse Email");
		
		choicce = scanner.nextInt();
		
		if(choicce == 1) {
			AdrProf adr = new AdrProf();
			adr.saisie();
			adresses.put(adr.toString(), adr);
			if(Profiles.size() == 0) {
				System.out.println("Il n'existe pas de Profils pour assigner cette adress");
			} else {
				//select profile
				System.out.println("Veuillez selectionner quel profil Voulez vous assigner cette adresse : ");
				i = 1;
				
				ArrayList<Profil> profiles = new ArrayList(Profiles) ;
				for(Profil profil : profiles ) {
					System.out.println(i + "--" + profil.toString());
					i++;
				}
				
				do {
					System.out.println("Veuillez donner un nombre entre 1 et " + profiles.size()+1);
					j = scanner.nextInt(); 		scanner.nextLine();
				}while(j<1 || j > profiles.size() + 1 ) ;
				
				Profil profil = profiles.get(j-1);
				Profiles.remove(profil) ;
				profil.getAdresses().put(adr.toString(), adr) ;
				Profiles.add(profil) ; 
			}
			
			
		} else
		
		if(choicce == 2) {
			String adress ;
			System.out.println("Veuillez inserer votre adresse à changer : ");
			adress = scanner.nextLine();
			if(!adresses.containsKey(adress)) {
				System.out.println("Cette adresse n'existe pas");
			} 
			else 
			//remove the old addresses because of its toString	key
			adresses.remove(adress);
			AdrEmail temp = new AdrProf();
			temp.saisie();
			//Adding new modified adress 
			adresses.put(temp.toString(), temp);
		}
			
		if(choicce == 3) {
			String adress1 ;
			System.out.println("Veuillez inserer votre adresse à changer : ");
			adress1 = scanner.nextLine();
			if(!adresses.containsKey(adress1)) {
				System.out.println("Cette adresse n'existe pas");
			}
			else
			adresses.remove(adress1);
		}
	}
	
	
	public static void afficherAdr() {
		
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("----Veuillez specifier quelle categorie vous voulez afficher----");
		int choice;
		do {
			System.out.println("--1--Pour Adresse Email Standard");
			System.out.println("--2--Pour Adresse Email Professionnel");
			System.out.println("--3--Pour Un affichage par site");
			choice = scanner.nextInt();
			
			if(choice == 1) {
				for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
					if(!(entry.getValue() instanceof AdrProf)) {
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
			
			if(choice == 3) {
				TreeSet <AdrEmail> TS = new TreeSet <AdrEmail> (adresses.values());
				String site = "" ;
				for(AdrEmail e : TS) {
					if(e.getSite()!= site ) {
						site = e.getSite() ;
						System.out.println(site);
					}
						
					System.out.println("\t"+e);
				}
			}
			
			System.out.println("Fin d'adresses");
		}while(choice<1 || choice>3);
	}
	
	
	
	public static void main(String[] args) {
		
		
		AdrEmail adr [] = new AdrEmail [6];
		AdrProf adr1 [] = new AdrProf [2];
		adr[0] =  new AdrEmail("shadow","outlook.com","azerfazer12$") ;
		adr[1] = new AdrEmail("ngedmond","hotmail.com","azerfazer12$");
		adr[2] = new AdrEmail("tedrlord","gmail.com","azerfazer12$");
		adr[3] = new AdrEmail("crobles","gmail.com","azerfazer12$");
		adr[4] = new AdrEmail("janneh","live.com","azerfazer12$");
		adr[5] = new AdrEmail("andale","verizon.net","azerfazer12$");
		adr1[0] = new AdrProf("markzuckerberg","facebook.com","azerfazer12$","médias sociaux","facebook");
		adr1[1] = new AdrProf("sboukhedouma","usthb.dz","azerfazer12$","Enseignement","usthb"); 
		AdrEmail adr2 = null;
		int y = 0;		
		//filling up the addresses hashmap.
		for(int i = 0 ; i<6 ; i++) {			
			adresses.put(adr[i].toString(), adr[i]) ;						
		}
		for(int i =0; i<2; i++) {
			adresses.put(adr1[i].toString(), adr1[i]);	
		}
		
		
		
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("**********BIENVENU AU PROJET FINAL DU MODULE POO*************");
		System.out.println("------Voulez vous ?------");
		int choix;
		
		do {
			choix = menu();
			
			switch (choix) {
				case 1:
					creerAdr() ;
					break;
					
				case 2:	
					ajouterAdr() ;
					break;
					
				case 3:
					afficherAdr() ;
					break;
					
				case 4:
					BoiteMsg BoiteMsg = new BoiteMsg();
					Integer k = null;
					if(k == null) {
						System.out.println("Donner votre taille de la boite");
						k = scanner.nextInt();
					}
					BoiteMsg.setCapacité(k);
//					BoiteMsg.
					
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
