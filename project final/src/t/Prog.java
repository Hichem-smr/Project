package t;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


 class Prog {

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
	

	
	
	public static void main(String[] args) throws MoreThan10485760, destinataire_incorrecte, ExceptionPieceExistante, message_vide {
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
		AdrEmail adr2 = null;
		int y = 0;		
		//filling up the addresses hashmap.
		for(int i = 0 ; i<6 ; i++) {			
			adresses.put(adr[i].toString(), adr[i]) ;						
		}
		for(int i =0; i<2; i++) {
			adresses.put(adr1[i].toString(), adr1[i]);	
		}
		
	
		int j ;
		do {
		System.out.println("----Veuillez inserez votre Address Email ----");
		System.out.println("---1- Address email Normal ----");
		System.out.println("---2- Address Email Professionnel ----");
//		j = scanner.nextInt();
		j = 1;
		//first you log in
		if(j == 1) {
			adr2 = new AdrEmail("hichem", "semmar.com", "azerfaze12$$");
			
			BoiteMsg BoiteMsg = new BoiteMsg();
			System.out.println("Donner votre taille de la boite");
			int k = scanner.nextInt();
			BoiteMsg.setCapacité(k);
			BoiteMsg.envoyerMsg(null);
			adr2.setBoite_de_messagerie(BoiteMsg);
			System.out.println("Sent " + adr2.getBoite_de_messagerie().getEnvoyes());
			System.out.println("recieved" + adr[5].getBoite_de_messagerie().getRecus());
			adr2.getBoite_de_messagerie().suprrimerMsg();
			adr[5].getBoite_de_messagerie().suprrimerMsg();
			System.out.println("Sent " + adr2.getBoite_de_messagerie().getEnvoyes());
			System.out.println("recieved" + adr[5].getBoite_de_messagerie().getRecus());
			
//			adr2.getBoite_de_messagerie().SpaceAlert();
		}
			
			
		else if(j == 2) {
			adr2 = new AdrProf();
			adr2.saisie();
			System.out.println("Donner votre taille de la boite");
			int k = scanner.nextInt();
			BoiteMsg BoiteMsg = new BoiteMsg();
			BoiteMsg.envoyerMsg(null);
			adr2.setBoite_de_messagerie(BoiteMsg);						
			System.out.println("space left " + adr2.getBoite_de_messagerie().SpaceLeft());
			
		
		}
		
		} while(j != 1 && j != 2);
		
		// need to test if the messages really arrived, and their states from sender and reciever
		
//		System.out.println(adr2.getBoite_de_messagerie().getEnvoyes());
//		System.out.println(adresses.get("sboukhedouma@usthb.dz").getBoite_de_messagerie().getRecus());
		
		
		
		
		
	}
	
	
		

}
