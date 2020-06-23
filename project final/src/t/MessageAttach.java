package t;

import java.util.Date;
import java.util.HashSet;

class PieceJointe{
	
	private String nom ;
	private int taille ;
	
	PieceJointe(){
		
	}
	
	PieceJointe(String nom , int taille ){
		
		this.nom = nom ;
		this.taille = taille ;
	}
	
	
	public boolean equals(Object o) {
		
		if (o==null)
			return false ;
		
		if(!(o instanceof MessageAttach))
			return false ;
		
		String nom = ((PieceJointe)o).nom;
		int taille = ((PieceJointe)o).taille; 
		if(this.nom == nom && this.taille==taille)
			return true ;
		
		return false ;
	}
}



public class MessageAttach extends Message {
	
	private HashSet<PieceJointe> attachement ;
	
	MessageAttach(){
		setEtat(Etat.CREE);
	}
	
	MessageAttach(String titre , String contenu , Date creation, Etat etat, HashSet<PieceJointe> attachement ){
		super(titre , contenu , creation , etat );
		this.attachement = attachement ;
	}
	
	
	
	
}
