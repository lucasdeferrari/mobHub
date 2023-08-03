package domain.geolocalizacion;

import domain.comunidad.Miembro;
import domain.notificaciones.NotificarRevisionManual;
import domain.servicios.Incidente;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
public class GestorGeolocalizacion {

   private static List<Incidente> incidentesCerca;

   public static void incidentesCercaDelMiembro(Miembro miembro, List<Incidente> incidentes) {

      double latitudMiembro = miembro.getUbicacionActual().getLatitud();
      double longitudMiembro = miembro.getUbicacionActual().getLongitud();


      incidentes.forEach(incidente -> {
         double latitudEstablecimiento = incidente.getEstablecimiento().getUbicacion().getLatitud();
         double longitudEstablecimiento = incidente.getEstablecimiento().getUbicacion().getLongitud();

         if ( Math.abs(latitudMiembro - latitudEstablecimiento) <= margen && Math.abs(longitudMiembro - longitudEstablecimiento) <= margen) {
            incidentesCerca.add(incidente);
         }
      });

      // ELIMINO ELEMENTOS REPETIDOS
      Set<Incidente> conjunto = new HashSet<>(incidentesCerca);
      List<Incidente> incidentesSinRepetidos = new ArrayList<>(conjunto);

      // VACIO LA LISTA DE INCIDENTES PARA QUE ARRANQUE VACIA LA PROXIMA
      incidentesCerca.clear();

      notificarRevisionManual(miembro, incidentesSinRepetidos);
   }

   public void notificarRevisionManual(Miembro miembro, List<Incidente> incidentes) {

      List<Miembro> miembros = new ArrayList<>();
      miembros.add(miembro);

      incidentes.forEach(unIncidente -> NotificarRevisionManual.notificar(unIncidente, miembros));

      // VACIO LA LISTA PARA QUE NO QUEDE EL MIEMBRO ANTERIOR
      miembros.clear();
   }
}
