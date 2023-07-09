package domain.notificaciones.medioDeNotificaciones;

import domain.notificaciones.tipoDeNotificacion.Notificacion;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface MedioDeNotificacion {
    public void notificar(List<Notificacion> notificaciones) throws EmailException;

}
