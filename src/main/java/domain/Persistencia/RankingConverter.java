package domain.Persistencia;

import domain.Rankings.MayorCantidadReportes;
import domain.Rankings.MayorGradoImpacto;
import domain.Rankings.MayorPromedioCierre;
import domain.Rankings.Ranking;
import domain.comunidad.Miembro;
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
public class RankingConverter implements AttributeConverter<Ranking, String> {
    @Override
    public String convertToDatabaseColumn(Ranking ranking) {
        return ranking.getClass().getName();
    }

    @Override
    public Ranking convertToEntityAttribute(String ranking) {
        Ranking ranking1 = null;

        if(ranking == "MayorCantidadReportes") {
            ranking1 = new MayorCantidadReportes();
        }
        else if (ranking == "MayorGradoImpacto") {
            ranking1 = new MayorGradoImpacto();
        }

        else if (ranking == "MayorPromedioCierre") {
            ranking1 = new MayorPromedioCierre();
        }

        else
            ranking1 = null;

        return ranking1;
    }
}