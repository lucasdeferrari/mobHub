package domain.Persistencia;

import domain.entidades.notificaciones.formaDeNotificacion.AlertarCuandoSucede;
import domain.entidades.notificaciones.formaDeNotificacion.AlertarSinApuro;
import domain.entidades.notificaciones.formaDeNotificacion.FormaNotificacion;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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