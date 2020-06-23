package Email;

import java.util.Scanner;

public class AdrEmail {
	
	private String pseudo ;
	private String site ; 
	private String mdp ;
	
	
	AdrEmail(){
	}
	
	
	AdrEmail(String pseudo , String site , String mdp){
		this.mdp = mdp ;
		this.pseudo = pseudo ;
		this.site = site ;
	}
	
	
	public String toString() {
		return (pseudo + '@'+ site );
	}
	
	
	//Method pour changer le mot de passe.
	public void reintialiserMdp() {
		
		Scanner scanner = new Scanner(System.in);
		String temp ;
		
		System.out.print("Saisir ancien mot de passe  ");
		do {
			
			temp = scanner.nextLine() ;
			if (!temp.contentEquals(mdp)) 
				System.out.println("Mot de passe incorrecte. Veuilez reessayer.");
			
		}while (!temp.contentEquals(mdp)) ;
		
		
		System.out.println("Saisir un nouveau mot de passe : ");
		boolean mdpValide ;
		do {
			
			temp = scanner.nextLine() ;
			mdpValide = AdrEmail.mdpValide(temp);
			if (!mdpValide) 
				System.out.println("Veuilez reessayer.");
			
		}while (!mdpValide) ;
		
	}
	
	
	//Methode pour verifier si une chaine est valide pour etre un mot de passe .
	public static boolean mdpValide(String mdp) {
		
		boolean contientdigit = false ;
		boolean contientalpha = false ; 
		boolean contientspc = false ;
		
		
		//Verifier si la taille de chaine est inferieure a 8.
		if(mdp.length() < 8) {
			System.out.println("Mot de passe non valide. Taille inferieure a 8.");
			return false ;
		}
		
		//Verifier si la chaine contient au moins une lettre, un digit, et un caractere speciale.
		for (int i=0 ; i<mdp.length() ; i++) {
			
			if(Character.isLetter(mdp.charAt(i)))	
				contientalpha= true ;
			
			else if(Character.isDigit(mdp.charAt(i)))
				contientdigit = true ;
			
			else
				contientspc = true ;
			
			//Si toute les conditions sonts vraies alors la chaine est valide comme mdp.
			if(contientspc && contientdigit && contientalpha)
				return true ;
		}
		
		System.out.println("Mot de passe invalide. Caractères requis manquants");
		return false ;
			
	}
}
