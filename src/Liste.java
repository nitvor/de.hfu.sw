import java.util.*;

import swa.runningeasy.dtos.ListeneintragDTO;
public class Liste {
	
	private ArrayList<Laeufer> laefers = new ArrayList<Laeufer>();
	
	public Laeufer[] getLaefers() {
		return (Laeufer[])laefers.toArray();
	}

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
		}else{
			throw new Exception("Laufer bereits in der Liste");
		}
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
}
