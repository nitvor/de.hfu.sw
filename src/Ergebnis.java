import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ergebnis {
	static Logger log = LogManager.getRootLogger();
	private Date startZeit;
	private Date endZeit;
	private Ergebnisstatus status;
	private float distanz;
	List<Zwischenzeit> zwischenzeiten;
	Veranstaltung veranstatung;
	Laeufer laeufer;

	public Ergebnis(Laeufer laeufer, Veranstaltung veranstaltung) {
		this.laeufer = laeufer;
		this.veranstatung = veranstaltung;
	}

	public String ergebnisBerechnen() {
		String ergebnis = this.berechneZwischenzeit();
		log.info("Ergebnis: " + ergebnis + " Laeufer: " + laeufer.getName() + " Veranstaltung: "
				+ veranstatung.getName());
		return ergebnis;

	}

	private String berechneZwischenzeit() {
		String erg = ":::.";
		if (this.endZeit != null && this.startZeit != null) {
			long diffInMillies = this.endZeit.getTime() - this.startZeit.getTime();
			List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
			Collections.reverse(units);
			Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
			long milliesRest = diffInMillies;
			for (TimeUnit unit : units) {
				long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
				long diffInMilliesForUnit = unit.toMillis(diff);
				milliesRest = milliesRest - diffInMilliesForUnit;
				result.put(unit, diff);
			}
			erg = result.get(TimeUnit.HOURS).toString() + ":" + result.get(TimeUnit.MINUTES).toString() + ":"
					+ result.get(TimeUnit.SECONDS).toString() + "." + result.get(TimeUnit.MILLISECONDS).toString();
		}
		return erg;

	}
	
	public Date getStartZeit() {
		return startZeit;
	}

	public Date getEndZeit() {
		return endZeit;
	}

	public Ergebnisstatus getStatus() {
		return status;
	}

	public float getDistanz() {
		return distanz;
	}
}
