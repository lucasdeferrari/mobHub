package domain.generadorRankings.estrategiasExportacion;

import domain.generadorRankings.exportables.Exportable;

public interface EstrategiaDeExportacion {
    public String exportar(Exportable exportable);
}
