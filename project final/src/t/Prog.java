package t;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class Prog {

	/*ive made the list of adresses into a static object so it can be accessible everywhere.
	ive picked hashmap to make it easier to access the adresse u need.
	the key of an element corresponds to the return value of its toString() method.
	for example the key of the element :
	
	pseudo: abdelghani
	site : gmail.com
	
	is the string "abdelghani@gmail.com".
	which means if u wanted to get that element u do adresses.get("abdelghani@gmail.com") .
	
	*/
	
	public static HashMap<String ,AdrEmail> adresses = new HashMap<String ,AdrEmail>() ;
	

	
	
	public static void main(String[] args) throws MoreThan10485760, destinataire_incorrecte, ExceptionPieceExistante {
		Scanner scanner = new Scanner(System.in);
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
 
		int y = 0;
		
		//filling up the addresses hashmap.
		for(int i = 0 ; i<6 ; i++) {
			
			adresses.put(adr[i].toString(), adr[i]) ;
			adresses.put(adr1[y].toString(), adr1[y]);
			y++ ;
			
		}
		
		
	
		int j ;
		do {
		System.out.println("----Veuillez inserez votre Address Email ----");
		System.out.println("---1- Address email Normal ----");
		System.out.println("---2- Address Email Professionnel ----");
		j = scanner.nextInt();
		
		//first you log in
		if(j == 1) {
			AdrEmail adr2 = new AdrEmail();
			adr2.Saisie_AdrEmail();
			
			
			//Envoye un message pour un destinataire
			int z;
			//write the message first
			
			MessageAttach message = new MessageAttach();
			message.saisie();
			message.setEtat(Etat.valueOf("ENVOYE"));
			
			do {
				
				

				// define your emails
				System.out.println("----Veuillez inserez votre Address Email Destinataire----");
				for(int i=0; i< adr.length; i++) {
					System.out.println(adr[i].toString());
				}
				for(int i=0; i< adr1.length; i++) {
					System.out.println(adr1[i].toString());
				}
				System.out.println("---1- Address email Normal ----");
				System.out.println("---2- Address Email Professionnel ----");
				int k = scanner.nextInt(); scanner.nextLine();
				if(k == 1) {
					AdrEmail adr9 = new AdrEmail();
					adr9.Saisie_AdrEmail();
					//verifie if the address exists
					for(int i=0; i< adr.length; i++) {
						if(adr9.equals(adr[i]) == true ) {
							//send the message to the email
							message.setEtat(Etat.valueOf("RECU"));
							adr[i].boite_de_messagerie.AddReçu(message);
							
							System.out.println("success");
							//need to set msg state as sent from sender, and received from receiver
							break;
						}
						else 
							if (adr9.equals(adr[i]) == false)	{
								if(i == adr.length-1)  {
									throw(new destinataire_incorrecte("Cette addresse n'existe pas"));
								}
							}
						
					}
					//2nd write your message, but this will happen only once whether there is one or many emails
					
					
					
					
				}
				else if(k == 2) {
					AdrProf adr10 = new AdrProf();
					adr10.saisie();
					for(int i=0; i< adr1.length; i++) {
						if(adr10.equals(adr1[i]) == true ) {
							//send the message to the email
							message.setEtat(Etat.valueOf("RECU"));
							adr1[i].boite_de_messagerie.AddReçu(message);
							//need to set msg state as sent from sender, and received from receiver
							break;
						}
						else 
							if(adr10.equals(adr1[i]) == false && i == adr1.length-1 ) {
							throw(new destinataire_incorrecte("Cette addresse n'existe pas"));
						}
						
					}
					
					
				}
				
				System.out.println("----Inserez 0 pour autres Adresse email Sinon 1 pour sortir----");
				z = scanner.nextInt();
			} while(z != 1);
		}
		else if(j == 2) {
			AdrProf adr2 = new AdrProf();
			adr2.saisie();
			//write the message first
			MessageAttach message = new MessageAttach();
			message.saisie();
			message.setEtat(Etat.valueOf("ENVOYE"));
			
			//Envoye un message pour un destinataire
			int z;
			do {
			
				adr2.boite_de_messagerie.AddReçu(message);
				// define your emails
				System.out.println("----Veuillez inserez votre Address Email Destinataire----");
				System.out.println("---1- Address email Normal ----");
				System.out.println("---2- Address Email Professionnel ----");
				int k = scanner.nextInt();
				if(k == 1) {
					AdrEmail adr9 = new AdrEmail();
					adr9.Saisie_AdrEmail();
					for(int i=0; i< adr.length; i++) {
						if(adr9.equals(adr[i]) == false) {
							throw(new destinataire_incorrecte("Cette piece Existe deja dans votre attachement"));
						}
						else {
							//send the message to the email
							message.setEtat(Etat.valueOf("RECU"));
							adr[i].boite_de_messagerie.AddReçu(message);
							//need to set msg state as sent from sender, and received from receiver
							
						}
					}
					//2nd write your message, but this will happen only once whether there is one or many emails
					
					
					
					
				}
				else if(k == 2) {
					AdrProf adr10 = new AdrProf();
					adr10.saisie();
					for(int i=0; i< adr.length; i++) {
						if(adr10.equals(adr1[i]) == false) {
							throw(new destinataire_incorrecte("Cette piece Existe deja dans votre attachement"));
						}
						else {
							
							//send the message to the email
							message.setEtat(Etat.valueOf("RECU"));
							adr1[i].boite_de_messagerie.AddReçu(message);
							//need to set msg state as sent from sender, and received from receiver
						}
					}
					
					
				}
				
				System.out.println("----Inserez 0 pour autres Adresse email Sinon 1 pour sortir----");
				z = scanner.nextInt();
			} while(z != 1);
			
		}
		
		} while(j != 1 && j != 2);
		
		// need to test if the messages really arrived, and their states from sender and reciever
		

		
		
		
		
	}

}
