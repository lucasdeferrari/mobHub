package domain.entidades.LectorCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ParserCSV {

    public Map<String, DatosCSV> parserCSV(String ruta_archivo) {// recibe el csv y lo lee.

        Map<String, DatosCSV> diccionarioDatos = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(ruta_archivo));

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord registro : parser) {
                String id = registro.get(0);
                String nombre = registro.get(1);
                String apellido = registro.get(2);
                String contacto = registro.get(3);
                String nombreOrganismo = registro.get(4);
                String tipoDato = registro.get(5);

                DatosCSV datos = new DatosCSV(nombre, apellido, contacto, nombreOrganismo, tipoDato);
                diccionarioDatos.put(id, datos);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return diccionarioDatos;


    }
}