package domain.services.geoRef.entidades;

import java.util.List;
import java.util.Optional;
public class ListadoDeDepartamentos {
  public int cantidad;
  public int total;
  public int inicio;
  public Parametro parametros;
  public List<Departamento> departamentos;

  private class Parametro {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }

  public Optional<Departamento> departamentoDeId(int id){
    return this.departamentos.stream().filter(p -> p.id == id).findFirst();
  }

}