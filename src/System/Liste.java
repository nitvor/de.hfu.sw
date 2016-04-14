package System;
import java.util.*;

import swa.runningeasy.dtos.ListeneintragDTO;
public class Liste {
	/*
	 * Liste von Laeufern
	 */
	private ArrayList<Laeufer> laefers = new ArrayList<Laeufer>();
	/*
	 * Um den Laeufer zur Liste hinzufügen zu können.
	 */
	public void hinzufuegen(Laeufer lauefer) throws Exception{
		boolean isIn = false;
		for(Laeufer laueferList: this.laefers){
			if(laueferList == lauefer){
				isIn = true;
				break;
			}
		}
		if(!isIn){
			this.laefers.add(lauefer);
		}
		/*else{
			throw new Exception("Laufer bereits in der Liste");
		}*/
	}
	//getter
	public Laeufer[] getLaefers() {
		return (Laeufer[])laefers.toArray();
	}
	
	public List<ListeneintragDTO> generateDTO(){
		List<ListeneintragDTO> res = new ArrayList<ListeneintragDTO>();
		for(Laeufer l : this.laefers){
			res.add(this.generateEintrag(l));
		}
		return res;
	}

	protected ListeneintragDTO generateEintrag(Laeufer laeufer){
		return new ListeneintragDTO(
				laeufer.getName(),
				laeufer.getVorname(),
				laeufer.getGeburtsjahr(),
				laeufer.getGeschlecht(),
				laeufer.getVereinszugehoerigkeit() != null ? laeufer.getVereinszugehoerigkeit().getName() : null,
				0,
				0,
				null
				);
	}
	
	public Liste marge(Liste liste){
		Liste result = new Liste();
		for(Laeufer l: this.laefers){
			if(!liste.laefers.contains(l)){
				try{
				result.hinzufuegen(l);
				}catch(Exception e){
					//TODO
				}
			}
		}
		return result;
	}
	
}
