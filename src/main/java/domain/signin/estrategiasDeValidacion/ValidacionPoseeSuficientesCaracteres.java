package domain.signin.estrategiasDeValidacion;

import domain.signin.EstrategiaValidacion;

class ValidacionPoseeSuficientesCaracteres implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return (contrasenia.length() > 7 && contrasenia.length() < 65);
  }
}
