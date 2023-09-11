package domain.services.geoRef.entidades;

import java.util.List;
import java.util.Optional;
public class ListadoDeLocalidades {
  public int cantidad;
  public int total;
  public int inicio;
  public Parametro parametros;
  public List<Localidad> departamentos;

  private class Parametro {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }

  public Optional<Localidad> departamentoDeId(int id){
    return this.departamentos.stream().filter(p -> p.id == id).findFirst();
  }

}