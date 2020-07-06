package t;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Profil {
	enum Genre {
		FEMININ, MASCULAIN;
	}
	private String nom;
	private String prenom;
	private int age;
	private String numero;
	private String pays;
	private Genre genre;
	private HashMap<String ,AdrEmail> Adresses ;
	
	public Profil(){
		
	}
	
	public Profil(String nom, String prenom, int age, String numero, String pays, String genre) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.numero = numero;
		this.pays = pays;
		this.genre = Genre.valueOf(genre);
		Adresses = new HashMap<String ,AdrEmail>() ;
	}
	
	public void saisieProfil() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----Veuillez insérez votre Nom du profil----");
		nom = scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Prenom du profil----");
		prenom = scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Age----");
		age = scanner.nextInt();
		
		System.out.println("----Veuillez insérez votre Numero de telephone----");
		numero = scanner.nextLine(); scanner.nextLine();
		
		System.out.println("----Veuillez insérez votre Pays----");
		pays = scanner.nextLine(); 
		
		System.out.println("----Veuillez insérez votre Genre----");
		int k;
		
		do { 
			System.out.println("----1 Pour masculain----");
			System.out.println("----2 Pour feminin----");
			k = scanner.nextInt();
			if(k == 1) {
				genre = Genre.valueOf("MASCULAIN");
			}
			else if(k == 2) {
				genre = Genre.valueOf("FEMININ");
			}
		} while(k < 1 || k > 2);
	}

	@Override
	public String toString() {
		return "profil [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", numero=" + numero + ", pays=" + pays
				+ ", genre=" + genre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profil other = (Profil) obj;
		if (age != other.age)
			return false;
		if (genre != other.genre)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	public void addAdress(AdrEmail adress) {
		Adresses.put(adress.toString(), adress);
	}
	
	public HashMap<String, AdrEmail> getAdresses() {
		return Adresses;
	}

	public void setAdresses(HashMap<String, AdrEmail> adresses) {
		Adresses = adresses;
	}
	

	public void DeleteAdr(String adr) {
		//return the adress if found else null
		Adresses.remove(adr);
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
	 
	
}
