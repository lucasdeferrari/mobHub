package domain.servicios;

import domain.services.geoRef.entidades.ListadoDeMunicipios;
import domain.services.geoRef.entidades.ListadoDeProvincias;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import domain.services.geoRef.interfaces.ServicioGeoRef;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Localizacion {
    private String provincia;
    private String departamento;
    private String municipio;

//    public static void obtenerLocalizacion() throws IOException {
//        ServicioGeoRef servicioGeoref2 = ServicioGeoRef.instancia();
//        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");
//
//        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref2.listadoDeProvincias();
//
//        listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id? 1 : -1);
//
//        for(Provincia unaProvincia:listadoDeProvinciasArgentinas.provincias){
//            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
//        }
//
//        Scanner entradaEscaner = new Scanner(System.in);
//        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());
//
//        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);
//
//        if(posibleProvincia.isPresent()){
//            Provincia provinciaSeleccionada = posibleProvincia.get();
//            ListadoDeMunicipios municipiosDeLaProvincia = servicioGeoref2.listadoDeMunicipiosDeProvincia(provinciaSeleccionada);
//            System.out.println("Los municipios de la provincia "+ provinciaSeleccionada.nombre +" son:");
//            for(Municipio unMunicipio: municipiosDeLaProvincia.municipios){
//                System.out.println(unMunicipio.nombre);
//            }
//        }
//        else{
//            System.out.println("No existe la provincia seleccionada");
//        }
//    }
}
