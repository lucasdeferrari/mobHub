package domain.entidades.signin;

import java.util.List;

public class ControladorDeEstrategiaValidacion {

  private List<EstrategiaValidacion> estrategiasDeValidacion;

  public void agregarEstrategiaDeValidacion(EstrategiaValidacion estrategia) {
    estrategiasDeValidacion.add(estrategia);
  }

  public boolean verificarContrasenia(String contrasenia) {

    return  estrategiasDeValidacion.stream().allMatch(b -> b.verificarContrasenia(contrasenia));
  }

}
