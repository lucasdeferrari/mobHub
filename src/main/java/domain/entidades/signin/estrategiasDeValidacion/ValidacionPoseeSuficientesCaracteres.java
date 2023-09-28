package domain.entidades.signin.estrategiasDeValidacion;

import domain.entidades.signin.EstrategiaValidacion;

class ValidacionPoseeSuficientesCaracteres implements EstrategiaValidacion {

  public boolean verificarContrasenia(String contrasenia) {
    return (contrasenia.length() > 7 && contrasenia.length() < 65);
  }
}
