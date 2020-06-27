package t;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.TreeSet;


public class MessageAttach extends Message {
	
	private HashSet<piece_jointe> attachement ;
	private long tailleattachement =0;
	
	
	MessageAttach(){
		attachement = new HashSet<piece_jointe>() ;
		setEtat(Etat.CREE);
		tailleattachement=0 ;
	}
	
	MessageAttach(String titre , String contenu , Date creation, Etat etat, HashSet<piece_jointe> attachement ){
		super(titre , contenu , creation , etat );
		this.attachement = attachement ;
		Iterator it = attachement.iterator() ;
		
		while(it.hasNext()) {
			tailleattachement += ((piece_jointe)it.next()).getTaille() ;
		}
	}
	
	
	MessageAttach(MessageAttach msg){
		super(msg) ;
		this.attachement = new HashSet<piece_jointe>(msg.attachement) ;
	}
	
	
	public String toString() {
		return(super.toString() +"Attachement :" + attachement + "\n\n") ;
		
	}
	
	
	public void saisie() throws MoreThan10485760, ExceptionPieceExistante {
		
		Scanner scanner = new Scanner(System.in);
		int choix ;

		super.saisie();
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
	
	
	
}
