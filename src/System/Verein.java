package System;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.VereinDTO;

public class Verein {
	static Logger log = LogManager.getRootLogger();
	/*
	 * Name des Vereins
	 */
	private String name;
	/*
	 * Laeufer die zu diesem Verein gehören.
	 */
	ArrayList<Laeufer> mitglieder;

	public Verein(String name) {
		this.name = name;
	}

	/*
	 * Um Laeufer dem Verein hinzuzufügen
	 */
	public void mitgliedHinzufuegen(Laeufer laeufer) throws Exception {
		if (laeufer.getVereinszugehoerigkeit() != null) {
			log.info("Der Laeufer " + laeufer.getName() + "ist nun Mitglied in dem Verein " + this.getName());
			this.mitglieder.add(laeufer);
			laeufer.setVereinszugehoerigkeit(this);
		} else {
			throw new Exception("Laefer gehoert bereits einem Verein an");
		}
	}

	/*
	 * Um Laeufer aus dem Verein zu entfernen
	 */
	public void mitgliedEntfernen(Laeufer laeufer) throws Exception {
		boolean isIn = false;
		for (Laeufer mitglied : this.mitglieder) {
			if (mitglied == laeufer) {
				isIn = true;
				break;
			}
		}
		if (!isIn) {
			log.info("Der Laeufer " + laeufer.getName() + "ist nun in dem Verein " + this.getName()
					+ "kein Mitglied mehr.");
			this.mitglieder.remove(laeufer);
			laeufer.setVereinszugehoerigkeit(null);
		} else {
			throw new Exception("Laefer nicht in dem Verein");
		}
	}

	// getter
	public String getName() {
		return name;
	}

	public VereinDTO generateDTO() {
		return new VereinDTO(this.name);
	}
}
