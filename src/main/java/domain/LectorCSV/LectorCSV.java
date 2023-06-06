package domain.LectorCSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import retrofit2.http.Path;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorCSV {
    private static final String Ruta_Archivo = "/archivo.csv"; // ruta de archivo fija

    public static void csvUploader () {// recibe el csv y lo lee.
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
/*
import java.io.File; // este CHATGPT


    public static void csvUploader(File archivoCSV) {   // ruta de archivo variable.
        try {
            Reader reader = Files.newBufferedReader(archivoCSV.toPath());

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord registro : parser) {
                String id = registro.get(0);
                String nombre = registro.get(1);
                String contacto = registro.get(2);

                // Resto del código
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String Ruta_Archivo = variable de la interfaz que me devuelve el path. Problema de despues.

    public static void csvUploader () {// recibe el csv y lo lee.
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


/*