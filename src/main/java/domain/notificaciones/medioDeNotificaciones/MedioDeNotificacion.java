package domain.notificaciones.medioDeNotificaciones;

import domain.notificaciones.tipoDeNotificacion.TipoNotificacion;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface MedioDeNotificacion {
    public void notificar(List<TipoNotificacion> notificaciones) throws EmailException;

}
