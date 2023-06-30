package domain.Rankings;


import domain.incidentes.Incidente;
import domain.incidentes.RepositorioIncidentes;
import domain.servicios.Entidad;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.Getter;

@Getter
public abstract class Ranking {
  protected RepositorioIncidentes repoIncidentes = RepositorioIncidentes.getInstancia();

  protected abstract List<Entidad> generar();
  public Boolean incidentesDeEstaSemana(Incidente incidente){
    LocalDate fechaActual = LocalDate.now();
    LocalDate inicioSemana = fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); //para que sea lunes el inicio de semana
    LocalDate finSemana = fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); //para obtener el domingo siguiente o el mismo día si es que ya es domingo. De esta manera, finSemana representa el domingo de la semana actual.
    LocalDate fechaCierre = incidente.getFechaCierre();
    return fechaCierre.isAfter(inicioSemana) && fechaCierre.isBefore(finSemana);
  }
}
