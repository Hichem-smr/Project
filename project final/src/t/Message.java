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
	private piece_jointe piece_jointe;
	private String titre ;
	private String contenu ;
	private Date creation ;
	private Etat etat ;
	int taille = titre.length() + contenu.length();
	
	
	Message(){
		setEtat(Etat.CREE) ;
		taille = 0 ;
	}
	
	Message(String titre, String contenu , Date creation , Etat etat ){
		
		this.creation = creation ;
		this.titre = titre ;
		this.contenu = contenu ; 
		this.setEtat(etat) ;
		
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
	void saisie_message() throws MoreThan10000000 {
		
		
		
		// add the others here
		// saisie de piece jointe
		int k;
		System.out.println("----Veuillez vous inserez une piece-jointe?----");
		do {
			System.out.println("1--Oui");
			System.out.println("2--Non");
			k = scanner.nextInt();
		} while(k != 1 && k != 2);
		
		//la saisie de piece jointe
		
		if(k == 1) {
			piece_jointe = new piece_jointe();
			piece_jointe.saisie();
		}
		
	}
	
	
	
}	
