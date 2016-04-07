import java.util.List;

import swa.runningeasy.dtos.AnmeldungDTO;
import swa.runningeasy.dtos.LaeuferDTO;
import swa.runningeasy.dtos.LaufzeitDTO;
import swa.runningeasy.dtos.ListeneintragDTO;
import swa.runningeasy.dtos.VeranstaltungDTO;
import swa.runningeasy.dtos.VereinDTO;
import swa.runningeasy.services.Auswertung;
import swa.runningeasy.services.RunningServices;

public class Service implements RunningServices {
	List<Veranstaltung> veranstaltungen;
	List<Verein> vereine;
	@Override
	public void erzeugeVeranstaltung(VeranstaltungDTO v) {
		// TODO Auto-generated method stub
		Veranstaltung veranstaltung = new Veranstaltung(v.getName(),v.getDistanz(), v.getDatum());
		veranstaltungen.add(veranstaltung);
	}
	@Override
	public void erzeugeVerein(VereinDTO v) {
		// TODO Auto-generated method stub
		Verein verein = new Verein(v.getName());
		vereine.add(verein);
	}
	@Override
	public void erzeugeAnmeldung(AnmeldungDTO a) throws IllegalArgumentException {
		// TOO Auto-generated method stub
		
	}
	@Override
	public void erzeugeLaeufer(LaeuferDTO a) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void erzeugeLaufzeit(LaufzeitDTO l) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<VeranstaltungDTO> getVeranstaltungen() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<VereinDTO> getVereine() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LaeuferDTO> getLaeufer() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AnmeldungDTO> getAnmeldungen(String Veranstaltung) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LaufzeitDTO> getLaufzeiten(String Veranstaltung) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ListeneintragDTO> getAuswertung(Auswertung a, String Veranstaltung) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
