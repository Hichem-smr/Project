package t;

public class Prog {

	public static void main(String[] args) {
		AdrEmail adr = new AdrEmail();
		adr.Saisie_AdrEmail();
		System.out.println(adr.toString());
		System.out.println(adr.getMdp());
		System.out.println(adr.getSite());

	}

}
