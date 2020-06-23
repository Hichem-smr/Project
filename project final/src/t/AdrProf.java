package t;

import java.util.Scanner;

public class AdrProf extends AdrEmail {
	String travaille;
	String domain;
	
	AdrProf(String pseudo , String site , String mdp, String domain) {
		super();
		this.travaille = site.toString().replace(".com", "");
		this.domain = domain;
	}
	
	void saisie() {
		Scanner scanner = new Scanner(System.in);
		super.Saisie_AdrEmail();
		
		travaille = getSite().toString().replace(".com", "") ;
		
		System.out.println("----Veuillez insérez le domain du travaille----");
		domain = scanner.nextLine();
	}
}
