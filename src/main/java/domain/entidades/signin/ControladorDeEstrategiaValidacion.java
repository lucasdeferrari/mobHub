package domain.entidades.signin;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEstrategiaValidacion {

  private List<EstrategiaValidacion> estrategiasDeValidacion = new ArrayList<>();

  public void agregarEstrategiaDeValidacion(EstrategiaValidacion estrategia) {
    estrategiasDeValidacion.add(estrategia);
  }

  public boolean verificarContrasenia(String contrasenia) {

    return  estrategiasDeValidacion.stream().allMatch(b -> b.verificarContrasenia(contrasenia));
  }

}
