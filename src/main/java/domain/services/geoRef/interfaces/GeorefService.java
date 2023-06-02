package domain.services.geoRef.interfaces;
import domain.services.geoRef.entidades.ListadoDeDepartamentos;
import domain.services.geoRef.entidades.ListadoDeProvincias;
import domain.services.geoRef.entidades.ListadoDeMunicipios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
//    @GET("provincias")
//    Call<ListadoDeProvincias> provincias();

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") int idProvincia,@Query("campos") String campos );

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("departamento") int idDepartamento, @Query("campos") String campos);


}
