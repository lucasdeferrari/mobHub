package domain.Persistencia;

import domain.entidades.Rankings.MayorCantidadReportes;
import domain.entidades.Rankings.MayorGradoImpacto;
import domain.entidades.Rankings.MayorPromedioCierre;
import domain.entidades.Rankings.Ranking;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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