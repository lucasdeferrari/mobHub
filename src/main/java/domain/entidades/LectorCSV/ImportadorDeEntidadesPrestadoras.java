package domain.entidades.LectorCSV;

import controllers.EntidadesPrestadorasController;
import domain.entidades.servicios.ConfigReader;


import java.util.HashMap;
import java.util.Map;


public class ImportadorDeEntidadesPrestadoras {

    Map<String, DatosCSV> diccionarioDatosEntidades = new HashMap<>();
    String rutaArchivoEntidadesPrestadoras = ConfigReader.getPropertyValue("pathEntidadesPrestadoras");

    public boolean importarEntidadesPrestadoras(String archivoEntidadesPrestadoras, EntidadesPrestadorasController controladorEntidadesPrestadoras) {
        ParserCSV parserCSV = new ParserCSV();
        diccionarioDatosEntidades = parserCSV.parserCSV(archivoEntidadesPrestadoras);

        controladorEntidadesPrestadoras.procesarDiccionario(diccionarioDatosEntidades);

        return true;
    }

}
