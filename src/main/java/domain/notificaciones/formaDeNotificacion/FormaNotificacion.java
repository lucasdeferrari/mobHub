package domain.notificaciones.formaDeNotificacion;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;

public interface FormaNotificacion {
    public void notificar(Notificacion unaNotificacion, Miembro unMiembro);

}