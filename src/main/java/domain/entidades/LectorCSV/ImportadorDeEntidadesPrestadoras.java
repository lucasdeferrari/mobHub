package domain.entidades.LectorCSV;

import domain.entidades.servicios.ConfigReader;


import java.util.HashMap;
import java.util.Map;


public class ImportadorDeEntidadesPrestadoras {

    Map<String, DatosCSV> diccionarioDatosEntidades = new HashMap<>();
    String rutaArchivoEntidadesPrestadoras = ConfigReader.getPropertyValue("pathEntidadesPrestadoras");

    public void importarEntidadesPrestadoras(String archivoEntidadesPrestadoras) {
        ParserCSV parserCSV = new ParserCSV();
        diccionarioDatosEntidades = parserCSV.parserCSV(archivoEntidadesPrestadoras);
    }

}
