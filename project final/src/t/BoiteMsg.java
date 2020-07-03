package t;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import Prog.Application;

public class BoiteMsg {
	//here's what we'll do,
	//all messages will be the Type Message Attach, and the user will have the choice
	//to whether treat it as a message with a file or not
	
	private HashSet<Message> reçus ;
	private HashSet<Message> envoyés ;
	private HashSet<Message> brouillons;
	private HashSet<Message> archives ;
	private HashSet<Message> corbeille;
	private HashSet<Message> spam ;
	private long capacité;
	
	
	
	public BoiteMsg (){
		reçus = new HashSet<Message>();
		envoyés = new HashSet<Message>();
		brouillons = new HashSet<Message>();
		archives = new HashSet<Message>();
		corbeille = new HashSet<Message>();
		spam = new HashSet<Message>();
	}
	
	public void AddArchive(Message message) {
		message.setEtat(Etat.ARCHIVE);
		archives.add(message);
	}
	
	public void AddReçu(Message message) {
		 message.setEtat(Etat.RECU);
		 message.setTitre(message.getTitre() + "(NON LU)");
		 reçus.add(message) ;
	}
	
	
	public void envoyerMsg(Message msg, String keydestinataire) throws destinataire_incorrecte, MoreThan10485760, ExceptionPieceExistante, message_vide{
		
		HashMap<String, AdrEmail> adresses = Application.adresses ;
		Scanner scanner = new Scanner(System.in) ;
		
		//Heres the use of the parametre
		if(msg==null) {
			msg = new MessageAttach() ;
			msg.saisie(0);
		}
		//Heres the use of the parametre
		
		String titre = msg.getTitre();
		String contenu = msg.getContenu();
		if(titre.isBlank() == true || titre.isEmpty() == true) {
			if(contenu.isBlank() == true || contenu.isEmpty() == true) {
				throw new message_vide("**Votre message est vide");
			}
		}
		
		
//		String keydestinataire ;
		AdrEmail valuedestinataire ;
		
		
		int choix;
//		do {
			Iterator iterator = Application.adresses.entrySet().iterator();	       
//			System.out.println("----Veuillez inserez votre Address Email Destinataires----");
//			keydestinataire = scanner.nextLine() ;
//	        System.out.println("THe address : " + keydestinataire);
//	        System.out.println(adresses);
			if(!Application.adresses.containsKey(keydestinataire)) {
				throw (new destinataire_incorrecte("L'adresse saisie est incorrecte")) ;
			}
			Message msg2 = new Message(msg) ;
			msg2.setEtat(Etat.RECU);
			msg.setTitre(msg.getTitre()+" (NON LU)");
			valuedestinataire = adresses.get(keydestinataire) ; //get the AdrMail with key keydistinatiare
			valuedestinataire.getBoite_de_messagerie().AddReçu(msg2);// add the message to that AdrMail
			adresses.put(keydestinataire, valuedestinataire) ;//put the updated version with the sent nessage bacl into the hashmap
//			do {
//				System.out.println("----Voulez vous ajouter d'autres destinataires ?---");
//				System.out.println("\t1.Oui\n\t2.Non");
//			
//			
//				choix = scanner.nextInt(); scanner.nextLine();
//			}while(choix!=1 && choix!=2);
		
//		}while(choix==1) ;
		
		//add the message to the "envoyes" folder after succesfully sending it
		msg.setTitre(msg.getTitre().replace(" (NON LU)",""));
		msg.setEtat(Etat.ENVOYE);
		envoyés.add(msg) ;
//		SpaceAlert();
	}
	
	
	public void suprrimerMsg() {
		
		Scanner scanner = new Scanner(System.in) ;
		String titre ;
		int choix ;
		HashSet<Message> dossier ;
		
		System.out.println("----Dans quel dossier ce trouve le message a supprimer ?---");
		do {
			System.out.println("\t1.Recus\n\t2.Envoyes");
			choix = scanner.nextInt() ; scanner.nextLine() ;
			
		}while(choix!=1 && choix!=2);
		
		if(choix==1)
			dossier = this.reçus ;
		else
			dossier = this.envoyés ;
		
		System.out.println("----Quel est le titre du message que vous voudriez supprimer ?---");
		titre = scanner.nextLine() ;
		
		ArrayList<Message> asupprimer = new ArrayList<Message>() ;
		boolean trouve  = false ;
		
		for(Message m : dossier) {
			if(m.getTitre().contentEquals(titre) || m.getTitre().contentEquals(titre+"(NON LU)")) {
				asupprimer.add(m) ;
				trouve = true ;
			}
		}
		
		//si il n'ya aucun message correspondant
		if(!trouve) {
			System.out.println("Ce message n'existe pas dans le dossier selectione");
			return  ;
		}
			
		//si il y'a un seul message correspondant.
		Message temp ;
		if(asupprimer.size()==1) {
			temp  = asupprimer.get(0);
			dossier.remove(temp);
			
			corbeille.add(temp);
			System.out.println("Message supprime");
			return  ;
		}		
		
		//si il y'a plusieurs messages.
		System.out.println("Il existe plusieur message avec ce titre, lequel voulez vous supprimer ? ");
		do {
			for(int i=0; i<asupprimer.size() ; i++) {
				
				System.out.print(i+"-"); asupprimer.get(i).afficher();
				System.out.println("\n");
			}
			System.out.println(asupprimer.size()+1 + "-Supprimer tout.");
			System.out.println(asupprimer.size()+2 + "-Quitter");
			
			do {
				System.out.println("Veuillez inserer une valeur entre 0 et "+ asupprimer.size()+2);
				choix= scanner .nextInt() ; scanner.nextLine() ;
				
			}while(choix>asupprimer.size()+2 || choix<0);
			
			if(choix == asupprimer.size()+2)
				return ;
			
			if(choix == asupprimer.size()+1) {
				//supprimer tous les messages correspondant.
				dossier.removeAll(asupprimer);
				//ajouter tous les messages correspondant a corbeille.
				for(int i = 0 ; i<asupprimer.size() ; i++) {
					temp = asupprimer.get(i) ;
					
					asupprimer.set(i, temp);
				}
				corbeille.addAll(asupprimer);
				System.out.println("Messages suprimes.");
				return ;
			}
			
			
			//supprimer un seul element.
			temp = asupprimer.get(choix) ;
			dossier.remove(temp) ;	// supprimer le messages de la boite de messagerie .
			asupprimer.remove(temp) ; //supprimer le message supprimer de la liste des messages correspondants.
			
			//ajouter le message supprime a la corbeile.
			corbeille.add(temp);	
			System.out.println("Message supprime");
			
		}while(choix!=asupprimer.size()+2 && asupprimer.size()!=0) ;
		
	}
	
	
	
