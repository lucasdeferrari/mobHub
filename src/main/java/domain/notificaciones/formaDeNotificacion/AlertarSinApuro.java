package domain.notificaciones.formaDeNotificacion;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.NotificacionApertura;
import domain.notificaciones.notificacion.Notificacion;
import org.apache.commons.mail.EmailException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AlertarSinApuro implements FormaNotificacion{
    private Timer timer;
    private List<Notificacion> notificacionesAEnviar;
    private LocalTime horarioDeNotificacion;
    private Miembro miembro;

    public void notificar(Notificacion unaNotificacion, Miembro unMiembro) {
        notificacionesAEnviar.add(unaNotificacion);
        miembro = unMiembro;
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
            if(esLaHora()) {
                List<NotificacionApertura> notificacionesApertura = filtrarAlertarSinApuro(notificacionesAEnviar); //consigue todas las notificaciones SinApuro que dentro tienen los incidentes

                notificacionesApertura = notificacionesApertura.stream().filter(unaNotificacion-> unaNotificacion.getIncidenteAsociado().estadoAbierto())
                        .collect(Collectors.toList());

                String nuevoCuerpo = "";
                notificacionesApertura.forEach(unaNotificacion -> {nuevoCuerpo.concat(unaNotificacion.cuerpo); nuevoCuerpo.concat(" \n");});


                NotificacionApertura notificacion = new NotificacionApertura(null);   //
                notificacion.setAsunto("Esta listo su nuevo resumen de notificaciones.");
                notificacion.setCuerpo(nuevoCuerpo);
                
                try {
                    miembro.getMedioDeNotificacion().notificar(notificacion, miembro);
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

    public List<NotificacionApertura> filtrarAlertarSinApuro(List<Notificacion> listaNotificaciones) {
        List<NotificacionApertura> listaFiltrada = new ArrayList<>();
        for (Notificacion notificacion : listaNotificaciones) {
            if (notificacion instanceof NotificacionApertura) {
                listaFiltrada.add((NotificacionApertura) notificacion);
            }
        }

        return listaFiltrada;
    }
}

