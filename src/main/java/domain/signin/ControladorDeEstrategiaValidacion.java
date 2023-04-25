package domain.signin;

import java.util.List;

public class ControladorDeEstrategiaValidacion {

  private List<EstrategiaValidacion> estrategiasDeValidacion;

  public void agregarEstrategiaDeValidacion(EstrategiaValidacion estrategia) {
    estrategiasDeValidacion.add(estrategia);
  }

  public boolean verificarContrasenia(Usuario usuario) { //que usuario sea paramtro (?
    String contrasenia = usuario.getContrasenia();
    return  estrategiasDeValidacion.stream().allMatch(b -> b.verificarContrasenia(contrasenia));
  }

}
