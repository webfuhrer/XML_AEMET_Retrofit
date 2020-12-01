import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EntradaSalida {

	public static String pedirProvincia(Map<String, String> mapa_provincias) {
		for (String clave: mapa_provincias.keySet())
		{
			System.out.println(clave+"- "+mapa_provincias.get(clave));
		}
		Scanner sc=new Scanner(System.in);
		String codigo=sc.nextLine();
		return codigo;
	}

	public static String pedirMunicipio(List<Municipio> lista_municipios) {
		for(Municipio m: lista_municipios)
		{
			System.out.println(m.getLoine().getCodigo()+" "+m.getNombre());
		}
		Scanner sc=new Scanner(System.in);
		String codigo=sc.nextLine();
		return codigo;
	}

}