	public void supprimerCorbeille() {
		Scanner scanner = new Scanner(System.in) ;
		String titre ;
		int choix ;
		
		System.out.println("----Quel est le titre du message que vous voudriez supprimer ?---");
		titre = scanner.nextLine() ;
		
		ArrayList<Message> asupprimer = new ArrayList<Message>() ;
		boolean trouve  = false ;
		
		for(Message m : corbeille) {
			if(m.getTitre()==titre) {
				asupprimer.add(m) ;
				trouve = true ;
			}
		}
		
		//si il n'ya aucun message correspondant
		if(!trouve) {
			System.out.println("Ce message n'existe pas dans la corbeille");
			return  ;
		}
			
		//si il y'a un seul message correspondant.
		Message temp ;
		if(asupprimer.size()==1) {
			temp  = asupprimer.get(0);
			corbeille.remove(temp);
			System.out.println("Message supprime");
			return  ;
		}		
		
		//si il y'a plusieurs messages.
		System.out.println("Il existe plusieur message avec ce titre, lequel voulez vous supprimer ? ");
		do {
			for(int i=0; i<asupprimer.size() ; i++) {
				
				System.out.print(i+"-"); asupprimer.get(i).afficher();
				System.out.println("\n");
			}
			System.out.println(asupprimer.size()+1 + "-Supprimer tout.");
			System.out.println(asupprimer.size()+2 + "-Quitter");
			
			do {
				System.out.println("Veuillez inserer une valeur entre 0 et "+ asupprimer.size()+2);
				choix= scanner .nextInt() ; scanner.nextLine() ;
				
			}while(choix>asupprimer.size()+2 || choix<0);
			
			if(choix == asupprimer.size()+2) 
				return ;
			
			if(choix == asupprimer.size()+1) {
				//supprimer tous les messages correspondant.
				corbeille.removeAll(asupprimer);
				System.out.println("Messages suprimmes.");
				return ;
			}
			
			//supprimer un seul element.
			temp = asupprimer.get(choix) ;
			corbeille.remove(temp) ;	// supprimer le messages de la boite de messagerie .
			asupprimer.remove(temp) ; //supprimer le message supprime de la liste des messages correspondants.
			System.out.println("Message supprime");
			
		}while(choix!=asupprimer.size()+2 && asupprimer.size()!=0) ;
	}
	
	
	public void deplacer() {
		
		Scanner scanner = new Scanner(System.in) ;
		String titre ;
		int choix ;
		
		System.out.println("----Quel est le titre du message que vous voudriez deplacer ?---");
		titre = scanner.nextLine() ;
		
		ArrayList<Message> adeplacer = new ArrayList<Message>() ;
		boolean trouve  = false ;
		
		for(Message m : spam) {
			if(m.getTitre()==titre) {
				adeplacer.add(m) ;
				trouve = true ;
			}
		}
		
		//si il n'ya aucun message correspondant
		if(!trouve) {
			System.out.println("Ce message n'existe pas dans spam.");
			return  ;
		}
			
		//si il y'a un seul message correspondant.
		Message temp ;
		if(adeplacer.size()==1) {
			temp  = adeplacer.get(0);
			spam.remove(temp);
			reçus.add(temp);
			System.out.println("Message deplace");
			return  ;
		}		
		
		//si il y'a plusieurs messages.
		System.out.println("Il existe plusieurs message avec ce titre, lequel voulez vous deplacer ? ");
		do {
			for(int i=0; i<adeplacer.size() ; i++) {
				
				System.out.print(i+"-"); adeplacer.get(i).afficher();
				System.out.println("\n");
			}
			System.out.println(adeplacer.size()+1 + "-Deplacer tout.");
			System.out.println(adeplacer.size()+2 + "-Quitter");
			
			do {
				System.out.println("Veuillez inserer une valeur entre 0 et "+ adeplacer.size()+2);
				choix= scanner .nextInt() ; scanner.nextLine() ;
				
			}while(choix>adeplacer.size()+2 || choix<0);
			
			if(choix == adeplacer.size()+1) {
				//supprimer tous les messages correspondant.
				spam.removeAll(adeplacer);
				//ajouter tous les messages correspondant au dossier "Recus".
				reçus.addAll(adeplacer);
				System.out.println("Messages deplaces.");
				return ;
			}
			
			//deplacer un seul element.
			temp = adeplacer.get(choix) ;
			spam.remove(temp) ;	// supprimer le messages de la boite de messagerie .
			adeplacer.remove(temp) ; //supprimer le message supprimer de la liste des messages correspondants.
			
			//ajouter le message supprime a spam.
			spam.add(temp);	
			System.out.println("Message deplace");
			
		}while(choix!=adeplacer.size()+2 && adeplacer.size()!=0) ;
		SpaceAlert();
	}
	
	
	//untested
	public void NombreDeMsgs()  {
		int i = 0;
		int j = 0;
		for(Message m : reçus) 	{
			if(m.getTitre().contains("(NON LU)")) {
				i++ ;
				} else j++;
			}
		System.out.println("Nombres de Messages Reçu Non Lu : " + i);
		System.out.println("Nombres de Messages Reçu Lu : " + j);   
		System.out.println("Nombres de Messages Envoyés : " + envoyés.size() );
		System.out.println("Nombres de Messages Spams : " + spam.size());
		System.out.println("Nombres de Messages brouillons : " + brouillons.size() );
		System.out.println("Nombres de Messages archives : " + archives.size() );
		System.out.println("Nombres de Messages corbeille : " + corbeille.size() );	
	}
	
	
	public void archiver() {
		
		Scanner scanner = new Scanner(System.in) ;
	
		System.out.println("----Insérer la date pour laquelle les messages seront archivés--");
		Date date ;
		
		int day,month,year;
		
		System.out.println("Année : ?");
		year = scanner.nextInt();
		
		System.out.println("Mois : ?");
		month = scanner.nextInt();
					
		System.out.println("Jour : ?");
		day = scanner.nextInt();

		date = new Date(year, month, day) ; 
		
		for(Message m : this.reçus) {
			if(m.getCreation().before(date)) {
				this.reçus.remove(m);
				m.setEtat(Etat.ARCHIVE);
				this.archives.add(m);
			}
		}
		
		for(Message m : this.envoyés) {
			if(m.getCreation().before(date)) {
				this.envoyés.remove(m);
				m.setEtat(Etat.ARCHIVE);
				this.archives.add(m);
			}
		}
		
	}
	
