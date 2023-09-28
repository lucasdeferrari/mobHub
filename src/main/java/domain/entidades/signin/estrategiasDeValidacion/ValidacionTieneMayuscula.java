package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionTieneMayuscula implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return contrasenia.matches("(?=.*[A-Z])");
  }
}
