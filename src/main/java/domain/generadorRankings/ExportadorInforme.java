package domain.generadorRankings;

import domain.servicios.Entidad;

import java.util.List;

public interface ExportadorInforme {

   public void exportarInforme(List<Entidad> listaRanking);
}
