package domain.geolocalizacion;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.NotificacionApertura;
import domain.notificaciones.notificacion.NotificacionRevision;
import domain.servicios.Incidente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GestorGeolocalizacion {

   private static List<Incidente> incidentesCerca = new ArrayList<>();

   public static void incidentesCercaDelMiembro(Miembro miembro, List<Incidente> incidentes) {

      double latitudMiembro = miembro.getUbicacionActual().getLatitud();
      double longitudMiembro = miembro.getUbicacionActual().getLongitud();

      double margen = 300; // Margen de ±300

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

      GestorGeolocalizacion.notificarMiembro(miembro, incidentesSinRepetidos);
   }

   public static void notificarMiembro(Miembro miembro, List<Incidente> incidentes) {
      incidentes.forEach(unIncidente -> {
         NotificacionRevision notificacion = new NotificacionRevision(unIncidente);
         miembro.getFormaNotificacion().notificar(notificacion, miembro);
      });

   }


}
