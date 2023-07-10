package domain.notificaciones.medioDeNotificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;
import org.apache.commons.mail.EmailException;

public interface MedioNotificacion {

    public void notificar(Notificacion notificacion, Miembro miembro) throws EmailException;


}
