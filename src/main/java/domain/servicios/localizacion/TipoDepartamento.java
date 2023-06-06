package domain.servicios.localizacion;

import domain.services.geoRef.entidades.ListadoDeDepartamentos;
import domain.services.geoRef.entidades.ListadoDeProvincias;
import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Provincia;
import domain.services.geoRef.interfaces.ServicioGeoRef;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class TipoDepartamento extends Localizacion {
    @Override
    public Departamento obtenerLocalizacion() throws IOException {
        ServicioGeoRef servicioGeoref2 = ServicioGeoRef.instancia();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref2.listadoDeProvincias();

        listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id ? 1 : -1);

        for (Provincia unaProvincia : listadoDeProvinciasArgentinas.provincias) {
            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        }

        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

        Provincia provinciaSeleccionada = null;
        Departamento departamentoElegido = null;
        if (posibleProvincia.isPresent()) {
            provinciaSeleccionada = posibleProvincia.get();
            ListadoDeDepartamentos departamentosDeLaProvincia = servicioGeoref2.listadoDeDepartamentosDeProvincia(provinciaSeleccionada);
            System.out.println("Los departamentos de la provincia " + provinciaSeleccionada.nombre + " son:");
            for (Departamento unDepartamento : departamentosDeLaProvincia.departamentos) {
                System.out.println(unDepartamento.nombre);
            }

            Scanner entradaEscaner2 = new Scanner(System.in);
            int idDepartamentoElegido = Integer.parseInt(entradaEscaner2.nextLine());

            Optional<Departamento> posibleDepartamento = departamentosDeLaProvincia.departamentoDeId(idDepartamentoElegido);
            departamentoElegido = null;

            if (posibleDepartamento.isPresent()) {
                departamentoElegido = posibleDepartamento.get();
            } else {
                System.out.println("No existe el departamento seleccionado.");
            }

        } else {
            System.out.println("No existe la provincia seleccionada");
        }
        return departamentoElegido;
    }
}
