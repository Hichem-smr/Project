package Prog;

import java.util.ArrayList;
import java.util.Date;
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
import t.ExceptionPieceExistante;
import t.Message;
import t.MessageAttach;
import t.MoreThan10485760;
import t.Profil;
import t.destinataire_incorrecte;
import t.message_vide;
import t.piece_jointe;

public class Application {

	public static HashMap<String ,AdrEmail> adresses = new HashMap<String ,AdrEmail>() ;
	public static HashMap<String ,AdrEmail> Profadresses = new HashMap<String ,AdrEmail>() ;
	public static HashMap<String ,AdrEmail> Normadresses = new HashMap<String ,AdrEmail>() ;
	public static ArrayList<String> list = new ArrayList<String>(); 
	public static HashSet<Profil> Profiles = new HashSet<Profil>() ;
	public static HashMap<String ,Message> Messages = new HashMap<String ,Message>() ;

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
	
	
	
	public static void main(String[] args) throws destinataire_incorrecte, MoreThan10485760, ExceptionPieceExistante, message_vide {
		
		
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
		
		Message Msg [] = new Message [5];
		MessageAttach MsgAttach [] = new MessageAttach [2];
		Msg[0] = new Message("Cours Systeme","un cours est programmé samedi à 10h00");
		Msg[1] = new Message("Cours POO","un cours est programmé samedi à 9h00");
		Msg[2] = new Message("Cours Archi","un cours est programmé samedi à 14h00");
		Msg[3] = new Message("Cours Bdd","un cours est programmé samedi à 11h00");
		Msg[4] = new Message("Cours Python","un cours est programmé samedi à 13h00");	
		
		piece_jointe piece = new piece_jointe("Serie d'exercices THL",10);
		MsgAttach[0] = new MessageAttach("Cours THL","un cours est programmé samedi à 8h00",piece);
		piece = new piece_jointe("Serie d'exercices Anglais",10);
		MsgAttach[1] = new MessageAttach("Cours Anglais","un cours est programmé samedi à 15h00",piece);
		
		for(int i = 0 ; i<4 ; i++) {			
			Messages.put(Msg[i].getTitre(), Msg[i]) ;						
		}
		for(int i =0; i<2; i++) {
			Messages.put(MsgAttach[i].getTitre(), MsgAttach[i]);	
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
					Message msg = new Message("Bienvenue","");
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
							Message msg = new Message("Bienvenue","");
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
							Message msg = new Message("Bienvenue","");
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
						System.out.println("10. trier les messages par date et par objet");
						System.out.println("11. Quitter");
						choice = scanner.nextInt() ;
					}while(choice > 10 || choice < 1);
					
					switch (choice) {
					case 1:
						
						adr[0].getBoite_de_messagerie().envoyerMsg( Msg[0],"ngedmond@hotmail.com");
						adr[1].getBoite_de_messagerie().envoyerMsg( Msg[1],"tedrlord@gmail.com");
						adr[2].getBoite_de_messagerie().envoyerMsg( Msg[2],"crobles@gmail.com");
						adr[3].getBoite_de_messagerie().envoyerMsg( Msg[3],"janneh@live.com");
						adr[4].getBoite_de_messagerie().envoyerMsg( Msg[4],"andale@verizon.net"); 
						adr[5].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"markzuckerberg@facebook.com");
						adr1[0].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"sboukhedouma@usthb.dz");
						adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
						break;
						
					case 2:
						System.out.println("Veuillez inserer l'adresse de votre boite-Email : ");
						System.out.println(adresses.keySet());
						scanner.nextLine();
						String adress = scanner.nextLine();
						adresses.get(adress).getBoite_de_messagerie().AfficheBoite();
						System.out.println("Espace Utilisé : " + adresses.get(adress).getBoite_de_messagerie().SpaceUsed());
						System.out.println("Espace restant : " + adresses.get(adress).getBoite_de_messagerie().SpaceLeft());
						break;
						
