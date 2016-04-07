import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Anmeldung {
	static Logger log = LogManager.getRootLogger();
	private int idNummer;
	private Anmeldestatus status;
	private Date datumDerAnmeldung;
	private Date zahlungsEingangsDatum;

	public void anmeldungZurueckziehen() {
		log.info(this.createLogInfo());
	}

	private String createLogInfo() {
		String result = "Anmeldung: " + idNummer + " zurückgezogen.";
		result += "Anmeldunggebühr wurde" + (this.zahlungsEingangsDatum != null ? "" : "nicht ") + "gezahlt";
		return result;
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
}
