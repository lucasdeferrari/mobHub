package domain.notificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.NotificacionApertura;
import domain.servicios.Incidente;

import java.util.List;


public class NotificarAperturaAMiembros implements Notificador {

    public void notificar(Incidente unIncidente, List<Miembro> unosMiembros) {

        NotificacionApertura notificacion = new NotificacionApertura(unIncidente);

        unosMiembros.forEach(unMiembro -> unMiembro.getFormaNotificacion().notificar(notificacion, unMiembro));


    }
}
