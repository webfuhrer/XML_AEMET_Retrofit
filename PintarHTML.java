import java.util.List;

public class PintarHTML {

	public static String crearTabla(ClimaAEMET clima) {
		List<Dia> lista=clima.getPrediccion();
		String html="<table><tr><th>Fecha</th><th>Minima</th><th>Maxima</th></tr>";
		for (Dia d: lista)
		{
			html+="<tr><td>"+d.getFecha()+"</td><td>"+d.getTemperatura().getMinima()+"</td>"+
					"<td>"+d.getTemperatura().getMaxima()+"</td></tr>";
		}
		html+="</table>";
		html="<html><head></head><body>"+html+"</body></html>";
		return html;
	}

}
