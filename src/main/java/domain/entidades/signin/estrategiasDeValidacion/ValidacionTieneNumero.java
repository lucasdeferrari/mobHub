package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionTieneNumero implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*\\d)");
  }
}
