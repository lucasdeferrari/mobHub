package domain.LectorCSV;

import domain.servicios.ConfigReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ImportadorDeEntidadesPrestadoras {

    Map<String, DatosCSV> diccionarioDatosEntidades = new HashMap<>();
    String rutaArchivoEntidadesPrestadoras = ConfigReader.getPropertyValue("pathEntidadesPrestadoras");

    public void importarEntidadesPrestadoras() {
        diccionarioDatosEntidades = ParserCSV.parserCSV(rutaArchivoEntidadesPrestadoras);
    }

}
