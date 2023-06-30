package domain.Rankings;

import domain.servicios.Incidente;
import domain.servicios.Entidad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;
public class MayorGradoImpacto extends Ranking {

  @Override
  public List<Entidad> generar() {
    List<Incidente> listaIncidentes = repoIncidentes.incidentes();
    Map<Entidad, Integer> cantidadIncidentes = new HashMap<>();

    listaIncidentes = listaIncidentes.stream().filter(incidente ->this.incidentesDeEstaSemana(incidente))
        .collect(Collectors.toList()); //hago que sean de esta semana

    for (Incidente incidente : listaIncidentes) {
      Entidad entidad = incidente.getEntidad();
      if (existeIncidenteReportado(entidad, incidente, listaIncidentes)) { // si el incidente fue reportado
        continue;
      }
        cantidadIncidentes.put(entidad, cantidadIncidentes.get(entidad) + 1); //si tiene le sumo uno

      }

    List<Entidad> entidadesOrdenadas = new ArrayList<>(cantidadIncidentes.keySet()); //agarro las entidades del map
    entidadesOrdenadas.sort((e1, e2) -> Double.compare(cantidadIncidentes.get(e2), cantidadIncidentes.get(e1))); //las ordeno por las que mas tienen a menos
    return entidadesOrdenadas;
  }

  public Boolean existeIncidenteReportado(Entidad entidad, Incidente incidente, List<Incidente> lista_incidentes) {

    if (incidente.getHoraCierre() == null) { //es incidenteAbierto
      for(Incidente incidente_lista: lista_incidentes) {
        if (menosDe24Horas(incidente, incidente_lista) && esElMismoIncidente(incidente, incidente_lista)) {
          return true;
        }
      }
    }
    return false;
  }

  public Boolean esElMismoIncidente(Incidente incidente1, Incidente incidente2){
    Boolean condicion1 = incidente1.getEstablecimiento() == incidente2.getEstablecimiento();
    Boolean condicion2 = incidente1.getServicio() == incidente2.getServicio();
    Boolean condicion3 = incidente1.getEntidad() == incidente2.getEntidad();
    return condicion1 && condicion2 && condicion3;

  }

  private static boolean menosDe24Horas(Incidente incidente1, Incidente incidente2) {
    LocalDateTime fechaApertura1 = LocalDateTime.of(incidente1.getFechaApertura(), incidente1.getHoraApertura());
    LocalDateTime fechaApertura2 = LocalDateTime.of(incidente2.getFechaApertura(), incidente2.getHoraApertura());

    Duration duracionEntreIncidentes = Duration.between(fechaApertura1, fechaApertura2);
    long horasEntreIncidentes = duracionEntreIncidentes.toHours();

    return horasEntreIncidentes < 24;
  }
}