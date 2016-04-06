import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ergebnis {
	private static Logger log = LogManager.getRootLogger();
	
	private Date laufZeit;
	
	private Ergebnisstatus status;
	
	public Ergebnisstatus getStatus() {
		return status;
	}

	private float distanz;
	ArrayList<Zwischenzeit> zwischenzeiten;
	Veranstaltung veranstatung;
	Laeufer laeufer;

	public Ergebnis(Laeufer laeufer, Veranstaltung veranstaltung, Date laufzeit) {
		this.laeufer = laeufer;
		this.veranstatung = veranstaltung;
		this.laufZeit = laufzeit;
	}


}
