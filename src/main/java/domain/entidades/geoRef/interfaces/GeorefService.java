package domain.entidades.geoRef.interfaces;
import domain.entidades.geoRef.entidades.ListadoDeLocalidades;
import domain.entidades.geoRef.entidades.ListadoDeProvincias;
import domain.entidades.geoRef.entidades.ListadoDeMunicipios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  GeorefService {
//    @GET("provincias")
//    Call<ListadoDeProvincias> provincias();

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

    @GET("departamentos")
    Call<ListadoDeLocalidades> departamentos(@Query("provincia") int idProvincia);

    @GET("departamentos")
    Call<ListadoDeLocalidades> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos );

    @GET("departamentos")
    Call<ListadoDeLocalidades> departamentos(@Query("provincia") int idProvincia, @Query("max") int max, @Query("campos") String campos );

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("max") int max, @Query("campos") String campos);

}


