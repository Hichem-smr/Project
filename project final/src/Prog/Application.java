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
		//choice must be given to whether prof adress or ordinaire
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
				System.out.println("------Voulez vous ?------");
			int choicce1;
			System.out.println("--1--Modifier votre Psuedo");
			System.out.println("--2--Modifier votre mot de passe");
			System.out.println("--3--Assigner votre adress a un autre profil");
			choicce1 = scanner.nextInt();
			
			switch (choicce1) {
			case 1:
				AdrEmail temp;
				temp = adresses.get(adress);				
				temp.SaisiePsuedo();
				adresses.remove(adress);
				adresses.put(temp.toString(), temp);
				break;
			case 2:
				adresses.get(adress).reintialiserMdp();
				break;
			case 3:
				System.out.println("Quel Profil voulez-vous assigner votre adress avec ?");
				i = 1;
				//we gotta find the adress before deleting it from the profile
				AdrEmail temp1 = null; ;
				for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
					if( (entry.getKey().equals(adress) )) {
						temp1 = entry.getValue();
					}
				}
				
				//we delete the adress from the current profile while displaying
				for(Profil profil : Profiles) {					
					profil.DeleteAdr(adress);
					System.out.println(i + "--" + profil );
					i++;
				}
				int m;
				// m must be between 1 and i
				do {
					m = scanner.nextInt();
				}while(m > i || m<1);
				
				i = 1;
				//we assign the adress to the new profile
				for(Profil profil : Profiles) {
					if(i == m) {
						profil.getAdresses().put(temp1.toString(), temp1);
						break;
					}
				}
				
				
				break;
			}
//			AdrEmail temp = new AdrProf();
//			//type of the address mus tbe preserved
//			temp.saisie();
//			//Adding new modified adress 
//			adresses.put(temp.toString(), temp);
		}
			
		if(choicce == 3) {
			String adress1 ;
			System.out.println("Veuillez inserer votre adresse à supprimer : ");
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
					//pour chaque mail créer une boiteEmail
					Integer k = null;
					if(k == null) {
						System.out.println("Donner votre taille de la boite");
						k = scanner.nextInt();
					}
					
				for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
					BoiteMsg boite_de_messagerie1 = new BoiteMsg();
					boite_de_messagerie1.setCapacité(k);
					Message msg = new Message("Bienvenue","","RECU");
					boite_de_messagerie1.AddReçu(msg);
					entry.getValue().setBoite_de_messagerie(boite_de_messagerie1);
					
				}
				System.out.println("Boite Emails crée !");
					break;
				case 5:
					//crée une nouvelle boite avec une adresse
					int j;
					Integer k1 = null;
					do {
						System.out.println("----Veuillez Choisir votre Address Email ----");
						System.out.println("---1- Address Email Normal ----");
						System.out.println("---2- Address Email Professionnel ----");
						j = scanner.nextInt();
						
						if(j == 1) {
							AdrEmail adress = new AdrEmail() ;
							adress.saisie();
							BoiteMsg BoiteMsg = new BoiteMsg();							
							if(k1 == null) {
								System.out.println("Donner votre taille de la boite");
								k1 = scanner.nextInt();
							}
							BoiteMsg.setCapacité(k1);
							adress.setBoite_de_messagerie(BoiteMsg);	
							Message msg = new Message("Bienvenue","","RECU");
							BoiteMsg.AddReçu(msg);
							
							
						}	
						
						else if(j == 2) {
							AdrProf adress = new AdrProf();
							adress.saisie();
							BoiteMsg BoiteMsg = new BoiteMsg();							
							if(k1 == null) {
								System.out.println("Donner votre taille de la boite");
								k = scanner.nextInt();
							}
							BoiteMsg.setCapacité(k1);
							adress.setBoite_de_messagerie(BoiteMsg);
							Message msg = new Message("Bienvenue","","RECU");
							BoiteMsg.AddReçu(msg);
						}
						
					} while(j != 1 && j != 2);
					
					
				case 6:
					for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
						System.out.println("Adress : " + entry.getKey());
						entry.getValue().getBoite_de_messagerie().AfficheBoite();
					}
					
				case 7:
					int choice;
					do {
						System.out.println("------Voulez vous ?------");
						System.out.println("1. Ajout automatique de messages (reçus, envoyés, brouillons)");
						System.out.println("2. Afficher le contenu d’une boite");
						System.out.println("3. Envoyer message");
						System.out.println("4. Afficher le contenu d’un message");
						System.out.println("5. Supprimer un message");
						System.out.println("6. Archiver un message/messages reçus avant une date d donnée");
						System.out.println("7. Restaurer un message donné");
						System.out.println("8. répondre à un message");
						System.out.println("9. vider un dossier (« spam » ou « envoyés »)");
						choice = scanner.nextInt() ;
					}while(choice > 9 || choice < 1);
					
					switch (choice) {
					case 1:
						
						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					case 5:
						
						break;
					case 6:
						
						break;
					case 7:
						
						break;
					case 8:
						
						break;
					case 9:
						
						break;
					}
					
					
					
				break;	
					
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
