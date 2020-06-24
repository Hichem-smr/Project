package t;

import java.util.Date;
import java.util.Scanner;

enum Etat	{
	CREE("Créé") , ENVOYE("Envoyé") , RECU("Reçu") , EN_COURS("En cours") , 
	STOCKE("Stocké") , SUPPRIME("Supprimé") , ARCHIVE("Archivé") , RETOURNE("Retourné");
	
	String displayvalue ;
	Etat(String displayvalue){
		this.displayvalue= displayvalue;
	}
	
}

public class Message {
	
	Scanner scanner = new Scanner(System.in);
	private String titre ;
	private String contenu ;
	private Date creation ;
	private Etat etat ;
	int taille = titre.length() + contenu.length();
	
	
	Message(){
		etat = Etat.CREE;
		taille = 0 ;
	}
	
	Message(String titre, String contenu , Date creation , Etat etat ){
		
		this.creation = creation ;
		this.titre = titre ;
		this.contenu = contenu ; 
		this.etat = etat ;
		taille = titre.length() + contenu.length();
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public boolean equals(Object o) {
		
		if (o==null)
			return false ;
		
		if(!(o instanceof Message))
			return false ;
		
		String titre = ((Message)o).titre;
		String contenu = ((Message)o).contenu; 
		if(this.titre==titre && this.contenu == contenu)
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
		return "Message [titre=" + titre + ", contenu=" + contenu + ", creation=" + creation + ", etat=" + etat
				+ ", taille=" + taille + "]";
	}
	
	//we need modify message method
	
	public void saisie() throws MoreThan10485760, ExceptionPieceExistante   {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----Veuillez insérez l'objet de votre message----");
		titre = scanner.nextLine();
		System.out.println("----Veuillez insérez le contenu de votre message----");
		contenu = scanner.nextLine();
		creation = new Date() ;		//Date() returns the current system date.
		etat = Etat.CREE ;
		taille = titre.length() + contenu.length() ;
		
	}
	
	
	
	
	
	
}	
