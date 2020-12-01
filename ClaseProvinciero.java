import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/*El nombre de la clase da igual, siempre que le ponga la anotación @Root con el nombre real*/
@Root(name="consulta_provinciero", strict=false)
public class ClaseProvinciero {

	@ElementList
	private List<Provincia> provinciero;
	
	
	public List<Provincia> getProvinciero() {
		return provinciero;
	}


	public void setProvinciero(List<Provincia> provinciero) {
		this.provinciero = provinciero;
	}


	@Override
	public String toString() {
		return "ClaseProvinciero [provinciero=" + provinciero + "]";
	}
	
	
}
