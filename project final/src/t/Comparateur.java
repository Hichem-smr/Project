package t;

import java.util.Comparator;

public class Comparateur implements Comparator<Message>{
	
	
	public int compare(Message arg0, Message arg1) {
		// TODO Auto-generated method stub
	
		if (arg0.getCreation().compareTo(arg1.getCreation())!=0)
			return arg0.getCreation().compareTo(arg1.getCreation());
		else
			return arg0.getTitre().compareTo(arg1.getTitre()) ;
	}

}
