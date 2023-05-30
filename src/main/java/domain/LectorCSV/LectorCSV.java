package domain.LectorCSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorCSV {

    private static final String Ruta_Archivo = //path del archivo... ;

    public static void main (String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(Ruta_Archivo));

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord registro : parser) {
                String id = registro.get(0);
                String nombre = registro.get(1);
                String contacto = registro.get(2);

                //para printear
//                System.out.println("ID " + id );
//                System.out.println("nombre " + nombre );
//                System.out.println("contacto " + contacto );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
