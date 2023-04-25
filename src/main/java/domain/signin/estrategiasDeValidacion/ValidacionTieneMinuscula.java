package domain.signin.estrategiasDeValidacion;

import domain.signin.EstrategiaValidacion;

class ValidacionTieneMinuscula implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*[a-z])");
  }
}
