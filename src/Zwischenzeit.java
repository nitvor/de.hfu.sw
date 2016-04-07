import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Zwischenzeit implements Comparable<Zwischenzeit> {
	static Logger log = LogManager.getRootLogger();

	private float distanz;
	private Date laufzeit;
	
	public Zwischenzeit(float distanz,Date laufzeit){
		this.laufzeit = laufzeit;
		this.distanz = distanz;
	}

	/*
	 * Laufzeit veraendern
	 * 
	 * TODO: Sollte nur vom Schiedsrichter aufgerufen werden koennen
	 */
	public void zeitKorrektur(Date zeit) {
		this.laufzeit = zeit;
	}
	
	//getter
	public float getDistanz() {
		return distanz;
	}


	public Date getLaufzeit() {
		return laufzeit;
	}

	@Override
	public int compareTo(Zwischenzeit o) {
		int result =0;
		if(this.distanz == o.distanz){
			result = this.laufzeit.compareTo(o.laufzeit);
		}
		return result;
	}
}
