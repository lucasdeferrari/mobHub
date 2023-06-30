package domain.Rankings;

import domain.servicios.Incidente;
import domain.servicios.Entidad;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.Getter;

@Getter
public abstract class Ranking {

  public abstract List<Entidad> generar(List<Incidente> incidentes);

  public Boolean esIncidenteDeEstaSemana(Incidente incidente) {
    LocalDateTime fechaActual = LocalDateTime.now();
    LocalDateTime inicioSemana = fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); //para que sea lunes el inicio de semana
    LocalDateTime finSemana = fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); //para obtener el domingo siguiente o el mismo día si es que ya es domingo. De esta manera, finSemana representa el domingo de la semana actual.
    LocalDateTime fechaCierre = incidente.getFechaHoraCierre();
    return fechaCierre.isAfter(inicioSemana) && fechaCierre.isBefore(finSemana);
  }
}

