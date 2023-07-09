package domain.notificaciones.formaDeNotificacion;

import domain.notificaciones.medioDeNotificaciones.MedioDeNotificacion;
import domain.notificaciones.tipoDeNotificacion.Notificacion;
import org.apache.commons.mail.EmailException;

import java.util.ArrayList;
import java.util.List;

public class AlertarCuandoSucede implements FormaNotificacion {

    private MedioDeNotificacion receptor;

    public void notificar(Notificacion unaNotificacion) {
        List<Notificacion> notificacionesAEnviar = new ArrayList<>();
        notificacionesAEnviar.add(unaNotificacion);
        try {
            receptor.notificar(notificacionesAEnviar);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }


}
