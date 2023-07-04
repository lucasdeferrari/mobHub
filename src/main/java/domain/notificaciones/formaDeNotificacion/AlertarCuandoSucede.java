package domain.notificaciones.formaDeNotificacion;

import domain.notificaciones.medioDeNotificaciones.MedioDeNotificacion;
import domain.notificaciones.tipoDeNotificacion.TipoNotificacion;
import domain.servicios.Incidente;
import org.apache.commons.mail.EmailException;

import java.util.ArrayList;
import java.util.List;

public class AlertarCuandoSucede implements FormaNotificacion {

    private MedioDeNotificacion receptor;

    public void notificar(TipoNotificacion unaNotificacion) {
        List<TipoNotificacion> notificacionesAEnviar = new ArrayList<>();
        notificacionesAEnviar.add(unaNotificacion);
        try {
            receptor.notificar(notificacionesAEnviar);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }


}
