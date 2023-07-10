package domain.notificaciones.medioDeNotificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.notificaciones.notificacion.Notificacion;
import domain.notificaciones.notificacion.NotificacionApertura;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface MedioDeNotificacion {

    public void notificar(Notificacion notificacion, Miembro miembro) throws EmailException;

}
