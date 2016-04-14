package System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import swa.runningeasy.dtos.LaufzeitDTO;

public class Ergebnisliste extends Liste {

	private Map<Laeufer,Ergebnis> ergebnisse = new HashMap<Laeufer,Ergebnis>();
	
	
	public void hinzufuegen(Laeufer lauefer) throws Exception{
		throw new Exception("Jeder Laeufer sollte Ergebnis Besitzen");
	}
	/*
	 * Laeufer mit seinem Ergebnis der Liste hinzufuegen.
	 */
	public void hinzufuegen(Laeufer lauefer,Ergebnis erg) throws Exception{
		try{
			super.hinzufuegen(lauefer);
			this.ergebnisse.put(lauefer, erg);
			this.Sort();
		}catch(Exception e){
			throw e;
		}
	}
	/*
	 * Um die Ergebnisse zu Sortieren.
	 */
	private void Sort(){
		SortedSet<Ergebnis> values = new TreeSet<Ergebnis>(this.ergebnisse.values());
		this.ergebnisse.clear();
		for(Ergebnis erg :values){
			this.ergebnisse.put(erg.laeufer, erg);
		}
	}
	
	//getter
	public Ergebnis getErgebniss(Laeufer lauefer){
		return this.ergebnisse.get(lauefer);
	}
	
	public List<LaufzeitDTO> generateErgebnissListeDTO(){
		List<LaufzeitDTO> res = new ArrayList<LaufzeitDTO>();
		for(Ergebnis erg : this.ergebnisse.values()){
			res.add(erg.generateDTO());
		}
		return res;
	}
}
