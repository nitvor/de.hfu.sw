import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Verein {
	static Logger log = LogManager.getRootLogger();
	private String name;
	List<Laeufer> mitglied;
	
	public Verein(String name) {
		this.name = name;
	}
	
	public void mitgliedHinzufuegen(Laeufer laeufer) {
		
	}
	
	public void mitgliedEntfernen(Laeufer laeufer) {
		
	}
	
	public String getName() {
		return name;
	}
}
