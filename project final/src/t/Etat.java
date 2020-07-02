package t;

public enum Etat	{
	CREE("Créé") , ENVOYE("Envoyé") , RECU("Reçu") , EN_COURS("En cours") , 
	STOCKE("Stocké") , SUPPRIME("Supprimé") , ARCHIVE("Archivé") , RETOURNE("Retourné");
	
	public String displayvalue ;
	Etat(String displayvalue){
		this.displayvalue= displayvalue;
	}
	
}
