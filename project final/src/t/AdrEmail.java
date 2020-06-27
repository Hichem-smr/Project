package t;

import java.util.Comparator;
import java.util.Scanner;

public class AdrEmail {
	
	private String pseudo ;
	private String site ; 
	private String mdp ;
	private BoiteMsg boite_de_messagerie ;
	
	public AdrEmail(){
		boite_de_messagerie = new BoiteMsg();
		
	}
	
	
	AdrEmail(String pseudo , String site , String mdp){
		this.mdp = mdp ;
		this.pseudo = pseudo ;
		this.setSite(site) ;
		boite_de_messagerie = new BoiteMsg();
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
		Scanner scanner = new Scanner(System.in);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdrEmail other = (AdrEmail) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
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

	 Comparator<String> SiteComparator = new Comparator<String>() {
	        @Override public int compare(String s1, String s2) {
	        	int index1=s1.indexOf("@") + 1;
	        	int index2=s2.indexOf("@") + 1;
	            return s1.substring(index1).compareTo(s2.substring(index2));
	        }           
	    };
	
	
	
}

