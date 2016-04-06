import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import swa.runningeasy.dtos.VereinDTO;

public class Verein {
	static Logger log = LogManager.getRootLogger();
	
	private String name;
	ArrayList<Laeufer> mitglieder;
	
	public Verein(String name) {
		this.name = name;
	}
	
	public void mitgliedHinzufuegen(Laeufer laeufer) throws Exception {
		if(laeufer.getVereinszugehoerigkeit() != null){
			this.mitglieder.add(laeufer);
			laeufer.setVereinszugehoerigkeit(this);
		}else{
			throw new Exception("Laefer gehoert bereits einem Verein an");
		}
	}
	
	public void mitgliedEntfernen(Laeufer laeufer)  throws Exception {
		boolean isIn = false;
		for(Laeufer mitglied : this.mitglieder){
			if(mitglied == laeufer){
				isIn = true;
				break;
			}
		}
		if(!isIn){
			this.mitglieder.remove(laeufer);
			laeufer.setVereinszugehoerigkeit(null);
		}else{
			throw new Exception("Laefer nicht in dem Verein");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public VereinDTO generateDTO(){
		return new VereinDTO(this.name);
	}
}
