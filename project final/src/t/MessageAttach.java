package t;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.TreeSet;


public class MessageAttach extends Message {
	
	private HashSet<piece_jointe> attachement =new HashSet<piece_jointe>() ; ;
	private long tailleattachement =0;
	
	
	public MessageAttach(){
		setEtat(Etat.CREE);
		tailleattachement=0 ;
	}
	
	public MessageAttach(String titre , String contenu,  HashSet<piece_jointe> attachement ){
		super(titre , contenu );
		this.attachement= attachement ;	
		tailleattachement = 0;
		for(piece_jointe p : this.attachement) {
			tailleattachement += p.getTaille() ;
		}
		
		
	}
	
	
	public MessageAttach(Message msg){
		super(msg) ;
		
		if(!(msg instanceof MessageAttach))
			return ;
		
		this.attachement = new HashSet<piece_jointe>(((MessageAttach)msg).getAttachement()) ;
		tailleattachement = 0 ;
		for(piece_jointe p : this.attachement) {
			tailleattachement += p.getTaille() ;
		}
	}
	
	
	public String toString() {
		
		if(attachement!=null)
			return(super.toString() +"Attachement :" + attachement + "\n\n") ;
		else
			return(super.toString() + "\n\n");
		
	}
	
	
	public void saisie(int a ) throws MoreThan10485760, ExceptionPieceExistante {
		
		Scanner scanner = new Scanner(System.in);
		int choix ;

		super.saisie(a);
		System.out.println("----Voulez vous ajouter des pieces jointes ?----");	
		do {
			System.out.println("\t1.Oui");
			System.out.println("\t2.Non");
			choix = scanner.nextInt() ; scanner.nextLine() ;
		}while(choix!=1 && choix!=2);
		
		if(choix==1) {
			attachement = new HashSet<piece_jointe>() ;
			do {
				piece_jointe piece = new piece_jointe(); 
				piece.saisie();
		
				if(attachement.contains(piece)) {
					throw(new ExceptionPieceExistante("Cette piece Existe deja dans votre attachement"));
				}
				
				attachement.add(piece) ;
				tailleattachement += piece.getTaille() ;
				
				if(tailleattachement + super.getTaille()>10485760) {
					throw( new MoreThan10485760("Taille du message depasse 10MO"));
				}
				
				System.out.println("----Voulez vous en ajouter d'autres?----");
				do {
					System.out.println("\t1.Oui");
					System.out.println("\t2.Non");
					choix = scanner.nextInt() ; scanner.nextLine() ;
				}while(choix!=1 && choix!=2);
				
			}while(choix==1) ;
		}
		
	}
	
	
	public void ajouterPiece(piece_jointe piece) throws ExceptionPieceExistante {
		
		boolean b = attachement.add(piece) ;
		if(!b) {
			throw(new ExceptionPieceExistante("Cette piece Existe deja dans votre attachement"));
		}
	}

	public long getTailleattachement() {
		return tailleattachement;
	}

	public void setTailleattachement(long tailleattachement) {
		this.tailleattachement = tailleattachement;
	}

	public HashSet<piece_jointe> getAttachement() {
		return attachement;
	}

	public void setAttachement(HashSet<piece_jointe> attachement) {
		this.attachement = attachement;
	}
	
	public void afficher() {
		
		super.afficher();
		System.out.println(attachement);
	}
	
	
	public boolean equals(Object o) {
		
		String titre;
		String contenu ;
		
		if (o==null)
			return false ;
		
		if(! (o instanceof Message))
			return false ;
		
		if(!(o instanceof MessageAttach)) {
			if(attachement!=null)
				return false ;
			
			titre = ((Message)o).getTitre();
			contenu = ((Message)o).getContenu(); 
			if((this.getTitre().contentEquals(titre) || this.getTitre().contentEquals(titre+" (NON LU)")) && this.getContenu().contentEquals(contenu))
				return true ;
		}
		
		titre = ((Message)o).getTitre();
		contenu = ((Message)o).getContenu(); 
		HashSet<piece_jointe> attachement = ((MessageAttach)o).getAttachement();
		
		if((this.getTitre().contentEquals(titre) || this.getTitre().contentEquals(titre+" (NON LU)")) && this.getContenu().contentEquals(contenu)
				&& this.attachement.equals(attachement) )
			return true ;
		
		return false ;
		
	}
	
	public int hashCode() {
		return 0;
	}
}
