package domain.notificaciones;

import domain.comunidad.Miembro;
import domain.comunidad.RolComunidad;
import domain.comunidad.RolServicio;
import domain.notificaciones.notificacion.NotificacionApertura;
import domain.servicios.Incidente;

import java.util.Map;


public class NotificarAperturaAMiembros implements Notificador {

    public void notificar(Incidente unIncidente, Map<Miembro, RolComunidad> unosMiembros) {

        NotificacionApertura notificacion = new NotificacionApertura(unIncidente);

        unosMiembros.forEach((unMiembro, unRol) -> unMiembro.getFormaNotificacion().notificar(notificacion, unMiembro));


    }
}
