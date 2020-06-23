package t;

import java.util.Date;

enum Etat{
	CREE("Créé") , ENVOYE("Envoyé") , RECU("Reçu") , EN_COURS("En cours") , 
	STOCKE("Stocké") , SUPPRIME("Supprimé") , ARCHIVE("Archivé") , RETOURNE("Retourné");
	
	String displayvalue ;
	Etat(String displayvalue){
		this.displayvalue= displayvalue;
	}
	
}

public class Message {
	
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
}	
