package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionNoTieneCaracteresSecuenciales implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?!.*(.)\1{3,})");
  }
}
