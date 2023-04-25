package domain.signin.estrategiasDeValidacion;

import domain.signin.EstrategiaValidacion;

class ValidacionNoTieneCaracteresSecuenciales implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?!.*(.)\1{3,})");
  }
}
