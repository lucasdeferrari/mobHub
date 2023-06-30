package domain.Rankings;
import java.time.LocalTime;
import domain.servicios.Incidente;
import domain.servicios.Entidad;
import domain.servicios.Incidente;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.DayOfWeek;
public class MayorPromedioCierre extends Ranking {

  @Override
  public List<Entidad> generar() {
    List<Incidente> incidentesCerrados = repoIncidentes.incidentesCerrados();
    Map<Entidad, Double> promedioTiempoCierre = new HashMap<>();
    Map<Entidad, Integer> cantidadIncidentes = new HashMap<>();

    incidentesCerrados = incidentesCerrados.stream().filter(incidente ->this.incidentesDeEstaSemana(incidente))
        .collect(Collectors.toList()); //hago que sean de esta semana

    for (Incidente incidente : incidentesCerrados) {
      Entidad entidad_incidente = incidente.getEntidad();
      long tiempoCierre = incidente.duracion();

      if (promedioTiempoCierre.containsKey(entidad_incidente)) {
        double promedioActual = promedioTiempoCierre.get(entidad_incidente); //consigo el promedio de la entidad
        int cantidadActual = cantidadIncidentes.get(entidad_incidente); //consigo la cantidad de incidentes

        double nuevoPromedio = (promedioActual * cantidadActual + tiempoCierre) / (cantidadActual + 1);
        promedioTiempoCierre.put(entidad_incidente, nuevoPromedio);
        cantidadIncidentes.put(entidad_incidente, cantidadActual + 1);
      } else {
        promedioTiempoCierre.put(entidad_incidente, (double) tiempoCierre); //si no encuentra el incidente
        cantidadIncidentes.put(entidad_incidente, 1);
      }
    }

    // Ordenar las entidades por su promedio de tiempo de cierre en orden descendente
    List<Entidad> entidadesOrdenadas = new ArrayList<>(promedioTiempoCierre.keySet()); //agarro las entidades del map
    entidadesOrdenadas.sort((e1, e2) -> Double.compare(promedioTiempoCierre.get(e2), promedioTiempoCierre.get(e1))); //las ordeno por las que mas tardan a menos

    return entidadesOrdenadas;
  }


}

// aca
