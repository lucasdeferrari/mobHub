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
        if(ranking == "MayorCantidadReportes") {
            MayorCantidadReportes rankingConvertido = new MayorCantidadReportes();
            return rankingConvertido;
        }
        else if (ranking == "MayorGradoImpacto") {
            MayorGradoImpacto rankingConvertido = new MayorGradoImpacto();
            return rankingConvertido;
        }

        else if (ranking == "MayorPromedioCierre") {
            MayorPromedioCierre rankingConvertido = new MayorPromedioCierre();
            return rankingConvertido;
        }

        else return null;
    }
}