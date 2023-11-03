package componentesExternos.geoRef.interfaces;

import componentesExternos.geoRef.entidades.ListadoDeLocalidades;
import componentesExternos.geoRef.entidades.ListadoDeMunicipios;
import componentesExternos.geoRef.entidades.ListadoDeProvincias;
import componentesExternos.geoRef.entidades.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoRef{
  private static ServicioGeoRef instancia = null;
  private int maximaCantidadRegistrosDefault = 200;
  private String urlApi = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  private ServicioGeoRef() {
    this.retrofit = new Retrofit.Builder()
            .baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public static ServicioGeoRef instancia(){
    if(instancia== null){
      instancia = new ServicioGeoRef();
    }
    return instancia;
  }

  // ---------- PROVINCIAS -----------------

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias("id, nombre");
    Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();


  }

  // ---------- MUNICIPIOS -----------------


  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int provincia_id) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia_id, maximaCantidadRegistrosDefault,"id, nombre");
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }



  public ListadoDeLocalidades listadoDeLocalidadesDeMunicipio(int municipio_id) throws IOException { //todo ver porque en teoria las localidades tienen el mismo nombre que los municipios
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeLocalidades> requestListadoDeLocalidades = georefService.localidades(municipio_id, maximaCantidadRegistrosDefault);
    Response<ListadoDeLocalidades> responseListadoDeLocalidades = requestListadoDeLocalidades.execute();
    return responseListadoDeLocalidades.body();
  }

}



