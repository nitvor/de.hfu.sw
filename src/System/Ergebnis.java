package System;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.LaufzeitDTO;

public class Ergebnis extends Zwischenzeit {
	private static Logger log = LogManager.getRootLogger();
	/*
	 * Status des Ergebnis
	 */
	private Ergebnisstatus status;
	/*
	 * Position des Laeufers
	 */
	public int position = 0;
	/*
	 * Liste von Zwischenzeiten
	 */
	ArrayList<Zwischenzeit> zwischenzeiten;
	
	Veranstaltung veranstaltung;
	Laeufer laeufer;

	public Ergebnis(Laeufer laeufer, Veranstaltung veranstaltung, Date laufzeit, float distanz) {
		super(distanz, laufzeit);
		this.laeufer = laeufer;
		this.veranstaltung = veranstaltung;
		this.zwischenzeiten = null;
	}
	
	public LaufzeitDTO generateDTO(){
		int nummer = 0;
		for(Anmeldung a : this.laeufer.anmeldungen){
			if(a.getVeranstaltung() == this.veranstaltung){
				nummer = a.getStartnummer();
				break;
			}
		}
		return new LaufzeitDTO(
				nummer,
				this.getLaufzeit(),
				this.veranstaltung.getName()
				);
	}

	// getter
	public Ergebnisstatus getStatus() {
		return status;
	}
	
	
}
