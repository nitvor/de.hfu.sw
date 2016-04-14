package System;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.AnmeldungDTO;
import swa.runningeasy.dtos.LaeuferDTO;
import swa.runningeasy.dtos.LaufzeitDTO;
import swa.runningeasy.dtos.ListeneintragDTO;
import swa.runningeasy.dtos.VeranstaltungDTO;
import swa.runningeasy.dtos.VereinDTO;
import swa.runningeasy.services.Auswertung;
import swa.runningeasy.services.RunningServices;
/*
 * TODO: Verbindung zwischen Verien und Laeufer ?
 */
public class Service implements RunningServices {
	private static Logger log = LogManager.getRootLogger();
	/*
	 * Liste der Veranstaltungen
	 */
	private ArrayList<Veranstaltung> veranstaltungen = new ArrayList<Veranstaltung>();
	/*
	 * Liste der Vereine
	 */
	private ArrayList<Verein> vereine = new ArrayList<Verein>();
	/*
	 * Liste der Laeufer
	 */
	private ArrayList<Laeufer> laeufer = new ArrayList<Laeufer>();
	
	
	public void erzeugeVeranstaltung(VeranstaltungDTO v) {
		Veranstaltung veranstaltung = new Veranstaltung(v.getName(),v.getDistanz(),v.getStartgebuehr(), v.getDatum(),v.getAnmeldeschluss());
		veranstaltungen.add(veranstaltung);
	}
	
	public void erzeugeVerein(VereinDTO v) {
		Verein verein = new Verein(v.getName());
		vereine.add(verein);
	}
	
	public void erzeugeAnmeldung(AnmeldungDTO a) throws IllegalArgumentException {
		try{
			Laeufer laeufer = this.suchLaeufer(a.getLaeufer().getVorname(), a.getLaeufer().getName());
			Veranstaltung veranstaltung = this.suchVeranstaltung(a.getVeranstaltung());
			Anmeldung an = veranstaltung.Anmelden(laeufer);
			if(a.isBezahlt()){
				an.anmeldungGezahlt(a.getStartnummer());
			}
			if(a.getVerein() != null && !a.getVerein().equalsIgnoreCase("")){
				an.setVerein(this.suchVerein(a.getVerein()));
			}
		}catch(Exception e){
			log.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void erzeugeLaeufer(LaeuferDTO a) {
		this.laeufer.add(
				new Laeufer(
					a.getName(),
					a.getVorname(),
					a.getGeburtsjahr(),
					a.getGeschlecht(),
					a.getEmail(),
					a.getSms(),
					a.getStrasse(),
					a.getPlz(),
					a.getOrt(),
					a.getLand(),
					null
				)
			);
	}
	
	public void erzeugeLaufzeit(LaufzeitDTO l) throws IllegalArgumentException {
		try{
			Veranstaltung ver = this.suchVeranstaltung(l.getVeranstaltung());
			Laeufer leuf = ver.getLaeuferByStartNummer(l.getStartnummer());
			ver.getErgebnisListe().hinzufuegen(leuf, 
					new Ergebnis(leuf,ver,l.getLaufzeit(),ver.getDistanz()));
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	public List<VeranstaltungDTO> getVeranstaltungen() {
		List<VeranstaltungDTO> res = new ArrayList<VeranstaltungDTO>();
		for(Veranstaltung v : this.veranstaltungen){
			res.add(v.generateDTO());
		}
		return res;
	}
	
	public List<VereinDTO> getVereine() {
		List<VereinDTO> res = new ArrayList<VereinDTO>();
		for(Verein v : this.vereine){
			res.add(v.generateDTO());
		}
		return res;
	}
	
	public List<LaeuferDTO> getLaeufer() {
		List<LaeuferDTO> res = new ArrayList<LaeuferDTO>();
		for(Laeufer v : this.laeufer){
			res.add(v.generateDTO());
		}
		return res;
	}
	
	public List<AnmeldungDTO> getAnmeldungen(String veranstaltung) {
		List<AnmeldungDTO> res = new ArrayList<AnmeldungDTO>();
		try{
			res = this.suchVeranstaltung(veranstaltung).getAnmeldungenDTO();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return res;
	}
	
	public List<LaufzeitDTO> getLaufzeiten(String Veranstaltung) {
		List<LaufzeitDTO> res = null;
		try{
			res = this.suchVeranstaltung(Veranstaltung).getErgebnisListe().generateErgebnissListeDTO();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return res;
	}
	
	public List<ListeneintragDTO> getAuswertung(Auswertung a, String Veranstaltung) {
		List<ListeneintragDTO> res = null;
		try{
			Veranstaltung ver = this.suchVeranstaltung(Veranstaltung);
			ver.generateStartList();
			if(a == Auswertung.STARTLISTE){
				res = ver.getStartliste().generateDTO();
			}else if(a == Auswertung.NICHTSTARTER){
				res =
					ver.getAlleGemeldetenLaeufer().marge(ver.getStartliste())
					.generateDTO();
			}else if(a == Auswertung.GESAMTERGEBNISLISTE){
				res = ver.getErgebnisListe().generateDTO();
			}else if(a == Auswertung.ABBRECHER){
				res =
					ver.getStartliste().marge(ver.getErgebnisListe())
					.generateDTO();
			}
			
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return res;
	}
	
	public void init() {
		veranstaltungen = new ArrayList<Veranstaltung>();
		vereine = new ArrayList<Verein>();
		laeufer = new ArrayList<Laeufer>();
		
	}
	/*
	 * Damit man nach einer Veranstaltung suchen kann.
	 */
	private Veranstaltung suchVeranstaltung(String name) throws Exception{
		Veranstaltung res = null;
		for(Veranstaltung v : this.veranstaltungen){
			if(v.getName().equalsIgnoreCase(name)){
				res = v;
				break;
			}
		}
		if(res == null){
			throw new Exception("Veranstaltung "+name+" nicht gefunden");
		}
		return res;
	}
	
	private Verein suchVerein(String name) throws Exception{
		Verein res = null;
		for(Verein v : this.vereine){
			if(v.getName().equalsIgnoreCase(name)){
				res = v;
				break;
			}
		}
		if(res == null){
			throw new Exception("Verein "+name+" nicht gefunden");
		}
		return res;
	}
	
	/*
	 * Damit man nach einem Laeufer suchen kann.
	 */
	private Laeufer suchLaeufer(String vorname,String nachname) throws Exception{
		Laeufer res = null;
		for(Laeufer l : this.laeufer){
			if(l.getVorname().equalsIgnoreCase(vorname) 
				&& l.getName().equalsIgnoreCase(nachname)){
				res = l;
				break;
			}
		}
		if(res == null){
			throw new Exception("Laeufer "+nachname+" nicht gefunden");
		}
		return res;
	}
}
