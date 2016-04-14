package System;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import swa.runningeasy.dtos.AnmeldungDTO;
import swa.runningeasy.dtos.LaeuferDTO;
import swa.runningeasy.dtos.VeranstaltungDTO;

public class Veranstaltung {

	private static Logger log = LogManager.getRootLogger();
	/*
	 * Name der Veranstaltung
	 */
	private String name;	
	private float distanz;
	/*
	 * Termin von der Veranstaltung
	 */
	private Date termin;
	/*
	 * Anmeldeschluss zur Veranstaltung
	 */
	private Date anmeldeschluss;
	/*
	 * Startgebuehr fuer die Veranstaltung
	 */
	private int startgebuehr;
	/*
	 * Zahlungsinformation fuer die Startgebuehr
	 */
	private String zahlungsinformationen;
	/*
	 * Liste der Anmeldungen
	 */
	private ArrayList<Anmeldung> anmeldungen = new ArrayList<Anmeldung>();
	/*
	 * Liste der Ergebnisse
	 */
	private Ergebnisliste ergebnisListe = new Ergebnisliste();
	/*
	 * Liste mit den Laeufern die Starten
	 */
	private Startliste startliste = new Startliste();
	/*
	 * Fuer die Startnummer der Lauefer
	 */
	private int startNummerCounter = 0;
	
	public Veranstaltung(String name, float distanz,int startGebuer, Date termin, Date anmeldeschluss) {
		this.name = name;
		this.distanz = distanz;
		this.termin = termin;
		this.anmeldeschluss = anmeldeschluss;
		this.startgebuehr = startGebuer;
	}
	
	public Veranstaltung(String name, float distanz,int startGebuer, Date termin, Date anmeldeschluss,int startNummerCounter) {
		this(name,distanz,startGebuer,termin,anmeldeschluss);
		this.startNummerCounter = startNummerCounter;
	}
	/*
	 * Damit der Lauefer zur Veranstaltung angemeldet ist.
	 */
	public Anmeldung Anmelden(Laeufer laufer) throws Exception{
		Anmeldung anmeldung = new Anmeldung(this, laufer);
		try{
			laufer.anmeldungHinzufuegen(anmeldung);
			this.anmeldungen.add(anmeldung);
			return anmeldung;
		}catch(Exception e){
			throw e;
		}
	}
	/*
	 * Gibt die Liste aller Laeufer zurueck, die die Startgebuehr nicht beszahlt haben
	 */
	public Liste getListeStargebuehrNichtBezahlt() {
		Liste result = new Liste();
		for(Anmeldung anmeldung : this.anmeldungen){
			if(anmeldung.getStatus() == Anmeldestatus.angemeldet){
				try{
					result.hinzufuegen(anmeldung.getLaeufer());
				}catch(Exception e){
					this.log.error(e.getMessage());
				}
			}
		}
		return result;
	}
	/*
	 * Erzeugt die Startliste
	 */
	public void generateStartList(){
		for(Anmeldung anmeldung: this.anmeldungen){
			try{
				if(anmeldung.getStatus() == Anmeldestatus.bezahlt){
					this.startliste.hinzufuegen(anmeldung.getLaeufer(), anmeldung.getStartnummer());
				}
			}catch(Exception e){
				log.error(e.getMessage());
			}
		}
	}

	/*
	 * Versendet die Laufzeit des Leufers bei dieser Veranstaltung per
	 * SMS an die Angegebenne Telefonnummer
	 */
	public void versandLaufzeitPlatzierung(Laeufer laufer, String telefonnummer) {
		//TODO
	}
	
	//Generiert die Startnummer fuer den Laeufer
	public int generateStartNummer(){
		return ++this.startNummerCounter;
	}
	
	//getter
	public String getName() {
		return name;
	}

	public float getDistanz() {
		return distanz;
	}

	public Date getTermin() {
		return termin;
	}

	public String getZahlungsinformationen() {
		return zahlungsinformationen;
	}
	
	public Date getAnmeldeschluss() {
		return anmeldeschluss;
	}

	public int getStartgebuehr() {
		return startgebuehr;
	}
	
	public Startliste getStartliste() {
		return startliste;
	}
	
	public Ergebnisliste getErgebnisListe() {
		return ergebnisListe;
	}
	
	public VeranstaltungDTO generateDTO(){
		VeranstaltungDTO res = new VeranstaltungDTO(
				this.name,
				this.termin,
				this.anmeldeschluss,
				this.startgebuehr,
				this.distanz
				);
		return res;
	}
	
	public List<AnmeldungDTO> getAnmeldungenDTO(){
		List<AnmeldungDTO> res = new ArrayList<AnmeldungDTO>();
		for(Anmeldung a : this.anmeldungen){
			res.add(a.generateDTO());
		}
		return res;
	}
	
	public Liste getAlleGemeldetenLaeufer(){
		Liste result = new Liste();
		for(Anmeldung a : this.anmeldungen){
			try{
				result.hinzufuegen(a.getLaeufer());
			}catch(Exception e){
				log.error(e.getMessage());
			}
		}
		return result;
	}
	
	public Laeufer getLaeuferByStartNummer(int nummer) throws Exception{
		Laeufer result = null;
		for(Anmeldung a : this.anmeldungen){
			if(a.getStartnummer() == nummer){
				result = a.getLaeufer();
				break;
			}
		}
		if(result == null){
			throw new Exception("Laeufer mit nummer "+nummer+" nicht gefunden");
		}
		return result;
	}
}
