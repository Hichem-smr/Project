package Prog;

import java.util.ArrayList;
import java.util.Collections;
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
import t.Comparateur;
import t.ExceptionPieceExistante;
import t.Message;
import t.MessageAttach;
import t.MoreThan10485760;
import t.Profil;
import t.destinataire_incorrecte;
import t.message_vide;
import t.piece_jointe;
import t.Etat;


public class Application {

	public static HashMap<String ,AdrEmail> adresses = new HashMap<String ,AdrEmail>() ;
	public static HashMap<String ,AdrEmail> Profadresses = new HashMap<String ,AdrEmail>() ;
	public static HashMap<String ,AdrEmail> Normadresses = new HashMap<String ,AdrEmail>() ;
	public static ArrayList<String> list = new ArrayList<String>(); 
	public static HashSet<Profil> Profiles = new HashSet<Profil>() ;
	public static HashMap<String ,Message> Messages = new HashMap<String ,Message>() ;
	public static HashMap<Message, String > Messages1 = new HashMap<Message, String >() ;
	static AdrEmail adr [] = new AdrEmail [6];
	static AdrProf adr1 [] = new AdrProf [2];

	public static void creerAdr() {
		
		adr[0] =  new AdrEmail("shadow","outlook.com","azerfazer12$") ;
		adr[1] = new AdrEmail("ngedmond","hotmail.com","azerfazer12$");
		adr[2] = new AdrEmail("tedrlord","gmail.com","azerfazer12$");
		adr[3] = new AdrEmail("crobles","gmail.com","azerfazer12$");
		adr[4] = new AdrEmail("janneh","live.com","azerfazer12$");
		adr[5] = new AdrEmail("andale","verizon.net","azerfazer12$");
		adr1[0] = new AdrProf("markzuckerberg","facebook.com","azerfazer12$","médias sociaux","facebook");
		adr1[1] = new AdrProf("sboukhedouma","usthb.dz","azerfazer12$","Enseignement","usthb"); 
		AdrEmail adr2 = null;
		//filling up the addresses hashmap.
		for(int i = 0 ; i<6 ; i++) {			
			adresses.put(adr[i].toString(), adr[i]) ;						
		}
		for(int i =0; i<2; i++) {
			adresses.put(adr1[i].toString(), adr1[i]);	
		}
		
		Profil profil1 = new Profil("Zuckerberg", "Mark", 24, "0556600845", "Etats unis", "MASCULAIN");
		profil1.getAdresses().put(adr1[0].toString(), adr1[0]);
		profil1.getAdresses().put(adr[3].toString(), adr[3]);
		Profiles.add(profil1);
		
			
		Profil profil2 = new Profil("Boukhedouma", "S", 34, "0556646845", "Algerie", "FEMININ");
		profil2.getAdresses().put(adr1[1].toString(), adr1[1]);
		profil2.getAdresses().put(adr[5].toString(), adr[5]);
		Profiles.add(profil2);
		
		
		Profil profil3 = new Profil("matoub", "lounes", 48, "0556689845", "Algerie", "MASCULAIN");
		profil3.getAdresses().put(adr[4].toString(), adr[4]);
		profil3.getAdresses().put(adr[2].toString(), adr[2]);
		Profiles.add(profil3);
		
		
		Profil profil4 = new Profil("chikhi", "abbdle hak", 19, "0556634845", "Algerie", "MASCULAIN");
		profil4.getAdresses().put(adr[0].toString(), adr[0]);
		profil4.getAdresses().put(adr[1].toString(), adr[1]);
		Profiles.add(profil4);
		
		
		Profil profil5 = new Profil("Ameziane", "abbdle ghani", 20, "0559873980", "Algerie", "MASCULAIN");
		Profiles.add(profil5);
		
		Profil profil6 = new Profil("Semmar", "Hichem", 20, "0779862538", "Algerie", "MASCULAIN");
		Profiles.add(profil6);

	}
	
	
	public static void ajouterAdr() {
		int i , j ;
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("------Voulez vous ?------");
		int choicce;
//		scanner.nextLine();
		System.out.println("--1--Ajouter Une Adresse Email");
		System.out.println("--2--Modifier Une Adresse Email");
		System.out.println("--3--Supprimer Une Adresse Email");
		
		AdrEmail adr = null ;
		
		choicce = scanner.nextInt();
		//choice must be given to whether prof adress or ordinaire
		if(choicce == 1) {
			
			do {
				System.out.println("Voulez vous un adresse publique ou proffesionel ?");
				System.out.println("\t1.Publique\n\t2.Proffesionelle");
				choicce = scanner.nextInt() ; scanner.nextLine() ;
			}while(choicce!=1 && choicce!=2 );
			
			if(choicce==1)
				adr = new AdrEmail() ;
			else
				adr= new AdrProf() ;
			
			
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
					System.out.println("Veuillez donner un nombre entre 1 et " + profiles.size());
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
			scanner.nextLine();
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
				String pseudo = temp.SaisiePsuedo();
				temp.setPseudo(pseudo);
				
				adresses.remove(adress);
				adresses.put(temp.toString(), temp);
				System.out.println(temp.toString());
				break;
			case 2:
				temp = adresses.get(adress);
				String mdp = temp.reintialiserMdp();
				temp.setMdp(mdp);
				
				adresses.remove(adress);				
				adresses.put(temp.toString(), temp);
				
				System.out.println(temp.getMdp());
				break;
			case 3:
				
				i = 1;
				//we gotta find the adress before deleting it from the profile
				AdrEmail temp1 = adresses.get(adress);
			
				
				//we delete the adress from the current profile
				for(Profil profil : Profiles) {					
					if(profil.getAdresses().containsKey(adress)) {
						profil.DeleteAdr(adress);
					} 
				}
				
				System.out.println("Quel Profil voulez-vous assigner votre adress avec ?");
				for(Profil profil : Profiles) {
					System.out.println(i + "--" + profil);
					i++;
				}
				
				int profileChoice = scanner.nextInt();
				
				
				i = 1;
				
				for(Profil profil : Profiles) {
					if(!(profileChoice == i)) {
						i++;
					} 
					else {
						System.out.println("Before : " + profil.getAdresses());
						break;
					}
						
				}
				i=1;
				
				for(Profil profil : Profiles) {
					if(!(profileChoice == i)) {
						i++;
					} 
					else {
						profil.getAdresses().put(temp1.toString(), temp1);
					}
						
				}
				
				
				i = 1;
				
				for(Profil profil : Profiles) {
					if(!(profileChoice == i)) {
						i++;
					} 
					else {
						System.out.println("After :" + profil.getAdresses());
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
			scanner.nextLine();
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
	
	
	public static void creerBoite() {
		
		Scanner scanner = new Scanner(System.in) ;
		//pour chaque mail créer une boiteEmail
		Integer k = null;
		if(k == null) {
			System.out.println("Donner votre taille de la boite (en MO)");
			k = scanner.nextInt(); scanner.nextLine();
		}
		
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
		
			if(entry.getValue().getBoite_de_messagerie()!=null)
			continue ;
		
			BoiteMsg boite_de_messagerie1 = new BoiteMsg();
			boite_de_messagerie1.setCapacité(1024*1024*k);
			Message msg = new Message("Bienvenue","");
			boite_de_messagerie1.AddReçu(msg);
			entry.getValue().setBoite_de_messagerie(boite_de_messagerie1);
		
		}
		System.out.println("Boite Emails crée !");
	}
	
	
	public static void ajouterBoite() {
		
		Scanner scanner = new Scanner(System.in) ;
		//crée une nouvelle boite avec une adresse
		int j;
		Integer k1 = null;
		AdrEmail adress = null ;
		do {
			System.out.println("----Veuillez Choisir votre Address Email ----");
			System.out.println("---1- Address Email Normal ----");
			System.out.println("---2- Address Email Professionnel ----");
			j = scanner.nextInt(); scanner.nextLine();
			
			
			
			if(j == 1) {
				 adress = new AdrEmail() ;
				adress.saisie();
				BoiteMsg BoiteMsg = new BoiteMsg();							
				if(k1 == null) {
					System.out.println("Donner votre taille de la boite (en MO)");
					k1 = scanner.nextInt(); scanner.nextLine();
				}
				BoiteMsg.setCapacité(k1*1024*1024);
				adress.setBoite_de_messagerie(BoiteMsg);	
				Message msg = new Message("Bienvenue","");
				BoiteMsg.AddReçu(msg);
				adresses.put(adress.toString(), adress);
			}	
			
			else if(j == 2) {
				adress = new AdrProf();
				adress.saisie();
				BoiteMsg BoiteMsg = new BoiteMsg();							
				if(k1 == null) {
					System.out.println("Donner votre taille de la boite");
					k1 = scanner.nextInt(); scanner.nextLine();
				}
				BoiteMsg.setCapacité(k1*1024*1024);
				adress.setBoite_de_messagerie(BoiteMsg);
				Message msg = new Message("Bienvenue","");
				BoiteMsg.AddReçu(msg);
				adresses.put(adress.toString(), adress);
			}
			
		} while(j != 1 && j != 2);
		
		int i ;
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
				System.out.println("Veuillez donner un nombre entre 1 et " + profiles.size());
				j = scanner.nextInt(); 		scanner.nextLine();
			}while(j<1 || j > profiles.size() + 1 ) ;
			
			Profil profil = profiles.get(j-1);
			Profiles.remove(profil) ;
			profil.getAdresses().put(adress.toString(), adress) ;
			Profiles.add(profil) ; 
		}
	}

	
	public static void afficherBoite() {
		
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
			System.out.println("Adress : " + entry.getKey());
			entry.getValue().getBoite_de_messagerie().AfficheBoite();
		}
	}
	
	
	public static void ajoutMessage(Message[] Msg , MessageAttach[] MsgAttach) throws destinataire_incorrecte, MoreThan10485760, ExceptionPieceExistante, message_vide {
		
		adr[0].getBoite_de_messagerie().envoyerMsg( Msg[0],"ngedmond@hotmail.com");
		adr[0].getBoite_de_messagerie().envoyerMsg( Msg[1],"ngedmond@hotmail.com");
		adr[0].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"ngedmond@hotmail.com");
		adr[0].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"ngedmond@hotmail.com");
		adr[0].getBoite_de_messagerie().envoyerMsg( Msg[4],"ngedmond@hotmail.com");
		
		adr[1].getBoite_de_messagerie().envoyerMsg( Msg[1],"tedrlord@gmail.com");
		adr[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"tedrlord@gmail.com");
		adr[1].getBoite_de_messagerie().envoyerMsg( Msg[3],"tedrlord@gmail.com");
		adr[1].getBoite_de_messagerie().envoyerMsg( Msg[4],"tedrlord@gmail.com");
		
		
		adr[2].getBoite_de_messagerie().envoyerMsg( Msg[2],"crobles@gmail.com");
		adr[2].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"crobles@gmail.com");
		adr[2].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"crobles@gmail.com");
		adr[2].getBoite_de_messagerie().envoyerMsg( Msg[2],"crobles@gmail.com");
		adr[2].getBoite_de_messagerie().envoyerMsg( Msg[2],"crobles@gmail.com");
		
		adr[3].getBoite_de_messagerie().envoyerMsg( Msg[3],"janneh@live.com");
		adr[3].getBoite_de_messagerie().envoyerMsg( Msg[1],"janneh@live.com");
		adr[3].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"janneh@live.com");
		adr[3].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"janneh@live.com");
		adr[3].getBoite_de_messagerie().envoyerMsg( Msg[2],"janneh@live.com");
		
		adr[4].getBoite_de_messagerie().envoyerMsg( Msg[4],"andale@verizon.net"); 
		adr[4].getBoite_de_messagerie().envoyerMsg( Msg[3],"andale@verizon.net"); 
		adr[4].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"andale@verizon.net"); 
		adr[4].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"andale@verizon.net"); 
		adr[4].getBoite_de_messagerie().envoyerMsg( Msg[0],"andale@verizon.net"); 
		
		adr[5].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"markzuckerberg@facebook.com");
		adr[5].getBoite_de_messagerie().envoyerMsg( Msg[1],"markzuckerberg@facebook.com");
		adr[5].getBoite_de_messagerie().envoyerMsg( Msg[4],"markzuckerberg@facebook.com");
		adr[5].getBoite_de_messagerie().envoyerMsg( Msg[0],"markzuckerberg@facebook.com");
		adr[5].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"markzuckerberg@facebook.com");
		
		adr1[0].getBoite_de_messagerie().envoyerMsg( MsgAttach[0],"sboukhedouma@usthb.dz");
		adr1[0].getBoite_de_messagerie().envoyerMsg( Msg[3],"sboukhedouma@usthb.dz");
		adr1[0].getBoite_de_messagerie().envoyerMsg( Msg[2],"sboukhedouma@usthb.dz");
		adr1[0].getBoite_de_messagerie().envoyerMsg( Msg[1],"sboukhedouma@usthb.dz");
		adr1[0].getBoite_de_messagerie().envoyerMsg( MsgAttach[1],"sboukhedouma@usthb.dz");
		
		
		adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
		adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
		adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
		adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
		adr1[1].getBoite_de_messagerie().envoyerMsg( Msg[2],"shadow@outlook.com");
	}
	
	
	public static void afficheEspaceBoite() {
		
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("Veuillez inserer l'adresse de votre boite-Email : ");
		System.out.println(adresses.keySet());
		String adress = scanner.nextLine();
		adresses.get(adress).getBoite_de_messagerie().AfficheBoite();
		System.out.println("Espace Utilisé : " + adresses.get(adress).getBoite_de_messagerie().SpaceUsed());
		System.out.println("Espace restant : " + adresses.get(adress).getBoite_de_messagerie().SpaceLeft());
	}
	
	
	public static void envoyerMessage() throws MoreThan10485760, ExceptionPieceExistante, destinataire_incorrecte, message_vide {
		
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("Veuillez inserer votre adresse Email : ");
		System.out.println(adresses.keySet());
		String adressSender = scanner.nextLine();
		System.out.println("Veuillez inserer votre adresse destinataire");
		String AdressReciever = scanner.nextLine();
		MessageAttach msg12 = new MessageAttach() ;
		msg12.saisie(0);
		adresses.get(adressSender).getBoite_de_messagerie().envoyerMsg(msg12, AdressReciever);
		Messages.put(msg12.getTitre(), msg12);
	}
	
	
	public static void afficherMessage() {
		
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("Veuillez inserer l'object de votre message : ");
		String MessageTitre = scanner.nextLine();
		if(Messages.containsKey(MessageTitre)) {
			Messages.get(MessageTitre).afficher();
		} else 
			System.out.println("Ce message n'existe Pas");

	}
	
	
	public static void supprimerMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		String adressSender ;
		
		System.out.println("Veuillez inserer votre adresse Email : ");
		System.out.println(adresses.keySet());
		adressSender = scanner.nextLine();
		
		if(!adresses.containsKey(adressSender)) {
			System.out.println("Cette adresse n'existe pas");
			return ;
		}
		
		int corbeille ;
		do {
			System.out.println("Ce message se trouve t'il dans la corbeille ?");
			System.out.println("\t1.Oui\n\t2.Non");
			corbeille= scanner.nextInt() ; scanner.nextLine(); 
		}while(corbeille!=1 && corbeille!=2) ;
		
		if(corbeille==2) 
			adresses.get(adressSender).getBoite_de_messagerie().suprrimerMsg();
		
		else 
			adresses.get(adressSender).getBoite_de_messagerie().supprimerCorbeille();
		
	}
	
	
	public static void archiverMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		String adress ;
		int choice ;
		
		do {
			System.out.println(adresses.keySet() + "\n");
			System.out.println("Veuillez inserer votre Adresse Email : ?");
			adress = scanner.nextLine();
							
			if(adresses.containsKey(adress)) 
				adresses.get(adress).getBoite_de_messagerie().archiver();
			
			else {
				System.out.println("Adress non existante");
			}
				
			
				System.out.println("Voulez vous ajouter d'autre Adresses?");
				System.out.println("--1-- Oui");
				System.out.println("--2-- Non");
				choice = scanner.nextInt(); scanner.nextLine();
			
	
		}while(choice != 2);
	}

	
	public static void restaurerMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		String adress ;
		
		System.out.println(adresses.keySet() + "\n");
		System.out.println("Veuillez inserer votre Adresse Email : ?");
	
		adress = scanner.nextLine();
		if(!(adresses.containsKey(adress))) {
			System.out.println("Adress non exitante");
			return ;
		}
		
		System.out.println("Veuillez inserer l'objet de votre message a restaurer");
		String msg_arestaurer = scanner.nextLine();
		
		
		for(Message m : adresses.get(adress).getBoite_de_messagerie().getCorbeille()) {
			if( m.getTitre().equals(msg_arestaurer) || m.getTitre().equals(msg_arestaurer.concat(" (NON LU)"))) {
				if(m.getEtat()== Etat.ENVOYE) {
					adresses.get(adress).getBoite_de_messagerie().getCorbeille().remove(m);
					adresses.get(adress).getBoite_de_messagerie().getEnvoyes().add(m);
					System.out.println("Message restaure");
					return ; 
				}
				
				else if(m.getEtat()== Etat.RECU) {
					adresses.get(adress).getBoite_de_messagerie().getCorbeille().remove(m);
					adresses.get(adress).getBoite_de_messagerie().getRecus().add(m);
					System.out.println("Message restaure");
					return  ;
				}
			}
		}					

	}
	
	
	public static void repondreMsg() throws MoreThan10485760, ExceptionPieceExistante, destinataire_incorrecte, message_vide {
		
		Scanner scanner = new Scanner(System.in) ;
		String adress ;
		
		System.out.println("Veuillez inserer votre adress Email : ");
		adress = scanner.nextLine();
		if(!(adresses.containsKey(adress))) {
			System.out.println("Adress invalide");
			return ;
		}
			
		AdrEmail repondeur = adresses.get(adress) ; 
		System.out.println("Veuillez inserer l'objet du message : ");
		String message = scanner.nextLine();
		Message arepondre = null ;
		for(Message m : adresses.get(adress).getBoite_de_messagerie().getReçus()) {
			if(m.getTitre().contentEquals(message) || m.getTitre().contentEquals(message +" (NON LU)")) {
				arepondre = m ;
			}
		}
		
		if(arepondre == null) {
			System.out.println("Message non existant");
			return  ;
		}
		
		Message reponse  = new MessageAttach() ;	
		String nouveauxtitre = arepondre.getTitre();
		nouveauxtitre = nouveauxtitre.replace(" (NON LU)", "");
		nouveauxtitre = "RE : ".concat(nouveauxtitre) ;
		reponse.setTitre(nouveauxtitre);
		reponse.saisie(1);
		
		for(AdrEmail a : adresses.values()) {
			if(a.getBoite_de_messagerie().getEnvoyes().contains(arepondre) && !a.equals(repondeur)) {
				repondeur.getBoite_de_messagerie().envoyerMsg(reponse, a.toString());
				return ;
			}
		}

	}
	
	
	public static void viderDossier() {
		
		
		String adress ;
		Scanner scanner = new Scanner(System.in) ;
		int choice ;
		
		System.out.println("Veuillez inserer votre adress Email : ");
		adress = scanner.nextLine();
		
		if(!adresses.containsKey(adress)) {
			System.out.println("Adresse non existante");
			return ;
		}
		
		System.out.println("Veuillez entrer quel dossier voulez vous supprimer ?");
		System.out.println("--1--Spam ? ");
		System.out.println("--2--Envoyés ? ");
		
		choice = scanner.nextInt(); scanner.nextLine();
		if(choice == 1) {
			adresses.get(adress).getBoite_de_messagerie().getSpam().clear();
		} 
		
		else 
		if(choice == 2) {
			adresses.get(adress).getBoite_de_messagerie().getEnvoyes().clear();
		}

	}
	
	
	public static void trierMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		String adress ;
		int choix ;
		
		System.out.println(adresses.keySet());
		System.out.println("Veuillez inserer votre Adress-Email : ?");
		adress = scanner.nextLine();
		if(!adresses.containsKey(adress)) {
			System.out.println("adress non existante");
			return  ;
		}
		
		Scanner scanner2 = new Scanner(System.in) ;
		
		do {
			System.out.println("Veuillez entrer quel dossier voulez vous Trier ?");
			System.out.println("--1--Spam ? ");
			System.out.println("--2--Envoyés ? ");
			System.out.println("--3--Reçus ?");					
			System.out.println("--4--brouillons ? ");
			System.out.println("--5--archives ? ");
			System.out.println("--6--corbeille ? ");
			choix = scanner2.nextInt(); scanner2.nextLine();
		}while(choix < 1 || choix > 6);
		
		HashSet<Message> dossier = null ;
		switch (choix) {
		case 1:
			dossier	= new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getSpam()) ;
			break;
		case 2:
			dossier = new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getEnvoyes()) ;
			break;
		case 3:
			dossier = new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getRecus()) ;
			break;
		case 4:
			dossier	= new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getBrouillons()) ;
			break;
		case 5:
			dossier	=new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getArchives()) ;
			break;
		case 6:
			dossier =new HashSet<Message>(adresses.get(adress).getBoite_de_messagerie().getCorbeille()) ;
			break;
		}
		
		System.out.println("Voulez vous un trie par date ou par objet ?");
		System.out.println("\t1.Par date.\n\t2.Par objet.");
		choix = scanner.nextInt() ; scanner.nextLine();
		
		if(choix==2) {
			TreeSet <Message> SortedMessages = new TreeSet <Message> (dossier);
			System.out.println(SortedMessages);
		}
		
		else if(choix==1) {
			ArrayList<Message> SortedMessages = new ArrayList<Message>(dossier) ;
			Collections.sort(SortedMessages, new Comparateur() );
			System.out.println(SortedMessages);
		}

	}
	
	
	public static void afficherBoiteContenantMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("Veuillez inserer l'objet de votre message : ?");
		String message = scanner.nextLine();
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
			if( entry.getValue().getBoite_de_messagerie().containsReçus(message) ){
				System.out.println(entry.getKey());
			}
		}

	}
	
	
	public static void afficherBoitesRemplies() {
		
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
			if(entry.getValue().getBoite_de_messagerie().getCapacité()*0.5 < entry.getValue().getBoite_de_messagerie().SpaceUsed()) {
				System.out.println(entry.getKey());
			}
		}

	}

	
	public static void eclaterBoites() {
		
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
			if(!(entry.getValue() instanceof AdrProf)) {
				Normadresses.put(entry.getKey(), entry.getValue());
			} else
			if(entry.getValue() instanceof AdrProf) {
				Profadresses.put(entry.getKey(), entry.getValue());
			}
		}
		System.out.println("-------Adresses Normal : ");
		for (Map.Entry<String, AdrEmail> entry : Normadresses.entrySet()) {
			System.out.println(entry.getValue());
		}
		
		System.out.println("-------Adresses Professionnel : ");
		for (Map.Entry<String, AdrEmail> entry : Profadresses.entrySet()) {
			System.out.println(entry.getValue());
		}

	}
	
	
	public static void calculerPourcentageSite() {
		
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("Veuillez inserer votre site");
		String site = scanner.nextLine();
		int total = 0;
		Map<String, Integer> hm = new HashMap<String, Integer>(); 
		
		for(Profil profile : Profiles) {
			if(profile.getAge() >= 18 && profile.getAge() <= 35) {
				
				for( Map.Entry<String, AdrEmail> entry : profile.getAdresses().entrySet() ) { 
					if(entry.getValue().getSite().contentEquals(site)) {
						hm.put(entry.getKey(), profile.getAge()) ;
						
					}
				}													
			}
		}
		
		
		for(int age = 16 ; age <=35 ; age++) {
			int tailletotal =0;
			int tailleutilise =0;
			
			if(!hm.containsValue(age))
				continue ;
			
			for (Map.Entry<String, Integer> val : hm.entrySet()) {
				if(val.getValue()==age) {
					tailletotal += (int)adresses.get(val.getKey()).getBoite_de_messagerie().getCapacité();
					tailleutilise+= adresses.get(val.getKey()).getBoite_de_messagerie().SpaceUsed();
					System.out.println(tailleutilise);
				}
			}
			
			System.out.println("Age : "+ age +"\nPourcentage d'utilisation : " +((float)tailleutilise/(float)tailletotal)*100 + "%\n");
		}

	}
	
	
	public static void afficherMsgAttach() {
		
		Scanner scanner = new Scanner(System.in) ; 
		System.out.println("Veuillez inserer l'adresse de votre boite-Email : ");
		System.out.println(adresses.keySet());
		String adrress = scanner.nextLine();
		
		if(!adresses.containsKey(adrress)) {
			System.out.println("Adress non existante");
			return  ;
		}
		
		System.out.println("-----Dossier Reçus--------");
		for(Message message1 : adresses.get(adrress).getBoite_de_messagerie().getReçus() ) {
			if((message1 instanceof MessageAttach)) {
				if(!((MessageAttach) message1).getAttachement().isEmpty() && ((MessageAttach) message1).getAttachement() != null) {
					
					message1.afficher();
					System.out.println("---------------------------------------------------------");
				}
			}
		}
		System.out.println("-----Dossier Brouillons--------");
		for(Message message1 : adresses.get(adrress).getBoite_de_messagerie().getBrouillons() ) {     
			if((message1 instanceof MessageAttach)) {
				if(((MessageAttach) message1).getAttachement() != null && !((MessageAttach) message1).getAttachement().isEmpty()) {
					message1.afficher();
				}
			}
		}
		System.out.println("-----Dossier Envoyées--------");
		for(Message message1 : adresses.get(adrress).getBoite_de_messagerie().getEnvoyes() ) {
			if((message1 instanceof MessageAttach)) {
				if(((MessageAttach) message1).getAttachement() != null && !((MessageAttach) message1).getAttachement().isEmpty()) {
					message1.afficher();
				}
			}
		}

	}
	
	
	public static void rechercherMsg() {
		
		Scanner scanner = new Scanner(System.in);
		int choix  ;
		
		System.out.println("----Voulez vous : ?----");
		do {
		System.out.println("--1--Rechercher par Destinataire ?");
		System.out.println("--2--Rechercher par Expéditeur ?");
		System.out.println("--3--Rechercher par un mot Clé ?");
		
		choix = scanner.nextInt(); scanner.nextLine() ;
		}while(choix < 1 || choix > 3);
		
		switch(choix) {
		case 1:
			System.out.println("Veuillez inserer votre adresse Email : ");
			String adr = scanner.nextLine();
			
			System.out.println("Veuillez inserer votre adresse Destinataire : ");
			String destinataire = scanner.nextLine();
			if(!adresses.containsKey(destinataire)) {
				System.out.println("adresse non existante");
				return ;
			}
			System.out.println("Les messages Envoye vers l'adress : " + destinataire + " sont : ");
			//Les messages envoyées doivent etre les memes reçus
			System.out.println(adresses.get(destinataire).getBoite_de_messagerie().getReçus());
			
			
			
			
			break;
		case 2:
			System.out.println("Veuillez inserer votre adresse Email : ");
			adr = scanner.nextLine();
			
			System.out.println("Veuillez inserer votre adresse Expediteur : ");
			String expediteur = scanner.nextLine();
			
			if(!adresses.containsKey(expediteur)) {
				System.out.println("adresse non existante");
				return  ;
			}
			System.out.println("Les messages envoyé de l'adress : " + expediteur + " sont : ");
			System.out.println(adresses.get(expediteur).getBoite_de_messagerie().getEnvoyes());
			
			break;
		case 3:
			System.out.println("Veuillez inserer votre Mot clé : ");
			String clé = scanner.nextLine();
			
			for(Map.Entry<String, Message> entry :  Messages.entrySet()) {
				if(entry.getValue().getTitre().contains(clé) || entry.getValue().getContenu().contains(clé)) {
					entry.getValue().afficher();
				}
			}
			
			break;
		}

	}
	
	
	public static void afficherProfilAvec2Boites() {
		
		int i;
		for(Profil profil : Profiles) {
		
			if(profil.getAdresses().size() >= 2) {
				
				System.out.println("Nom : " + profil.getNom());
				System.out.println("Prenom : " + profil.getPrenom());
				System.out.println("-----------------------------------------------");
			}
		}

	}
	
	
	public static void piraterSite() {
		
		Scanner scanner = new Scanner(System.in) ;
		String site ;
		
		System.out.println("Veuillez inserer votre site : ?");
		long cap;
		site = scanner.nextLine();
		//every adress MUST be in the adresses hashmap
		for (Map.Entry<String, AdrEmail> entry : adresses.entrySet()) {
			if(entry.getValue().getSite().equals(site)) {
				cap = entry.getValue().getBoite_de_messagerie().getCapacité();
				entry.getValue().setBoite_de_messagerie(new BoiteMsg());
				entry.getValue().getBoite_de_messagerie().setCapacité(cap);
			}
		}

	}
	
	
	public static void main(String[] args) throws destinataire_incorrecte, MoreThan10485760, ExceptionPieceExistante, message_vide {
		
		Message[] Msg = new Message [5];
		MessageAttach[] MsgAttach  = new MessageAttach [2];
		Msg[0] = new Message("Cours Systeme","un cours est programmé samedi à 10h00", new Date(2020, 7 , 10, 0 , 0 , 0));
		Msg[1] = new Message("Cours POO","un cours est programmé samedi à 9h00" ,new Date(2020, 7 , 10, 0 , 0 , 0));
		Msg[2] = new Message("Cours Archi","un cours est programmé samedi à 14h00" ,new Date(2020, 7 , 12, 0 , 0 , 0));
		Msg[3] = new Message("Cours Bdd","un cours est programmé samedi à 11h00");
		Msg[4] = new Message("Cours Python","un cours est programmé samedi à 13h00", new Date(2020, 7 , 12, 0 , 0 , 0));	
		
		HashSet<piece_jointe> piece = new HashSet<piece_jointe>();
		piece.add(new piece_jointe("Serie d'exercices THL",10000)) ;
		MsgAttach[0] = new MessageAttach("Cours THL","un cours est programmé samedi à 8h00",piece , new Date(2020, 7 , 14, 0 , 0 , 0));
		HashSet<piece_jointe> piece2 = new HashSet<piece_jointe>(); 
		piece2.add(new piece_jointe("Serie d'exercices Anglais",10000));
		MsgAttach[1] = new MessageAttach("Cours Anglais","un cours est programmé samedi à 15h00",piece2 , new Date(2020, 7 , 14, 0 , 0 , 0));		
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
					creerBoite() ;
					break;
					
				case 5:
					ajouterBoite() ;		
					break;
					
				case 6:
					afficherBoite() ;
					break ;
					
				case 7:
					int choixxx;
					do {
						
					
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
						choice = scanner.nextInt() ; scanner.nextLine();
						choixxx = choice;
					}while(choice > 11 || choice < 1 );
					
					switch (choice) {
					case 1:
						ajoutMessage(Msg , MsgAttach) ;
						break;
						
					case 2:
						afficheEspaceBoite();
						break;
						
					case 3:
						envoyerMessage() ;
						break;
						
					case 4:
						afficherMessage() ;
						break;
						
					case 5:
						supprimerMsg() ;
						break;
						
					case 6:
						archiverMsg() ;
						break;
						
					case 7:
						restaurerMsg() ;
						break;
						
					case 8:
						repondreMsg() ;
						break;
						
					case 9:
						viderDossier() ;
						break;
						
					case 10:
						trierMsg() ;
						break;
						
					case 11:
						break;
						
					}
					
				}while(choixxx != 11);
					
				break;	
					
				case 8: 
					int choice;
					do {						
						System.out.println("1. Afficher toutes les boites ayant reçu un message donné : ");
						System.out.println("2. Afficher les boites qui sont remplies à plus de 50% de leur capacité : ");
						System.out.println("3. Eclater la collection des boites en deux collections :");					
						System.out.println("4. Calcul du pourcentage d'utilisation pour a site donnée : ");
						System.out.println("5. Afficher les messages ayant des pièces jointes (pour une boite donnée)");
						System.out.println("6. Rechercher les messages par destinataire, par expéditeur, par mot clé.");
						System.out.println("7. Afficher les noms, prénoms des profils ayant au moins deux boites de messagerie.");  							
						System.out.println("8. Vider toutes les boites d’un site donné");
						choice = scanner.nextInt() ; scanner.nextLine();
					}while( choice > 8 || choice < 1 );
					switch (choice) {
					
					case 1: 
						afficherBoiteContenantMsg();
						break;
						
					case 2: 
						afficherBoitesRemplies() ;
						break;
						
					case 3: 
						eclaterBoites() ;						
						break;
						
					case 4: 
						calculerPourcentageSite() ;
						break;
						
					case 5: 
						afficherMsgAttach() ;
						break;
						
					case 6: 
						rechercherMsg() ;						
						break;

					case 7: 
						afficherProfilAvec2Boites() ;
						break;
						
					case 8: 
						piraterSite() ;
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
			z = scanner.nextInt();	scanner.nextLine() ;
		}while(z<1 || z>9);
		return z;
	}
	
	
}
