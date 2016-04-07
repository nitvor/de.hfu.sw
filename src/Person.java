
public class Person {
	private String name;
	private String vorname;
	private String adresse;
	private char geschlecht;
	private String geburtsjahr;
	private String email;
	private String telefonnummer;
	private boolean veranstalter;
	private boolean schitzrichter;
	private boolean vorstand;

	public Person(String name, String vorname, String adresse, char geschlecht, String geburtsjahr, String email,
			String telefonnummer, boolean veranstalter, boolean schitzrichter, boolean vorstand) {
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.geschlecht = geschlecht;
		this.geburtsjahr = geburtsjahr;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.veranstalter = veranstalter;
		this.schitzrichter = schitzrichter;
		this.vorstand = vorstand;
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public String getAdresse() {
		return adresse;
	}

	public char getGeschlecht() {
		return geschlecht;
	}

	public String getGeburtsjahr() {
		return geburtsjahr;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public boolean isVeranstalter() {
		return veranstalter;
	}

	public boolean isSchitzrichter() {
		return schitzrichter;
	}

	public boolean isVorstand() {
		return vorstand;
	}
	
	
}
