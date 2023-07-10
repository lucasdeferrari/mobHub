package domain.notificaciones.formaDeNotificacion;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;
import org.apache.commons.mail.EmailException;

public class AlertarCuandoSucede implements FormaNotificacion {

    public void notificar(Notificacion unaNotificacion, Miembro miembro) {
        try {
            miembro.getMedioDeNotificacion().notificar(unaNotificacion, miembro);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }


}
