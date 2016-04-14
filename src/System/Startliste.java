package System;
import java.util.*;

import swa.runningeasy.dtos.ListeneintragDTO;
public class Startliste extends Liste {
	/*
	 * Liste der Laeufer die eine Nummer haben.
	 */
	private Map<Laeufer,Integer> laueferMitNummer = new HashMap<Laeufer,Integer>();
	

	public void hinzufuegen(Laeufer lauefer) throws Exception{
		throw new Exception("Jeder Laeufer sollte eine Nummer Besitzen");
	}
	/*
	 * Laeufer mit seiner Startnummer hinzufügen
	 */
	public void hinzufuegen(Laeufer lauefer,Integer startNummer) throws Exception{
		try{
			super.hinzufuegen(lauefer);
			this.laueferMitNummer.put(lauefer, startNummer);
		}catch(Exception e){
			throw e;
		}
	}
	//getter
	public int getStartNummer(Laeufer lauefer){
		return this.laueferMitNummer.get(lauefer);
	}
	@Override
	protected ListeneintragDTO generateEintrag(Laeufer laeufer){
		return new ListeneintragDTO(
				laeufer.getName(),
				laeufer.getVorname(),
				laeufer.getGeburtsjahr(),
				laeufer.getGeschlecht(),
				laeufer.getVereinszugehoerigkeit() != null ? laeufer.getVereinszugehoerigkeit().getName() : null,
				this.getStartNummer(laeufer),
				0,
				null
				);
	}
	
}
