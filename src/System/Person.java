package System;

public class Person {
	
	private String name;
	private String vorname;
	private int geburtsjahr;
	private char geschlecht;
	private String email;
	private String telefonnummer;
	private String strasse;
	private String plz;
	private String ort;
	private String land;
	
	public Person(String name, String vorname, int geburtsjahr,
			char geschlecht, String email, String telefonnummer, String strasse,
			String plz, String ort, String land) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.geburtsjahr = geburtsjahr;
		this.geschlecht = geschlecht;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}
	
	// getter
	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public int getGeburtsjahr() {
		return geburtsjahr;
	}

	public char getGeschlecht() {
		return geschlecht;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public String getStrasse() {
		return strasse;
	}

	public String getPlz() {
		return plz;
	}

	public String getOrt() {
		return ort;
	}

	public String getLand() {
		return land;
	}

}
