package domain.incidentes;

import domain.servicios.Servicio;
import domain.servicios.TipoDeServicio;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RepositorioIncidentes {

private static RepositorioIncidentes instancia = null;

public static RepositorioIncidentes getInstancia(){
  if(instancia == null) {
    instancia = new RepositorioIncidentes();
  }
  return instancia;
  }
  private RepositorioIncidentes(){
  //inicializar la instancia
}


  public List<Incidente> incidentes(){
    List<Incidente> incidentes = new ArrayList<>();
   return incidentes;
  }
  public List<Incidente> incidentesAbiertos(){
    List<Incidente> incidentesAbiertos = new ArrayList<>();;
    return incidentesAbiertos;
  } // la conseguimos en otra capa
  public List<Incidente> incidentesCerrados(){
    List <Incidente> incidentesCerrados = new ArrayList<>() ;
    return incidentesCerrados;
  }

//  puL
}
