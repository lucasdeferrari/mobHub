package domain.Rankings;

import domain.generadorRankings.GenerarExcellInforme;
import domain.servicios.*;
import domain.servicios.Incidente;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Workbook;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
public class GeneradorRanking { //todo para mi va singleton
  private static List<Entidad> rankingMayorCantidadReportes; //de esta semana
  private static List<Entidad> rankingMayorPromedioCierre;
  private static List<Entidad> rankingMayorGradoImpacto;
  private static List<Incidente> incidentes;
  private static ExportadorInforme exportadorInforme;
  private Timer timer;

  LocalTime horaDeInicio = LocalTime.of(0, 0, 0);
  // LA CLASE SE DEBE EJECUTAR POR PRIMERA VEZ UN DOMINGO

  public  void main(String[] args) {
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
      timer.schedule(task, 0, 7);
    }, delayInicial, TimeUnit.DAYS);

    // Cierra el executor después de ejecutar la tarea
    executor.shutdown();
  }

  TimerTask task = new TimerTask() {
    public void run() {
      Ranking criterio1 = new MayorPromedioCierre();
      Ranking criterio2 = new MayorCantidadReportes();
      Ranking criterio3 = new MayorGradoImpacto();

      rankingMayorCantidadReportes =  criterio1.generar(incidentes);
      rankingMayorGradoImpacto = criterio2.generar(incidentes);
      rankingMayorPromedioCierre = criterio3.generar(incidentes);

      incidentes.clear();
    }
  };

  public static void agregarIncidente(Incidente unIncidente) {
    incidentes.add(unIncidente);
  }


  public static void devolverInformeEntidadPrestadora(EntidadPrestadora entidadPrestadora, Ranking criterio) {
    List<Entidad> entidadesDeEmpresas = entidadPrestadora.getEntidades();
    entidadesDeEmpresas.retainAll(rankingSegunCriterio(criterio)); // Intersecta las listas
    exportadorInforme.exportarInforme(entidadesDeEmpresas);
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


