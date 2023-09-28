package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionTieneMinuscula implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*[a-z])");
  }
}
