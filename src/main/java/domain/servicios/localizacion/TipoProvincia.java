package domain.servicios.localizacion;

import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.ListadoDeDepartamentos;
import domain.services.geoRef.entidades.ListadoDeProvincias;
import domain.services.geoRef.entidades.Provincia;
import domain.services.geoRef.interfaces.ServicioGeoRef;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class TipoProvincia extends Localizacion {

    @Override
    public Provincia obtenerLocalizacion() throws IOException {
        ServicioGeoRef servicioGeoref = ServicioGeoRef.instancia();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();

        listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id ? 1 : -1);

        for (Provincia unaProvincia : listadoDeProvinciasArgentinas.provincias) {
            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        }

        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

        Provincia provinciaSeleccionada = null;
        if (posibleProvincia.isPresent()) {
            provinciaSeleccionada = posibleProvincia.get();
            System.out.println(provinciaSeleccionada);
        }
        else {
            throw new IOException("No existe la provincia seleccionada");
        }

        return provinciaSeleccionada;
    }
}
