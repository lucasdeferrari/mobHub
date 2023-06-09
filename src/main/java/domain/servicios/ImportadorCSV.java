package domain.servicios;

import domain.LectorCSV.DatosCSV;
import domain.LectorCSV.LectorCSV;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ImportadorCSV {
    private LectorCSV lectorCsv;
    Map <String, DatosCSV> diccionarioDatos = new HashMap<>();

    private static final String Ruta_Archivo = "C:\\Users\\Lucas\\OneDrive - UTN.BA\\Documentos\\facultad\\2023-tpa-mama-grupo-18\\src\\main\\java\\domain\\LectorCSV\\archivo.csv"; // ruta de archivo fija

    public ImportadorCSV(LectorCSV lectorCsv, Map<String, DatosCSV> diccionarioDatos) {
        this.lectorCsv = lectorCsv;
        this.diccionarioDatos = diccionarioDatos;
    }

    public void traerDiccionario() {
        diccionarioDatos = lectorCsv.csvUploader(Ruta_Archivo);
    }
}
