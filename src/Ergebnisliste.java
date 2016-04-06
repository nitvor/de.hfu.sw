import java.util.HashMap;

import java.util.Map;

public class Ergebnisliste extends Liste {

	private Map<Laeufer,Ergebnis> ergebnisse = new HashMap<Laeufer,Ergebnis>();
	

	public void hinzufuegen(Laeufer lauefer) throws Exception{
		throw new Exception("Jeder Laeufer sollte Ergebniss Besitzen");
	}
	
	public void hinzufuegen(Laeufer lauefer,Ergebnis erg) throws Exception{
		try{
			super.hinzufuegen(lauefer);
			this.ergebnisse.put(lauefer, erg);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void Sort(){
		//TODO
	}
	
	public Ergebnis getErgebniss(Laeufer lauefer){
		return this.ergebnisse.get(lauefer);
	}
}
