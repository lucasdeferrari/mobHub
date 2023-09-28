package domain.entidades.Rankings;

import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.RolServicio;
import domain.entidades.servicios.Entidad;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.TipoDeServicio;

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
    List<Integer> listaGradoImpacto = incidentes.stream().map(incidente -> incidente.getComunidad())
            .map(comunidad -> gradoDeImpacto(comunidad,finalIncidentes)).collect(Collectors.toList());


    List<Entidad> listaEntidadesVacia = new ArrayList<>();
    return listaEntidadesVacia;
  }

  public Integer gradoDeImpacto(Comunidad comunidad, List<Incidente> incidentes){

    List<Miembro> listaMiembros = new ArrayList<>();

    for (Miembro unMiembro : comunidad.getMiembros().keySet()) {
      incidentes.forEach(unIncidente -> {
        TipoDeServicio tipoDeServicio = unIncidente.getServicio().getNombre();
        if(unMiembro.getRolesServicios().get(tipoDeServicio.ordinal()) == RolServicio.AFECTADO) {
          listaMiembros.add(unMiembro);
        }
      });
    }
    return listaMiembros.size();
  }

}
