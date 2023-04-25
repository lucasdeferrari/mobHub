package domain.signin.estrategiasDeValidacion;

import domain.signin.EstrategiaValidacion;

class ValidacionNoTieneEspaciosEnBlanco implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=\\S+$)");
  }
}
