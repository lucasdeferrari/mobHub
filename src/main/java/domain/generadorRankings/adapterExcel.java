package domain.generadorRankings;


import domain.servicios.Entidad;

import java.util.List;

public class adapterExcel implements ExportadorInforme {

    private FormatoExcel adapter;
    public void exportarInforme(List<Entidad> listaRanking){
        this.adapter.exportarInforme(listaRanking);
    }
}




