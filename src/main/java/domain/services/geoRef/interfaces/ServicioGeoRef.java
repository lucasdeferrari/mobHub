package domain.services.geoRef.interfaces;

import domain.services.geoRef.entidades.ListadoDeDepartamentos;
import domain.services.geoRef.entidades.ListadoDeMunicipios;
import domain.services.geoRef.entidades.ListadoDeProvincias;
import domain.services.geoRef.entidades.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoRef {
  private static ServicioGeoRef instancia = null;
  private static int maximaCantidadRegistrosDefault = 200;
  private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
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

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
    Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }

  public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
    return responseListadoDeDepartamentos.body();
  }
}



