package domain.Rankings;

import domain.incidentes.RepositorioIncidentes;
import domain.servicios.EntidadPrestadora;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import domain.servicios.Entidad;
@Setter
@Getter
public class GeneradorRanking {
  private Ranking criterio;
  private List<Entidad> rankingActual;
public List<Entidad> generarRanking(){ //Falta que sea cada una semana
  rankingActual = criterio.generar();
  return criterio.generar();
  }
  public List<Entidad> devolverUltimoRanking(EntidadPrestadora entidadPrestadora){
    List<Entidad> entidadesDeEmpresas = entidadPrestadora.getEntidades();
    entidadesDeEmpresas.retainAll(rankingActual); // Intersecta las listas
    return entidadesDeEmpresas;
  }



}
