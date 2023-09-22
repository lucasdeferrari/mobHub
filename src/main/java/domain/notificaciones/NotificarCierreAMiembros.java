package domain.notificaciones;

import domain.comunidad.Miembro;
import domain.comunidad.RolComunidad;
import domain.comunidad.RolServicio;
import domain.notificaciones.notificacion.NotificacionCierre;
import domain.servicios.Incidente;

import java.util.Map;

public class NotificarCierreAMiembros implements Notificador{


    public void notificar(Incidente unIncidente, Map<Miembro, RolComunidad> unosMiembros) {

        NotificacionCierre notificacion = new NotificacionCierre(unIncidente);

        unosMiembros.forEach((unMiembro, unRol) -> unMiembro.getFormaNotificacion().notificar(notificacion, unMiembro));

    }
}
