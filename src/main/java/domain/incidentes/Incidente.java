package domain.incidentes;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.servicios.Entidad;
import domain.servicios.Establecimiento;
import domain.servicios.TipoDeServicio;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class Incidente {
  private String observaciones;
  private List<Comunidad> comunidades;
  private Miembro quienAbrio; // ???
  private Miembro quiencerro;
  private TipoDeServicio servicio;
  private Establecimiento establecimiento;
  private Entidad entidad;
  private LocalDate fechaApertura;
  private LocalTime horaApertura;
  private LocalDate fechaCierre;
  private LocalTime horaCierre;

  public Duration duracion(){
    return Duration.between(this.horaCierre, this.horaApertura);
  }

}
