package domain.LectorCSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import retrofit2.http.Path;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LectorCSV {

    //Dado que uno de los objetivos del Sistema es ayudar a mejorar la calidad de los servicios públicos,
// en esta versión se incorporan como usuarios de la plataforma a las empresas o entidades propietarias de los servicios públicos
// y a los organismos de control (en caso de que existiese por el tipo de servicio). Cada empresa podrá designar una persona a la cual le llegará información resumida sobre las problemáticas de los servicios que se ofrecen. De igual manera, los organismos de control podrán designar una persona con el mismo objetivo. La generación de la información que recibirán estará a cargo de un servicio de software específico que será detallado en la próxima entrega.
//La carga de datos de entidades prestadoras y organismos de control debe poder ser realizada en forma masiva a través de la carga de un archivo CSV.

    public Map<String, DatosCSV> csvUploader (String ruta_archivo) {// recibe el csv y lo lee.

        Map <String, DatosCSV> diccionarioDatos = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(ruta_archivo));

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord registro : parser) {
                String id = registro.get(0);
                String nombre = registro.get(1);
                String apellido = registro.get(2);
                String contacto = registro.get(3);
                String nombreOrganismo = registro.get(4);

                DatosCSV datos = new DatosCSV(nombre, apellido,contacto, nombreOrganismo);
                diccionarioDatos.put(id,datos);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
