package t;

import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import Prog.Application;




public class Message implements Comparator<Message>, Comparable<Message> {
	
	Scanner scanner = new Scanner(System.in);
	private String titre ;
	private String contenu ;
	private Date creation ;
	private Etat etat ;
	int taille ;
	// = titre.length() + contenu.length();
	
	public Message(){
		etat = Etat.CREE;
		taille = 0 ;
	}
	
	public Message(String titre, String contenu ){
		
		this.creation = new Date() ;
		this.titre = titre ;
		this.contenu = contenu ; 
		this.etat = Etat.CREE;
		taille = titre.length() + contenu.length();
	}
	
	
	public Message(Message m){
		this.contenu = m.contenu ;
		this.titre = m.titre ; 
		this.creation = m.creation ;
		this.taille = m.taille ;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	
	public void setEtat(String etat) {
		this.etat = Etat.valueOf(etat) ;
	}
	
	public boolean equals(Object o) {
		
		if (o==null)
			return false ;
		
		if(! (o instanceof Message))
			return false ;
		
		if(o instanceof MessageAttach) {
			if(((MessageAttach)o).getAttachement()!=null)
				return false ;
		}
			
		
		String titre = ((Message)o).titre;
		String contenu = ((Message)o).contenu; 
		if((this.titre.contentEquals(titre) || this.titre.contentEquals(titre+" (NON LU)")) && this.contenu.contentEquals(contenu) )
			return true ;
		
		return false ;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	@Override
	public String toString() {
		return (titre+"-"+taille+"byte-"+etat.displayvalue+"-" +creation+"\n");
	}
	
	//we need modify message method
	
	public void saisie(int a) throws MoreThan10485760, ExceptionPieceExistante   {
		
		Scanner scanner = new Scanner(System.in);
		int z;
		
		
		if(a==0) {
			System.out.println("----Veuillez insérez l'objet de votre message----");
			titre = scanner.nextLine();
		}
		System.out.println("----Veuillez insérez le contenu de votre message----");
		contenu = scanner.nextLine();
		creation = new Date() ;		//Date() returns the current system date.
		etat = Etat.CREE ;
		
		
			System.out.println("---- Voulez vous ----");
			System.out.println("--1--  Afficher votre message ? ----");
			System.out.println("--2--  Modifier votre message ? ----");
			System.out.println("--3--  EXIT ?  ----");
			int k = scanner.nextInt();
			switch(k) {
			case 1 :
			this.afficher();
			case 2:
				int f;
				do {
					System.out.println("-----  Voulez vous -----");
					System.out.println("--1--  Modifier votre Objet ? ----");
					System.out.println("--2--  Modifier votre Contenu du message ? ----");
					System.out.println("--3--  EXIT ? ----");
					 f = scanner.nextInt(); scanner.nextLine() ;
					 if(f == 1) {
						 System.out.println("----Veuillez insérez l'objet de votre message----");
							titre = scanner.nextLine();
					 } 
					 else if(f == 2) {
						 System.out.println("----Veuillez insérez le contenu de votre message----");
							contenu = scanner.nextLine();
					 }
				} while(f != 1 && f !=2 && f!=3);
				
			
			}
			
			taille = titre.length() + contenu.length() ;
			Application.Messages.put(this.titre, this) ;
	}
	
	
	
	public void afficher() {
		
		
		System.out.println("Objet : " + titre + "\n");
		System.out.println("\t" + contenu );
		titre = titre.replace("(NON LU)", "");
	}
	
	@Override
	public int compareTo(Message arg0) {
		
		if(this.titre.compareTo(arg0.titre)!=0)
			return this.titre.compareTo(arg0.titre);
		
		else
			return this.creation.compareTo(arg0.creation);
	}
	
	
	class Comparateur implements Comparator<Message>{
	
		public int compare(Message arg0, Message arg1) {
			// TODO Auto-generated method stub
		
			if (arg0.creation.compareTo(arg1.creation)!=0)
				return arg0.creation.compareTo(arg1.creation);
			else
				return arg0.titre.compareTo(arg1.titre) ;
		}

	}
	
	
	public int compare(Message arg0, Message arg1) {
		// TODO Auto-generated method stub
	
		if (arg0.creation.compareTo(arg1.creation)!=0)
			return arg0.creation.compareTo(arg1.creation);
		else
			return arg0.titre.compareTo(arg1.titre) ;
	}
	
	
	public boolean expediteur() {
		if(this.etat.equals(Etat.ENVOYE)) {
			return true;
		}
		return false;
	}
	
	public boolean destinataire() {
		if(this.etat.equals(Etat.RECU)) {
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		return 0;
	}
}	
