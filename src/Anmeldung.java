import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;

public class Anmeldung {
	static Logger log = LogManager.getRootLogger();
	/*
	 * Unique-ID fuer die Ueberweisung
	 */
	private UUID idNummer;

	/*
	 * Status der Anmeldung
	 */
	private Anmeldestatus status;
	/*
	 * Datum der Anmeldungserzeugung
	 */
	private Date datumDerAnmeldung;
	/*
	 * Zahlungseingangsdatum
	 */
	private Date zahlungsEingangsDatum;
	
	private Laeufer laeufer;
	private Veranstaltung veranstaltung;
	

	/*
	 *Startnummer bei der Veranstaltung
	 *
	 *default 0
	 * wird erst dann generiert, wenn die Gebuehr gezahlt wurde 
	 */
	private int startnummer;
	
	public Anmeldung(Veranstaltung veranstaltung, Laeufer leufer){
		log.info("Anmeldung fuer die Veranstaltung-"+veranstaltung.getName()+"fuer Laufer:"
				+leufer.getName()+" erzeugt");
		this.idNummer = UUID.randomUUID();
		this.laeufer = leufer;
		this.veranstaltung = veranstaltung;
		this.status = Anmeldestatus.angemeldet;
		this.datumDerAnmeldung = new Date();
		this.startnummer = 0;
	}
	
	/*
	 * Anmledung zurück ziehen
	 * 
	 * TODO: 
	 * -Information an die Verwaltung weitungleiten, wenn die Genuehr bereits gezahlt wurde
	 * -Security: Sollte nur vom Leufer aufgerufen werden 
	 */
	public void anmeldungZurueckziehen() {
		log.info(this.createLogInfo());
		this.status = Anmeldestatus.abgemeldet;
	}
	
	/*
	 * Anmeldegebuehr wurde uberwiesen
	 * startnummer wird erzeugt
	 * 
	 * TODO:
	 * -Security: Sollte nur vom Veranstalter aufgerufen werden 
	 */
	public void anmeldungGezahlt(){
		log.info("Anmeldung: " + idNummer + " wurde gezahlt.");
		this.status = Anmeldestatus.bezahlt;
		this.zahlungsEingangsDatum = new Date();
		this.startnummer = this.veranstaltung.generateStartNummer();
	}

	//getter
	public int getStartnummer() {
		return startnummer;
	}
	
	public Anmeldestatus getStatus() {
		return status;
	}

	public Date getDatumDerAnmeldung() {
		return datumDerAnmeldung;
	}

	public Date getZahlungsEingangsDatum() {
		return zahlungsEingangsDatum;
	}
	
	public UUID getIdNummer() {
		return idNummer;
	}
	
	public Veranstaltung getVeranstaltung() {
		return veranstaltung;
	}
	
	// Private-Methoden
	private String createLogInfo() {
		String result = "Anmeldung: " + idNummer + " zurueckgezogen.";
		result += "Anmeldunggeb�hr wurde" + (this.zahlungsEingangsDatum != null ? "" : "nicht ") + "gezahlt";
		return result;
	}
}