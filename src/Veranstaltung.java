import java.util.Date;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Veranstaltung {
	//TODO: IN ARBEIT
	private static Logger log = LogManager.getRootLogger();
	
	private String name;	
	private float distanz;
	private Date termin;
	
	private Date anmeldeschluss;
	private int startgebuehr;
	private String zahlungsinformationen;
	
	private ArrayList<Anmeldung> anmeldungen;
	
	public Veranstaltung(String name, float distanz, Date termin) {
		this.name = name;
		this.distanz = distanz;
		this.termin = termin;
	}
	
	public void getListeStargebuehrNichtBezahlt() {
		
	}
	
	public void createListen() {
		
	}
	
	public void versandLaufzeitPlatzierung() {
		
	}
	
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
	//TODO
	public int generateStartNummer(){
		return 0;
	}
	
}
