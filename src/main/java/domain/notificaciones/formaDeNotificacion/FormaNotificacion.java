package domain.notificaciones.formaDeNotificacion;

import domain.notificaciones.tipoDeNotificacion.TipoNotificacion;
import domain.servicios.Incidente;

public interface FormaNotificacion {
    public void notificar(TipoNotificacion unaNotificacion);

}