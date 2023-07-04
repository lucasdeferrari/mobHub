package domain.notificaciones.formaDeNotificacion;

import domain.comunidad.Miembro;
import domain.notificaciones.medioDeNotificaciones.MedioDeNotificacion;
import domain.notificaciones.tipoDeNotificacion.TipoNotificacion;
import domain.servicios.Incidente;
import org.apache.commons.mail.EmailException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AlertarSinApuro implements FormaNotificacion{
    private Timer timer;
    private MedioDeNotificacion receptor;
    private List<TipoNotificacion> notificacionesAEnviar;
    private LocalTime horarioDeNotificacion;

    public void notificar(TipoNotificacion unaNotificacion) {
        notificacionesAEnviar.add(unaNotificacion);
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
            timer.schedule(task, 0, 30);
        }, delayInicial, TimeUnit.MINUTES);

        // Cierra el executor después de ejecutar la tarea
        executor.shutdown();
    }

    TimerTask task = new TimerTask() {
        public void run() {
            boolean result = esLaHora();
            if(result) {
                notificacionesAEnviar.stream().filter(unaNotificacion->unaNotificacion.filtrarSegunTipo()).collect(Collectors.toList());
                try {
                    receptor.notificar(notificacionesAEnviar);
                } catch (EmailException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };


    private Boolean esLaHora(){
        LocalTime horarioActual = LocalTime.now();
        return horarioActual == horarioDeNotificacion;
    }
}