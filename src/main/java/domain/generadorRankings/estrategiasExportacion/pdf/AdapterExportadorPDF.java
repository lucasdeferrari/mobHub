package domain.generadorRankings.estrategiasExportacion.pdf;

import domain.generadorRankings.exportables.Exportable;

public interface AdapterExportadorPDF {
    public String exportar(Exportable exportable);
}