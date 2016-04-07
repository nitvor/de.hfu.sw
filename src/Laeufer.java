import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.LaeuferDTO;

public class Laeufer extends Person {
	private static Logger log = LogManager.getRootLogger();
	/*
	 * In welchem Verein sich der Laeufer befindet.
	 */
	private Verein vereinszugehoerigkeit;
	/*
	 * Liste der Anmeldungen
	 */
	ArrayList<Anmeldung> anmeldungen = new ArrayList<Anmeldung>();
	/*
	 * Liste der Ergebnisse
	 */
	ArrayList<Ergebnis> ergebnisse = new ArrayList<Ergebnis>();

	public Laeufer(String name, String vorname, int geburtsjahr, char geschlecht, String email, String telefonnummer,
			String strasse, String plz, String ort, String land, Verein vereinszugehoerigkeit) {
		super(name, vorname, geburtsjahr, geschlecht, email, telefonnummer, strasse, plz, ort, land);
		this.vereinszugehoerigkeit = vereinszugehoerigkeit;
	}

	/*
	 * Damit sich der Laeufer an der Veranstaltung anmelden kann.
	 */
	public void anmeldungHinzufuegen(Anmeldung anmeldung) throws Exception {
		boolean exist = false;
		for (Anmeldung vorhandeneAnmeldung : this.anmeldungen) {
			if (vorhandeneAnmeldung.getVeranstaltung() == anmeldung.getVeranstaltung()) {
				exist = true;
				break;
			}
		}
		if (exist) {
			log.info("Laeufer: " + this.getName() + "hat sich bereits fuer die Veranstaltung "
					+ anmeldung.getVeranstaltung() + "angemeldet.");
			throw new Exception("Anmeldung fÃ¼r die Veranstaltung exestiert");
		} else {
			log.info("Laeufer: " + this.getName() + "hat sich für die Veranstaltung " + anmeldung.getVeranstaltung()
					+ "angemeldet.");
			this.anmeldungen.add(anmeldung);
		}
	}

	/*
	 * Um die ErgebnisListe zu erzeugen und diese zurueck zugeben.
	 */
	public Ergebnis[] erzeugeErgebnisListe() {
		log.info("Erzeuge Ergebnisliste fuer den Laeufer " + this.getName());
		ArrayList<Ergebnis> result = new ArrayList<Ergebnis>();
		for (int i = 0; i > ergebnisse.size(); i++) {
			if (this.ergebnisse.get(i).getStatus() == Ergebnisstatus.beendet) {
				result.add(this.ergebnisse.get(i));
			}
		}
		return (Ergebnis[]) result.toArray();
	}

	// getter
	public Verein getVereinszugehoerigkeit() {
		return vereinszugehoerigkeit;
	}

	// setter
	public void setVereinszugehoerigkeit(Verein vereinszugehoerigkeit) {
		this.vereinszugehoerigkeit = vereinszugehoerigkeit;
	}

	public LaeuferDTO generateDTO() {
		return new LaeuferDTO(this.getName(), this.getVorname(), this.getGeburtsjahr(), this.getGeschlecht(),
				this.getEmail(), this.getTelefonnummer(), this.getStrasse(), this.getPlz(), this.getOrt(),
				this.getLand());
	}

}