	//untested
	public int SpaceUsed() {
		int s = 0;
			for(Message m : reçus) 	
				{
					if (m instanceof MessageAttach)
						s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
					else
						s = (int) (m.getTaille()) + s ;
				}
				
			for(Message m : spam) 	
				{
					if (m instanceof MessageAttach)
						s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
					else
						s = (int) (m.getTaille()) + s ;	
				}
			
			for(Message m : brouillons) 	
				{
				if (m instanceof MessageAttach)
					s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
				else
					s = (int) (m.getTaille()) + s ;
				}
			
			for(Message m : archives) 	
				{
				if (m instanceof MessageAttach)
					s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
				else
					s = (int) (m.getTaille()) + s ;	
				}
			
			for(Message m : corbeille) 	
				{
				if (m instanceof MessageAttach)
					s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
				else
					s = (int) (m.getTaille()) + s ;	
				}
			
			for(Message m : envoyés) 	
			{
				if (m instanceof MessageAttach)
					s = (int) (m.getTaille() + ((MessageAttach) m).getTailleattachement()) + s ;
				else
					s = (int) (m.getTaille()) + s ;	
			}
			
			return s;
				
			}
	
	//untested	
	public int SpaceLeft() {
		int k = (int) capacité - SpaceUsed();
		return k;
	}
		
