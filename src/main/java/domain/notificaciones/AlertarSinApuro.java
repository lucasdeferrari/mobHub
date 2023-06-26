package domain.notificaciones;

import domain.servicios.Incidente;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlertarSinApuro implements FormaNotificacion{
    private Timer timer;
    private MedioDeNotificacion receptor;
    private List<Incidente> incidentes;
    private LocalTime horarioDeNotificacion;

    public void notificar(Incidente unIncidente) {
        incidentes.add(unIncidente);
    }

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
            timer.schedule(task, 0, 30 * 60 * 1000);
        }, delayInicial, TimeUnit.SECONDS);

        // Cierra el executor después de ejecutar la tarea
        executor.shutdown();
    }

    TimerTask task = new TimerTask() {
        public void run() {
            boolean result = esLaHora();
            if(result) {
               // receptor.notificar(incidentes);
                //TODO
            }
        }
    };

    private Boolean esLaHora(){
        LocalTime horarioActual = LocalTime.now();
        return horarioActual == horarioDeNotificacion;
    }
}