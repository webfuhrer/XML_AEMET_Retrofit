import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PeticionXML {
	
	public static void pedirProvincias(ActualizacionDatos clase_llamante)
	{
		
		Retrofit retrofit = new Retrofit.Builder()
			    .baseUrl("http://ovc.catastro.meh.es/ovcservweb/ovcswlocalizacionrc/")
			    .addConverterFactory(SimpleXmlConverterFactory.create())
			    .build();

			ServicioPedirDatos service = retrofit.create(ServicioPedirDatos.class);
			
			Call<ClaseProvinciero> llamada=service.pedirProvincias();
			llamada.enqueue(new Callback<ClaseProvinciero>() {
				
				@Override
				public void onResponse(Call<ClaseProvinciero> call, Response<ClaseProvinciero> response) {
					//System.out.println(response.body());
					HashMap<String, String> mapa_provincias=new HashMap();
					
					ClaseProvinciero p=response.body();
					List<Provincia> lista_provincias=p.getProvinciero();
					for(Provincia provincia: lista_provincias)
					{
						mapa_provincias.put(provincia.getCpine(), provincia.getNp());
					}
					//ClasePrincipal.mostrarDatos(p.getProvinciero());
					clase_llamante.mostrarProvincias(mapa_provincias);
				}
				
				@Override
				public void onFailure(Call<ClaseProvinciero> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: "+t.getLocalizedMessage());
					
				}
			});
	}
	public static void pedirMunicipios(String nombre_provincia, ActualizacionDatos llamante)
	{
		Retrofit retrofit = new Retrofit.Builder()
			    .baseUrl("http://ovc.catastro.meh.es/ovcservweb/ovcswlocalizacionrc/")
			    .addConverterFactory(SimpleXmlConverterFactory.create())
			    .build();

			ServicioPedirDatos service = retrofit.create(ServicioPedirDatos.class);
			Call<ClaseMunicipiero> llamada_municipios=service.pedirMunicipios(nombre_provincia, "");
			llamada_municipios.enqueue(new Callback<ClaseMunicipiero>() {

				@Override
				public void onResponse(Call<ClaseMunicipiero> call, Response<ClaseMunicipiero> response) {
					// TODO Auto-generated method stub
					System.out.println(response.body());
					llamante.mostrarMunicipios(response.body());
				}

				@Override
				public void onFailure(Call<ClaseMunicipiero> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: "+t.getLocalizedMessage());
				}
			});
	}
	public static void pedirClima(String codigo_aemet, ActualizacionDatos llamante)
	{
		Retrofit retrofit = new Retrofit.Builder()
			    .baseUrl("http://www.aemet.es/xml/")
			    .addConverterFactory(SimpleXmlConverterFactory.create())
			    .build();

			ServicioPedirDatos service = retrofit.create(ServicioPedirDatos.class);
			Call<ClimaAEMET> llamada_clima=service.pedirClima(codigo_aemet);
			llamada_clima.enqueue(new Callback<ClimaAEMET>() {

				@Override
				public void onResponse(Call<ClimaAEMET> call, Response<ClimaAEMET> response) {
					System.out.println(response.body());
					llamante.mostrarClima(response.body());
					
				}

				@Override
				public void onFailure(Call<ClimaAEMET> call, Throwable t) {
					System.out.println("ERROR: "+t.getLocalizedMessage());
				}
			});
	}
	public interface ActualizacionDatos
	{
		public void mostrarProvincias(Map<String,String> lisat_provincias);
		public void mostrarMunicipios(ClaseMunicipiero m);
		public void mostrarClima(ClimaAEMET clima);
	}
}