	//untested
	public void SpaceAlert() {
		if( (int) (capacité*0.8) <= SpaceUsed() ) {
			System.out.println("				   ****ALERTE !!! *****");
			System.out.println("IL VOUS RESTE MOIN DE 20% ESPACE DISPONIBLE DANS VOTRE BOITE ");
			System.out.println("      VOUS DEVEZ SUPPRIMER CERTAINS MESSAGES");
		}
	}
	
	public void AfficheBoite() {
		System.out.println("Messages Reçus : \n" + reçus);
		System.out.println("Messages spam : \n" + spam);
		System.out.println("Messages brouillons : \n" + brouillons);
		System.out.println("Messages archives : \n" + archives);
		System.out.println("Messages corbeille : \n" + corbeille);
		System.out.println("Messages envoyés : \n" + envoyés);
	}
	
	public int nbrRecus() {
		
		return reçus.size() ;
	}
	
	
	public int nbrEnvoyes(){
		
		return this.envoyés.size() ;
	}
	
	
	public int nbrSpam(){
		
		return spam.size() ;
	}
	
	
	public int nbrCorbeille() {
		
		return corbeille.size() ;
	}
	
	
	public int nbrArchive(){
		
		return archives.size() ;
	}
	
	
	public int nbrBrouillon() {
		
		return brouillons.size() ;
	}

	
	public HashSet<Message> getRecus(){
		return this.reçus ;
	}
	
	
	public HashSet<Message> getEnvoyes(){

		return this.envoyés ;
	}


	public long getCapacité() {
		return capacité;
	}


	public void setCapacité(long capacité) {
		this.capacité = capacité;
	}

	public HashSet<Message> getCorbeille() {
		return corbeille;
	}

	public void setCorbeille(HashSet<Message> corbeille) {
		this.corbeille = corbeille;
	}

	public HashSet<Message> getReçus() {
		return reçus;
	}

	public void setReçus(HashSet<Message> reçus) {
		this.reçus = reçus;
	}

	public HashSet<Message> getEnvoyés() {
		return envoyés;
	}

	public void setEnvoyés(HashSet<Message> envoyés) {
		this.envoyés = envoyés;
	}

	public HashSet<Message> getBrouillons() {
		return brouillons;
	}

	public void setBrouillons(HashSet<Message> brouillons) {
		this.brouillons = brouillons;
	}

	public HashSet<Message> getArchives() {
		return archives;
	}

	public void setArchives(HashSet<Message> archives) {
		this.archives = archives;
	}

	public HashSet<Message> getSpam() {
		return spam;
	}

	public void setSpam(HashSet<Message> spam) {
		this.spam = spam;
	}
	
	
}
