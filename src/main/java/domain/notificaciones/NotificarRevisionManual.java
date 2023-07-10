package domain.notificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.NotificacionRevision;
import domain.servicios.Incidente;

import java.util.List;

public class NotificarRevisionManual implements Notificador {

    public static void notificar(Incidente unIncidente, List<Miembro> unosMiembros) {

        NotificacionRevision notificacion = new NotificacionRevision(unIncidente);

        unosMiembros.forEach(unMiembro -> unMiembro.getFormaNotificacion().notificar(notificacion, unMiembro));

    }


}
