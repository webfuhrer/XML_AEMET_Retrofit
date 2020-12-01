
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicioPedirDatos {
@GET("ovccallejero.asmx/ConsultaProvincia")
Call<ClaseProvinciero> pedirProvincias();
@GET("ovccallejero.asmx/ConsultaMunicipio")
Call<ClaseMunicipiero> pedirMunicipios(@Query("Provincia") String provincia,@Query("Municipio") String municipio);
//http://www.aemet.es/xml/municipios/localidad_28079.xml
@GET("municipios/localidad_{codigo}.xml")
Call<ClimaAEMET> pedirClima(@Path("codigo") String codigo);
}
