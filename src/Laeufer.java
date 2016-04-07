import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Laeufer extends Person {
	static Logger log = LogManager.getRootLogger();
	private Verein vereinszugehoerigkeit;
	private int laufNummer;
	ArrayList<Laeufer> anmeldungen;
	ArrayList<Ergebnis> ergebnisse;

	public Laeufer(String name, String vorname, String adresse, char geschlecht, String geburtsjahr, String email,
			String telefonnummer, boolean veranstalter, boolean schitzrichter, boolean vorstand,
			Verein vereinszugehoerigkeit) {
		super(name, vorname, adresse, geschlecht, geburtsjahr, email, telefonnummer, veranstalter, schitzrichter,
				vorstand);
		this.vereinszugehoerigkeit = vereinszugehoerigkeit;
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

	public int getLaufNummer() {
		return laufNummer;
	}

	public void setLaufNummer(int laufNummer) {
		this.laufNummer = laufNummer;
	}

	public Verein getVereinszugehoerigkeit() {
		return vereinszugehoerigkeit;
	}
	
	
}
