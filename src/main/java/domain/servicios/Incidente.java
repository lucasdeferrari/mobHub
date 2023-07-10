package domain.servicios;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class Incidente {
    private String observaciones;
    private List<Comunidad> comunidades; //TODO ver si va o no
    private Miembro quienAbrio;
    private Miembro quienCerro;
    private Servicio servicio;
    private Establecimiento establecimiento;
    private Entidad entidad;
    private LocalDateTime fechaHoraApertura;
    private LocalDateTime fechaHoraCierre;


    public long duracion(){
        Duration duracion = Duration.between(fechaHoraApertura, fechaHoraCierre);
        long horasTranscurridas = duracion.toMinutes() % 60;
        return horasTranscurridas;
    }

    public Incidente(List<Comunidad> comunidades, Miembro quienAbrio, Servicio servicio, Establecimiento establecimiento, Entidad entidad, LocalDateTime fechaHoraApertura, LocalDateTime fechaHoraCierre) {
        this.comunidades = comunidades;
        this.quienAbrio = quienAbrio;
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        this.entidad = entidad;
        this.fechaHoraApertura = fechaHoraApertura;
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public void ponerDisponible() {
        servicio.disponible();
    }

    public Boolean estadoAbierto() {
        return (fechaHoraCierre == null);
    }

    public boolean tieneEsteServicio(Servicio unServicio) {
        return servicio == unServicio;
    }

}
