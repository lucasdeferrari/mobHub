package domain.Rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.comunidad.Rol;
import domain.servicios.Incidente;
import domain.servicios.Entidad;
import domain.servicios.TipoDeServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MayorGradoImpacto extends Ranking{

  @Override
  public List<Entidad> generar(List<Incidente> incidentes) {

    incidentes = incidentes.stream()
            .filter(unincidente -> esIncidenteDeEstaSemana(unincidente))
            .collect(Collectors.toList());

    List<Incidente> finalIncidentes = incidentes;
    List<Integer> listaGradoImpacto = incidentes.stream()
            .flatMap(incidente -> incidente.getComunidades().stream())
            .distinct()
            .map(comunidad -> gradoDeImpacto(comunidad, finalIncidentes))
            .collect(Collectors.toList());

    // TODAVIA NO TENEMOS EL DETALLE DE COMO DEVUELVE LA LISTA DE LAS ENTIDADES

    List<Entidad> listaEntidadesVacia = new ArrayList<>();
    return listaEntidadesVacia;
  }

  public Integer gradoDeImpacto(Comunidad comunidad, List<Incidente> incidentes){

    List<Miembro> listaMiembros = new ArrayList<>();

    for (Miembro unMiembro : comunidad.getMiembros()) {
      incidentes.forEach(unIncidente -> {
        TipoDeServicio tipoDeServicio = unIncidente.getServicio().getNombre();
        if(unMiembro.getRolesServicios()[tipoDeServicio.ordinal()] == Rol.AFECTADO) {
          listaMiembros.add(unMiembro);
        }
      });
    }
    return listaMiembros.size();
  }

}

