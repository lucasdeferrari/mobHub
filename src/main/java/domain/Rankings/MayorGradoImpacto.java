package domain.Rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.comunidad.Rol;
import domain.servicios.Incidente;
import domain.servicios.Entidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.javatuples.KeyValue;

public class MayorGradoImpacto extends Ranking{

  @Override
  public List<Entidad> generar(List<Incidente> incidentes) {

    incidentes = incidentes.stream()
            .filter(unincidente -> esIncidenteDeEstaSemana(unincidente))
            .collect(Collectors.toList());

    List<Integer> listaGradoImpacto = incidentes.stream()
            .flatMap(incidente -> incidente.getComunidades().stream())
            .distinct()
            .map(comunidad -> gradoDeImpacto(comunidad))
            .collect(Collectors.toList());

    // TODAVIA NO TENEMOS EL DETALLE DE COMO DEVUELVE LA LISTA DE LAS ENTIDADES

    List<Entidad> listaEntidadesVacia = new ArrayList<>();
    return listaEntidadesVacia;
  }

  public Integer gradoDeImpacto(Comunidad comunidad){

    List<Miembro> listaMiembros = new ArrayList<>();

    for (Map.Entry<Miembro, Rol> unMiembro : comunidad.getMiembros().entrySet()) {
      if (unMiembro.getValue() == Rol.AFECTADO) {
        listaMiembros.add(unMiembro.getKey());
      }
    }
    return listaMiembros.size();
  }

}

