package domain.Persistencia;

import domain.comunidad.Miembro;
import domain.notificaciones.formaDeNotificacion.AlertarCuandoSucede;
import domain.notificaciones.formaDeNotificacion.AlertarSinApuro;
import domain.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.notificaciones.medioDeNotificaciones.AdapterEmail;
import domain.notificaciones.medioDeNotificaciones.AdapterWhatsApp;
import domain.notificaciones.medioDeNotificaciones.MedioNotificacion;
import domain.notificaciones.notificacion.Notificacion;
import org.apache.commons.mail.EmailException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter(autoApply = true)
public class FormaNotificacionConverter implements AttributeConverter<FormaNotificacion, String> {
    @Override
    public String convertToDatabaseColumn(FormaNotificacion formaNotificacion) {
        String nombreFormaNotifiacion = (formaNotificacion.getClass().getName()) == "AlertarCuandoSucede" ? "Cuando sucede" : "Sin apuro";

        return formaNotificacion == null? null : nombreFormaNotifiacion;
    }

    @Override
    public FormaNotificacion convertToEntityAttribute(String formaNotificacion) {
        FormaNotificacion formaNotificacion2 = null;

        if(formaNotificacion == "Cuando sucede") {
            formaNotificacion2 = new AlertarCuandoSucede();
        }
        else if (formaNotificacion == "Sin apuro") {
            formaNotificacion2 = new AlertarSinApuro();
        }

        else
            formaNotificacion2 = null;

        return formaNotificacion2;
    }
}