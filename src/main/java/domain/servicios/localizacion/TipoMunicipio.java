package domain.servicios.localizacion;

import domain.services.geoRef.entidades.*;
import domain.services.geoRef.interfaces.ServicioGeoRef;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class TipoMunicipio extends Localizacion {

    @Override
    public Municipio obtenerLocalizacion() throws IOException {
        ServicioGeoRef servicioGeoref1 = ServicioGeoRef.instancia();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref1.listadoDeProvincias();

        listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id ? 1 : -1);

        for (Provincia unaProvincia : listadoDeProvinciasArgentinas.provincias) {
            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        }

        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

        Provincia provinciaSeleccionada = null;
        Departamento departamentoElegido = null;

        Municipio municipioElegido;

        if (posibleProvincia.isPresent()) {
            provinciaSeleccionada = posibleProvincia.get();
            ListadoDeMunicipios municipiosDeLaProvincia = servicioGeoref1.listadoDeMunicipiosDeProvincia(provinciaSeleccionada);
            System.out.println("Los municipios de la provincia " + provinciaSeleccionada.nombre + " son:");
            for (Municipio unMunicipio : municipiosDeLaProvincia.municipios) {
                System.out.println(unMunicipio.nombre);
            }
            Scanner entradaEscaner1 = new Scanner(System.in);
            int idMunicipioElegido = Integer.parseInt(entradaEscaner1.nextLine());

            Optional<Municipio> posibleMunicipio = municipiosDeLaProvincia.municipioDeId(idMunicipioElegido);
            municipioElegido = null;

            if (posibleMunicipio.isPresent()) {
                municipioElegido = posibleMunicipio.get();
            } else {
                System.out.println("No existe el municipio seleccionado.");
            }
        } else {
            throw new IOException("No existe la provincia seleccionada");
        }
        return municipioElegido;
    }
}
