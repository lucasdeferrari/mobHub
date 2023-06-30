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
public class GeneradorRanking {
  private Ranking criterio;
  private List<Entidad> rankingActual;
public List<Entidad> generarRanking(){ //Falta que sea cada una semana
  rankingActual = criterio.generar();
  return criterio.generar();
  }
  public List<Entidad> devolverUltimoRanking(EntidadPrestadora entidadPrestadora){
    List<Entidad> entidadesDeEmpresas = entidadPrestadora.getEntidades();
    entidadesDeEmpresas.retainAll(rankingActual); // Intersecta las listas
    return entidadesDeEmpresas;
  }



}
