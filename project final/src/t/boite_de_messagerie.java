package t;

import java.util.HashSet;
import java.util.Iterator;

public class boite_de_messagerie {
	//here's what we'll do,
	//all messages will be the Type Message Attach, and the user will have the choice
	//to whether treat it as a message with a file or not
	private MessageAttach message = new MessageAttach();
	private HashSet<MessageAttach> reçus = new HashSet();
	private HashSet<MessageAttach> envoyés = new HashSet();;
	private HashSet<MessageAttach> brouillons = new HashSet();;
	private HashSet<MessageAttach> archives = new HashSet();;
	private HashSet<MessageAttach> corbeille = new HashSet();;
	private HashSet<MessageAttach> spam = new HashSet();;
	long capacité;
	
	void AddReçu(MessageAttach message) {
		 reçus.add(message) ;
		 System.out.println("---- Message Added !! ----");
	}
	
	

}
