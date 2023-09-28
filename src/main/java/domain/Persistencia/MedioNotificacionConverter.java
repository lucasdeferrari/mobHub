package domain.Persistencia;

import domain.entidades.notificaciones.medioDeNotificaciones.AdapterEmail;
import domain.entidades.notificaciones.medioDeNotificaciones.AdapterWhatsApp;
import domain.entidades.notificaciones.medioDeNotificaciones.MedioNotificacion;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MedioNotificacionConverter implements AttributeConverter<MedioNotificacion, String> {
    @Override
    public String convertToDatabaseColumn(MedioNotificacion medioNotificacion) {
        String nombreMedioNotifiacion = (medioNotificacion.getClass().getName()) == "AdapterWhatsApp" ? "Whatsapp" : "Email";

        return medioNotificacion == null? null : nombreMedioNotifiacion;
    }

    @Override
    public MedioNotificacion convertToEntityAttribute(String medioNotificacion) {
        MedioNotificacion medioNotificacion1 = null;

        if(medioNotificacion == "Whatsapp") {
            medioNotificacion1 = new AdapterWhatsApp();
        }
        else if (medioNotificacion == "Email") {
            medioNotificacion1 = new AdapterEmail();
        }

        else
            medioNotificacion1 = null;

        return medioNotificacion1;
    }
}