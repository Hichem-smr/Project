package t;

import java.util.Scanner;

public class AdrProf extends AdrEmail {
	String entreprise;
	String domain;
	
	AdrProf(String pseudo , String site , String mdp, String domain , String entreprise) {
		super();
		this.entreprise = entreprise ;
		this.domain = domain;
	}
	
	void saisie() {
		Scanner scanner = new Scanner(System.in);
		super.Saisie_AdrEmail();
		System.out.println("----Veuillez insérez le nom de l'entreprise----");
		entreprise = scanner.nextLine();
		
		System.out.println("----Veuillez insérez le domain du travaille----");
		domain = scanner.nextLine();
	}
	
	
	public String toString() {
		return(super.toString() +", "+entreprise +", "+domain);
	}
}