					case 3:
						System.out.println("Veuillez inserer votre adresse Email : ");
						System.out.println(adresses.keySet());
						scanner.nextLine();
						String adressSender = scanner.nextLine();
						System.out.println("Veuillez inserer votre adresse destinataire");
						String AdressReciever = scanner.nextLine();
						MessageAttach msg12 = new MessageAttach() ;
						msg12.saisie();
						adresses.get(adressSender).getBoite_de_messagerie().envoyerMsg(msg12, AdressReciever);
						Messages.put(msg12.getTitre(), msg12);
						break;
						
					case 4:
						System.out.println("Veuillez inserer l'object de votre message : ");
						scanner.nextLine();
						String MessageTitre = scanner.nextLine();
						if(Messages.containsKey(MessageTitre)) {
							System.out.println("Titre : "   + Messages.get(MessageTitre).getTitre());
							System.out.println("Contenu : " + Messages.get(MessageTitre).getContenu());
						} else 
							System.out.println("Ce message n'existe Pas");
						break;
					case 5:
						System.out.println("Veuillez inserer votre adresse Email : ");
						System.out.println(adresses.keySet());
						scanner.nextLine();
						adressSender = scanner.nextLine();
						System.out.println("Veuillez inserer l'object de votre message a supprimer : ");
						scanner.nextLine();
						MessageTitre = scanner.nextLine();
						if(Messages.containsKey(MessageTitre)) {
							adresses.get(MessageTitre).getBoite_de_messagerie().suprrimerMsg();
						} else 
							System.out.println("Ce message n'existe Pas");
						
						break;
					case 6:
						do {
							System.out.println(adresses.keySet() + "\n");
							System.out.println("Veuillez inserer votre Adresse Email : ?");
							scanner.nextLine();
							adress = scanner.nextLine();
							Date datee = null;							
							
							if(datee == null) {
								System.out.println("Veuillez inserer votre date : ");
								int sec,hour,day,month,year,min;
								
								System.out.println("Année : ?");
								year = scanner.nextInt();
								
								System.out.println("Mois : ?");
								month = scanner.nextInt();
								
															
								System.out.println("Jour : ?");
								day = scanner.nextInt();
								
								System.out.println("Heure : ?");
								hour = scanner.nextInt();
								
								System.out.println("Min : ?");
								min = scanner.nextInt();
								
								System.out.println("Seconde : ?");
								sec = scanner.nextInt();
								
								datee = new Date(year, month, min, hour, min, sec); 
							}
							
							//msg isnt deleted after the archive add 
							for(Message message : adresses.get(adress).getBoite_de_messagerie().getRecus()) {
								if(message.getCreation().before(datee)) {
									 adresses.get(adress).getBoite_de_messagerie().AddArchive(message);
								}
							}
							
							System.out.println("Voulez vous ajouter d'autre Adresses?");
							System.out.println("--1-- Oui");
							System.out.println("--2-- Non");
							scanner.nextLine();
							choice = scanner.nextInt();
							
							
							
						}while(choice != 2);
						break;
					case 7:
						System.out.println("Veuillez inserer l'objet de votre message a restaurer");
						scanner.nextLine();
						adress = scanner.nextLine();
						if(adresses.get(adress).getBoite_de_messagerie().getCorbeille().contains(adress)) {
							System.out.println("A quel dossier voulez vous restaurer ce message : ?");
							System.out.println("--1--Reçus");
							System.out.println("--2--Envoyés");
							scanner.nextLine();
							choice = scanner.nextInt();
							if(choice == 1) {
								adresses.get(adress).getBoite_de_messagerie().getReçus().add(Messages.get(adress));
							} else 
								
							if(choice == 2) {
								adresses.get(adress).getBoite_de_messagerie().getEnvoyés().add(Messages.get(adress));
							}
							//remove the message from corbeille
							adresses.get(adress).getBoite_de_messagerie().getCorbeille().remove(Messages.get(adress));
							
						} else 
							System.out.println("Ce message n'existe pas");
						
						
						
