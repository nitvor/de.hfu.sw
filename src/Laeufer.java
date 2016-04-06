import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Laeufer extends Person {
	private static Logger log = LogManager.getRootLogger();
	
	private Verein vereinszugehoerigkeit;
	ArrayList<Anmeldung> anmeldungen = new ArrayList<Anmeldung>();
	ArrayList<Ergebnis> ergebnisse = new ArrayList<Ergebnis>();

	public Laeufer(String name, String vorname, int geburtsjahr,
			char geschlecht, String email, String telefonnummer, String strasse,
			String plz, String ort, String land,
			Verein vereinszugehoerigkeit) {
		super(name,vorname,geburtsjahr,
				geschlecht,email,telefonnummer,strasse,
				plz, ort,land);
		this.vereinszugehoerigkeit = vereinszugehoerigkeit;
	}
	
	public void anmeldungHinzufuegen(Anmeldung anmeldung) throws Exception{
		boolean exist = false;
		for(int i = 0; i< this.anmeldungen.size(); i++){
			if(this.anmeldungen.get(i).getVeranstaltung() == anmeldung.getVeranstaltung()){
				exist = true;
				break;
			}
		}
		if(exist){
			throw new Exception("Anmeldung für die Veranstaltung exestiert");
		}else{
			this.anmeldungen.add(anmeldung);
		}
	}

	public Ergebnis[] erzeugeErgebnisListe() {
		log.info("Erzeuge Ergebnisliste fuer den Laeufer " + this.getName());
		ArrayList<Ergebnis> result = new ArrayList<Ergebnis>();
		for(int i = 0; i > ergebnisse.size(); i++){
			if(this.ergebnisse.get(i).getStatus() == Ergebnisstatus.beendet){
				result.add(this.ergebnisse.get(i));
			}
		}
		return (Ergebnis[])result.toArray();
	}

	public Verein getVereinszugehoerigkeit() {
		return vereinszugehoerigkeit;
	}
	
	
}
