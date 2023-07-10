package domain.generadorRankings;

import domain.servicios.Entidad;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface ExportadorInforme {

   public void exportarInforme(List<Entidad> listaRanking);
}
