package domain.services.geoRef.interfaces;

import domain.services.geoRef.entidades.*;
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

  // ---------- PROVINCIAS -----------------

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias("id, nombre");
    Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();


  }

  // ---------- MUNICIPIOS -----------------

  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id);
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }

  public ListadoDeMunicipios listadoDeMunicipiosDeProvinciaSegunCampos(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre");
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }

  public ListadoDeMunicipios listadoDeMunicipiosDeProvinciaSegunCamposConMaximo(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, maximaCantidadRegistrosDefault,"id, nombre");
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }



  // ---------- DEPARTAMENTOS -----------------

  public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id);
    Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
    return responseListadoDeDepartamentos.body();
  }

  public ListadoDeDepartamentos listadoDeDepartamentosDeProvinciaSegunCamposConMaximo(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id, maximaCantidadRegistrosDefault , "id, nombre");
    Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
    return responseListadoDeDepartamentos.body();
  }

}



