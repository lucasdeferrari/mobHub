package domain.LectorCSV;

import domain.servicios.ConfigReader;


import java.util.HashMap;
import java.util.Map;


public class ImportadorDeEntidadesPrestadoras {

    Map<String, DatosCSV> diccionarioDatosEntidades = new HashMap<>();
    String rutaArchivoEntidadesPrestadoras = ConfigReader.getPropertyValue("pathEntidadesPrestadoras");

    public void importarEntidadesPrestadoras() {
        diccionarioDatosEntidades = ParserCSV.parserCSV(rutaArchivoEntidadesPrestadoras);
    }

}
