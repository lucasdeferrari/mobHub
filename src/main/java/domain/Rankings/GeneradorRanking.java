package domain.Rankings;

import domain.servicios.Incidente;
import domain.servicios.Entidad;
import domain.servicios.EntidadPrestadora;
import domain.servicios.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
public class GeneradorRanking { //todo para mi va tsingleton
  private static List<Entidad> rankingMayorCantidadReportes; //de esta semana
  private static List<Entidad> rankingMayorPromedioCierre;
  private static List<Entidad> rankingMayorGradoImpacto;
  private static List<Incidente> incidentes = new ArrayList<>();
  private Timer timer;

  LocalTime horaDeInicio = LocalTime.of(0,0,0);

  public void main() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    // Calcula el retraso hasta la hora exacta
    LocalTime horaActual = LocalTime.now();
    long delayInicial = horaActual.until(horaDeInicio, ChronoUnit.SECONDS);

    if (delayInicial < 0) {
      delayInicial += 24 * 60 * 60; // Agrega 24 horas si la hora deseada ya pasó hoy
    }
    // Programa la tarea para que se ejecute en la hora exacta
    executor.schedule(() -> {
      timer = new Timer();
      timer.schedule(task, 0,  7);
    }, delayInicial, TimeUnit.DAYS);

    // Cierra el executor después de ejecutar la tarea
    executor.shutdown();
  }

  TimerTask task = new TimerTask() {
    public void run() {
      // habria que agarrar con un metodo, la lista de incidentes, aca o en algun lado antes
      generarRanking();
    }
  };
  public List<Entidad> devolverUltimoRanking(EntidadPrestadora entidadPrestadora){
    List<Entidad> entidadesDeEmpresas = entidadPrestadora.getEntidades();
    entidadesDeEmpresas.retainAll(rankingActual); // Intersecta las listas
    return entidadesDeEmpresas;
  }

  public static List<Entidad> rankingSegunCriterio(Ranking criterio) {
    if (criterio instanceof MayorPromedioCierre) {
      return rankingMayorPromedioCierre;
    }
    else if (criterio instanceof MayorCantidadReportes) {
      return rankingMayorCantidadReportes;
    }
    else if (criterio instanceof MayorGradoImpacto) {
      return rankingMayorGradoImpacto;
    }
  throw new RuntimeException();
  }
}


