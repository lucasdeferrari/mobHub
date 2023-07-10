package domain.generadorRankings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import domain.servicios.Entidad;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class FormatoExcel implements ExportadorInforme {

    public void exportarInforme(List<Entidad> listaRanking) {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Informe");

        String[] encabezados = {"Posición", "Entidad"};

        // Agregar los encabezados por columnas
        Row filaEncabezados = hoja.createRow(0);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = filaEncabezados.createCell(i);
            celda.setCellValue(encabezado);
        }

        // Agregar los datos de las entidades
        for (int i = 0; i < listaRanking.size(); i++) {
            Entidad entidad = listaRanking.get(i);
            Row filaDatos = hoja.createRow(i + 1);
            Cell celdaPosicion = filaDatos.createCell(0);
            celdaPosicion.setCellValue(i + 1);
            Cell celdaEntidad = filaDatos.createCell(1);
            celdaEntidad.setCellValue(entidad.getNombre());
        }

        return libro;
    }
}


