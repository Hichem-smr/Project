package t;

import java.util.Comparator;
import java.util.Scanner;


public class AdrEmail implements Comparator<AdrEmail>, Comparable<AdrEmail>{
	Scanner scanner = new Scanner(System.in);
	private String pseudo ;
	private String site ; 
	private String mdp ;
	private BoiteMsg boite_de_messagerie ;
	
	public AdrEmail(){
		
	}
	
	
	public AdrEmail(String pseudo , String site , String mdp){
		this.mdp = mdp ;
		this.pseudo = pseudo ;
		this.setSite(site) ;
	}
	
	
	public String toString() {
		return (pseudo + '@'+ site );
	}
	
	
	//Method pour changer le mot de passe.
	public String reintialiserMdp() {
		
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
		
		return temp;
		
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
	// method pour verifier si le pseudo est valide
	public static boolean Verif_pseudo(String pseudo) {
		
		if(Character.isLetter(pseudo.charAt(0)) == false) {
			System.out.println("Pseudonyme invalid. Le premier caractère n'est pas une lettre.");
			return false;
		}
		
		for(int i=1; i<pseudo.length(); i++) {
			
			if(Character.isLetter(pseudo.charAt(i)) == false && Character.isDigit(pseudo.charAt(i)) == false && pseudo.charAt(i) != '-' && pseudo.charAt(i) != '_' && pseudo.charAt(i) != '.' ) {
				System.out.println("Pseudonyme invalid. Presence de caractere(s) interedit(s).");				
				return false;
			}
			
		}
		return true;
	}
	
	
	public void saisie() {
		//insertion du pseudo
		
		System.out.println("----Veuillez inserez votre Nom d'utilisateur----");
		int k = 0;
		do {
			if(k != 0) {
				System.out.println("----Veuillez reinsérez votre Nom d'utilisateur----");
			}
			pseudo = scanner.nextLine();
			k = 1;
		} while(!Verif_pseudo(pseudo));
		
		
		//Insertion du site
		System.out.println("----Veuillez inserez votre Nom du site----");
		String site1 = scanner.nextLine();
		setSite(site1.concat(""));
		
		
		//insertion du mot de passe
		k=0;
		System.out.println("----Veuillez insérez votre Mot de passe----");
		
		do {
			if(k != 0) {
				System.out.println("----Veuillez reinsérez votre Mot de passe----");
			}
			mdp = scanner.nextLine();
			k = 1;
		} while(!mdpValide(mdp));
	}
	


	@Override
	public boolean equals(Object obj) {
		
		if(obj==null)
			return false ;
		
		if (!(obj instanceof AdrEmail))
			return false ;
		
		AdrEmail o = (AdrEmail) obj ;
		if(!site.contentEquals(o.site) || !pseudo.contentEquals(o.pseudo))
			return false ;
		
		return true ;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public BoiteMsg getBoite_de_messagerie() {
		return boite_de_messagerie;
	}


	public void setBoite_de_messagerie(BoiteMsg boite_de_messagerie) {
		this.boite_de_messagerie = boite_de_messagerie;
	}


	@Override
	public int compareTo(AdrEmail arg0) {
		// TODO Auto-generated method stub
		
		if(this.site.compareTo(arg0.site)!=0)
			return this.site.compareTo(arg0.site);
		
		else
			return this.pseudo.compareTo(arg0.pseudo);
	}


	@Override
	public int compare(AdrEmail arg0, AdrEmail arg1) {
		// TODO Auto-generated method stub
		
		if (arg0.site.compareTo(arg1.site)!=0)
			return arg0.site.compareTo(arg1.site);
		else
			return arg0.pseudo.compareTo(arg1.pseudo) ;
	}


	public String SaisiePsuedo() {
		System.out.println("----Veuillez inserez votre Nom d'utilisateur----");
		int k = 0;
		String pseudo1;
		do {
			if(k != 0) {
				System.out.println("----Veuillez reinsérez votre Nom d'utilisateur----");
			}
			pseudo1 = scanner.nextLine();
			k = 1;
		} while(!Verif_pseudo(pseudo1));
		return pseudo1;
	}

	public int hashCode() {
		return 0;
	}

}

