import java.util.Date;

import System.*;
import swa.runningeasy.dtos.AnmeldungDTO;
import swa.runningeasy.dtos.LaeuferDTO;
import swa.runningeasy.dtos.LaufzeitDTO;
import swa.runningeasy.dtos.VeranstaltungDTO;
import swa.runningeasy.dtos.VereinDTO;
import swa.runningeasy.services.RunningServices;

public class Main {
	
	static LaeuferDTO laeuferEins = new LaeuferDTO("Schmidt","Hans",1980,'M',"hans.schmidt@mail.com","0123456789","Kapele 2","98755","Berlin","Deutschland");
	static LaeuferDTO laeuferZwei = new LaeuferDTO("Mueller","Fritz",1985,'M',"firtz.mueller@mail.com","0123456789","Birke 4","98755","Berlin","Deutschland");
	
	static VeranstaltungDTO veranstaltung = new VeranstaltungDTO("Freiburg Marathon",new Date() ,new Date() ,20,42.5f);
	
	static AnmeldungDTO anmeldnung = new AnmeldungDTO(laeuferEins,true,veranstaltung.getName(),"",1);
	
	static VereinDTO verein = new VereinDTO("Glueck");
	
	static LaufzeitDTO laufzeit = new LaufzeitDTO(1, new Date(),veranstaltung.getName());
	
	
	public static String toString(LaeuferDTO l){
		String resutlt = "Name="+ l.getName()+ "," + "Vorname=" + l.getVorname() + "," + "Geburtsjahr=" + l.getGeburtsjahr() + "," + "Geschlecht=" + l.getGeschlecht() + "," + "Email=" + l.getEmail()  + "," + "SMS=" + l.getSms() + "," + "Strasse=" + l.getStrasse() + "," + "PLZ=" + l.getPlz() + "," + "Ort=" + l.getOrt() + "," + "Land=" + l.getLand();  
		return resutlt;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunningServices service = new Service();
		
		service.erzeugeLaeufer(laeuferEins);
		for(LaeuferDTO l : service.getLaeufer()){
			System.out.println(toString(l));
		}
				
		service.erzeugeVeranstaltung(veranstaltung);
		System.out.println("/////");
		for(VeranstaltungDTO v : service.getVeranstaltungen()){
			System.out.println(v.toString());
		}
		
		service.erzeugeVerein(verein);
		System.out.println("/////");
		for(VereinDTO a : service.getVereine()){
			System.out.println(a.toString());
		}
		
		service.erzeugeAnmeldung(anmeldnung);
		System.out.println("/////");
		for(AnmeldungDTO a : service.getAnmeldungen(veranstaltung.getName())){
			System.out.println(a.toString());
		}
		
	}

}
