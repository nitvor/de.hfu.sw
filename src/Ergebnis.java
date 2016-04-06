import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.LaufzeitDTO;

public class Ergebnis extends Zwischenzeit {
	private static Logger log = LogManager.getRootLogger();
	
	private Ergebnisstatus status;
	public int position = 0;
	ArrayList<Zwischenzeit> zwischenzeiten;
	
	Veranstaltung veranstatung;
	Laeufer laeufer;

	public Ergebnis(Laeufer laeufer, Veranstaltung veranstaltung, Date laufzeit, float distanz) {
		super(distanz, laufzeit);
		this.laeufer = laeufer;
		this.veranstatung = veranstaltung;
		this.zwischenzeiten = null;
	}
	
	public LaufzeitDTO generateDTO(){
		int nummer = 0;
		for(Anmeldung a : this.laeufer.anmeldungen){
			if(a.getVeranstaltung() == this.veranstatung){
				nummer = a.getStartnummer();
				break;
			}
		}
		return new LaufzeitDTO(
				nummer,
				this.getLaufzeit(),
				this.veranstatung.getName()
				);
	}

	// getter
	public Ergebnisstatus getStatus() {
		return status;
	}
	
	
}
