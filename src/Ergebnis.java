import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ergebnis extends Zwischenzeit {
	private static Logger log = LogManager.getRootLogger();
	
	private Ergebnisstatus status;
	ArrayList<Zwischenzeit> zwischenzeiten;
	
	Veranstaltung veranstatung;
	Laeufer laeufer;

	public Ergebnis(Laeufer laeufer, Veranstaltung veranstaltung, Date laufzeit, float distanz) {
		super(distanz, laufzeit);
		this.laeufer = laeufer;
		this.veranstatung = veranstaltung;
		this.zwischenzeiten = null;
	}
	

	// getter
	public Ergebnisstatus getStatus() {
		return status;
	}
}
