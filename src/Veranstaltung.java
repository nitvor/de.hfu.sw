import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.AnmeldungDTO;
import swa.runningeasy.dtos.LaeuferDTO;
import swa.runningeasy.dtos.VeranstaltungDTO;

public class Veranstaltung {

	private static Logger log = LogManager.getRootLogger();
	
	private String name;	
	private float distanz;
	private Date termin;
	
	private Date anmeldeschluss;
	

	private int startgebuehr;
	private String zahlungsinformationen;
	
	private ArrayList<Anmeldung> anmeldungen;
	
	private Ergebnisliste ergebnisListe = new Ergebnisliste();
	

	private Startliste startliste = new Startliste();
	
	

	private int startNummerCounter = 0;
	
	public Veranstaltung(String name, float distanz, Date termin) {
		this.name = name;
		this.distanz = distanz;
		this.termin = termin;
	}
	
	public Veranstaltung(String name, float distanz, Date termin,int startNummerCounter) {
		this(name,distanz,termin);
		this.startNummerCounter = startNummerCounter;
	}
	
	
	public void Anmelden(Laeufer laufer) throws Exception{
		Anmeldung anmeldung = new Anmeldung(this, laufer);
		try{
			laufer.anmeldungHinzufuegen(anmeldung);
			this.anmeldungen.add(anmeldung);
		}catch(Exception e){
			throw e;
		}
	}
	/*
	 * Gibt die Liste aller Laufer zurueck, die die Startgebühr nicht beszahlt haben
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
	
	//TODO
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
				this.log.error(e.getMessage());
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
			throw new Exception("Laeufer nicht gefunden");
		}
		return result;
	}
}
