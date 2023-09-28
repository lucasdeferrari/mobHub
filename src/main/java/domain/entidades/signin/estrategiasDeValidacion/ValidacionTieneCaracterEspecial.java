package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionTieneCaracterEspecial implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*[@#$%^&+=!])");
  }
}