						break;
					case 8:
						System.out.println("Veuillez inserer votre adress Email : ");
						scanner.nextLine();
						adress = scanner.nextLine();
						System.out.println("Veuillez inserer l'objet du message : ");
						scanner.nextLine();
						String message = scanner.nextLine();
						if(!adresses.get(adress).getBoite_de_messagerie().getRecus().contains(message)) {
							System.out.println("Ce message n'as pas été reçu pas");
						} else {
							
							if(Messages.get(message) instanceof Message) {
								Message msg3 = new Message();
								
								msg3.setTitre("re-".concat(Messages.get(message).getTitre()));
								System.out.println("Veuillez saisir votre reponse : ");
								String contenu = scanner.nextLine();
								msg3.setContenu(contenu);
								Messages.put(msg3.toString(), msg3);
								//for every adress, if the objject sent is the same as recieved in the last email means it was a sender
								//and it will recieve an answer 
								for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
									for(Message message1 : adresses.get(entry.getValue()).getBoite_de_messagerie().getEnvoyes()) {
										// si le titre est equivalent
										if(message1.getTitre().equals(message)) {
											entry.getValue().getBoite_de_messagerie().AddReçu(msg3);
										}
									}
								}
							} 
								//else
//							
//							if(Messages.get(message) instanceof MessageAttach) {
//								
//								Message msg3 = new MessageAttach("re-".concat(Messages.get(message).getTitre()), "",Messages.get(message).get) );
//								msg3.setTitre("re-".concat(Messages.get(message).getTitre()));
//								System.out.println("Veuillez saisir votre reponse : ");
//								String contenu = scanner.nextLine();
//								
//								for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
//									for(Message message1 : adresses.get(entry.getValue()).getBoite_de_messagerie().getEnvoyes()) {
//										// si le titre est equivalent
//										if(message1.getTitre().equals(message)) {
//											
//										}
//									}
//								}
//							}
							
							
								
						}	
							
						
						
						
						break;
					case 9:
						System.out.println("Veuillez inserer votre adress Email : ");
						scanner.nextLine();
						adress = scanner.nextLine();
						System.out.println("Veuillez entrer quel dossier voulez vous supprimer ?");
						System.out.println("--1--Spam ? ");
						System.out.println("--2--Envoyés ? ");
						scanner.nextLine();
						choice = scanner.nextInt();
						if(choice == 1) {
							for(Message msg : adresses.get(adress).getBoite_de_messagerie().getSpam() ) {
								
								adresses.get(adress).getBoite_de_messagerie().getSpam().remove(msg);
								Messages.remove(msg);
							}
						} else 
						if(choice == 2) {
							for(Message msg : adresses.get(adress).getBoite_de_messagerie().getEnvoyés() ) {
								adresses.get(adress).getBoite_de_messagerie().getEnvoyés().remove(msg);
								Messages.remove(msg);
							}
						}
						break;
						
					case 10:
						System.out.println(adresses.keySet());
						System.out.println("Veuillez inserer votre Adress-Email : ?");
						scanner.nextLine();
						adress = scanner.nextLine();
						System.out.println("Veuillez entrer quel dossier voulez vous Trier ?");
						System.out.println("--1--Spam ? ");
						System.out.println("--2--Envoyés ? ");
						System.out.println("--3--Reçus ?");					
						System.out.println("--4--brouillons ? ");
						System.out.println("--5--archives ? ");
						System.out.println("--6--corbeille ? ");
						scanner.nextLine();
						do {
							
							choix = scanner.nextInt();
						}while(choix < 1 || choix > 6);
						 HashSet dossier = new HashSet(); 
						switch (choix) {
						case 1:
							dossier	= (HashSet) adresses.get(adress).getBoite_de_messagerie().getSpam().clone();
							break;
						case 2:
							dossier = (HashSet) adresses.get(adress).getBoite_de_messagerie().getEnvoyes().clone();
							break;
						case 3:
							dossier = (HashSet) adresses.get(adress).getBoite_de_messagerie().getReçus().clone();
							break;
						case 4:
							dossier	= (HashSet) adresses.get(adress).getBoite_de_messagerie().getBrouillons().clone();
							break;
						case 5:
							dossier	= (HashSet) adresses.get(adress).getBoite_de_messagerie().getArchives().clone();
							break;
						case 6:
							dossier = (HashSet) adresses.get(adress).getBoite_de_messagerie().getCorbeille().clone();
							break;
						}
						TreeSet <Message> SortedMessages = new TreeSet <Message> (dossier);
						
						System.out.println(SortedMessages);
						
						
						break;
						
					}
					
					
					
				break;	
					
				case 8: 
					do {						
						System.out.println("1. Afficher toutes les boites ayant reçu un message donné : ");
						System.out.println("2. Afficher les boites qui sont remplies à plus de 50% de leur capacité : ");
						System.out.println("3. Eclater la collection des boites en deux collections :");					
						System.out.println("4. Calcul du pourcentage d'utilisation pour a site donnée : ");
						System.out.println("5. Afficher les messages ayant des pièces jointes (pour une boite donnée)");
						System.out.println("6. Rechercher les messages par destinataire, par expéditeur, par mot clé.");
						System.out.println("7. Afficher les noms, prénoms des profils ayant au moins deux boites de messagerie.");  							
						System.out.println("8. Vider toutes les boites d’un site donné");
						choice = scanner.nextInt() ;
					}while(choice > 8 || choice < 1);
					switch (choice) {
					
					case 1: 
						System.out.println("Veuillez inserer l'objet de votre message : ?");
						String message = scanner.nextLine();
						//all messages must exist in the Messages hashshet
						for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
							if(entry.getValue().getBoite_de_messagerie().getRecus().contains(Messages.get(message))) {
								System.out.println(entry.getKey());
							}
						}
						break;
					case 2: 
						for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
							if(entry.getValue().getBoite_de_messagerie().getCapacité()*0.5 < entry.getValue().getBoite_de_messagerie().SpaceLeft()) {
								System.out.println(entry.getKey());
							}
						}
						break;
					case 3: 
						for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
							if(!(entry.getValue() instanceof AdrProf)) {
								Normadresses.put(entry.getKey(), entry.getValue());
							} else
							if(entry.getValue() instanceof AdrProf) {
								Profadresses.put(entry.getKey(), entry.getValue());
							}
						}
						break;
					case 4: 
						//haven't intialized the profiles yet
						
						
						System.out.println("Veuillez inserer votre site");
						String site = scanner.nextLine();
						int total = 0;
						for(Profil profile : Profiles) {
							if(profile.getAge() >= 18 && profile.getAge() <= 35) {
								
								for( Map.Entry<String, AdrEmail> entry : profile.getAdresses().entrySet() ) { 
									if(entry.getValue().getSite().equals(site)) {
										total++;
										list.add(String.valueOf(profile.getAge()));
									}
								}															
							}
						}
						
						Map<String, Integer> hm = new HashMap<String, Integer>(); 
						  
				        for (String i : list) { 
				            Integer occurance= hm.get(i); 
				            hm.put(i, (occurance == null) ? 1 : occurance + 1); 
				        } 
				  
				        // displaying the occurrence of elements in the arraylist 
				        for (Map.Entry<String, Integer> val : hm.entrySet()) { 
				            System.out.println("Age : " + val.getKey() + " "
				                               + "Pourcentage : "
				                               + ": " + (val.getValue()/total)*100 + "%"); 
				        } 
				     
						 
						break;
					case 5: 
						
						break;
					case 6: 
						break;
					case 7: 
						
						break;
					case 8: 
						
						break;
					}
					
					
				break;
				
				case 9:
					break;
				
			}
		}while(choix != 9);
		
		
		
		
		
		
		
		
		

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
			System.out.println("8. Autres requêtes :");		
			System.out.println("9. Quitter");
			z = scanner.nextInt();	
		}while(z<1 || z>9);
		return z;
		
	}
	
	
}
