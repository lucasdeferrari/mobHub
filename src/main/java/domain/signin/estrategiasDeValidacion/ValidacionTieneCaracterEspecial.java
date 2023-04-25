package domain.signin.estrategiasDeValidacion;

import domain.signin.EstrategiaValidacion;

class ValidacionTieneCaracterEspecial implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*[@#$%^&+=!])");
  }
}
