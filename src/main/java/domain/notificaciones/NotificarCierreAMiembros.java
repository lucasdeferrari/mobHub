package domain.notificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.NotificacionCierre;
import domain.servicios.Incidente;

import java.util.List;

public class NotificarCierreAMiembros implements Notificador{


    public static void notificar(Incidente unIncidente, List<Miembro> unosMiembros) {

        NotificacionCierre notificacion = new NotificacionCierre(unIncidente);

        unosMiembros.forEach(unMiembro -> unMiembro.getFormaNotificacion().notificar(notificacion, unMiembro));

    }
}
