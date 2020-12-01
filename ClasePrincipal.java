import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ClasePrincipal implements PeticionXML.ActualizacionDatos{
	String codigo_provincia="";
	public static void main(String[] args) {
		ClasePrincipal p=new ClasePrincipal();
		p.iniciarAplicacion();
	}
	public void iniciarAplicacion()
	{
		Map<String, String> mapa_provincias=AccesoBD.recuperarProvincias();
		if(mapa_provincias.isEmpty())
		{
			PeticionXML.pedirProvincias(this);
		}
		else
		{
			codigo_provincia=EntradaSalida.pedirProvincia(mapa_provincias);
			ArrayList<Municipio> lista_municipios=AccesoBD.recuperarMunicipios(codigo_provincia);
			if ( lista_municipios.isEmpty())
			{
			PeticionXML.pedirMunicipios(mapa_provincias.get(codigo_provincia), this);
			}
			else
			{
				ClaseMunicipiero m=new ClaseMunicipiero();
				m.setLista_municipios(lista_municipios);
				String codigo_municipio=EntradaSalida.pedirMunicipio(lista_municipios);
				String codigo_aemet=calcularCodigoAEMET(codigo_municipio);
				PeticionXML.pedirClima(codigo_aemet, this);
			}
		}
		
	}
	
	@Override
	public void mostrarProvincias(Map<String,String> mapa_provincias) {
		// TODO Auto-generated method stub
		//Debo grabarlas en MySQL
		AccesoBD.insertarProvincias(mapa_provincias);
		codigo_provincia=EntradaSalida.pedirProvincia(mapa_provincias);
		PeticionXML.pedirMunicipios(mapa_provincias.get(codigo_provincia), this);
	}
	@Override
	public void mostrarMunicipios(ClaseMunicipiero m) {
		// TODO Auto-generated method stub
		AccesoBD.insertarMunicipios(m, codigo_provincia);
		List<Municipio> lista_municipios=m.getLista_municipios();
		String codigo_municipio=EntradaSalida.pedirMunicipio(lista_municipios);
		String codigo_aemet=calcularCodigoAEMET(codigo_municipio);
		PeticionXML.pedirClima(codigo_aemet, this);
	}
	private String calcularCodigoAEMET(String codigo_municipio) {
		// TODO Auto-generated method stub
		while(codigo_municipio.length()<3)
		{
			codigo_municipio="0"+codigo_municipio;
		}
		return codigo_provincia+codigo_municipio;
	}
	@Override
	public void mostrarClima(ClimaAEMET clima) {
		String html_tabla=PintarHTML.crearTabla(clima);
		System.out.println(html_tabla);
	}
	
}
