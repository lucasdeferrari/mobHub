package domain.servicios;

import domain.Persistencia.EntidadPersistente;
import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@Table
public class Incidente extends EntidadPersistente {
    @Column
    private String observaciones;
    @OneToOne
    private Comunidad comunidad;
    @OneToOne
    private Miembro quienAbrio;
    @OneToOne
    private Miembro quienCerro;
    @Transient
    private Servicio servicio;
    @Transient
    private Establecimiento establecimiento;
    @OneToOne
    private Entidad entidad;
    @Column
    private LocalDateTime fechaHoraApertura;
    @Column
    private LocalDateTime fechaHoraCierre;

    // hacer el converter de la fecha todo
    // hacer bien el oneToOne todo
    // no entendi bien como seria el mapeo con servicios y establecimientos todo


    public long duracion(){
        Duration duracion = Duration.between(fechaHoraApertura, fechaHoraCierre);
        long horasTranscurridas = duracion.toMinutes() % 60;
        return horasTranscurridas;
    }

    public Incidente(Comunidad comunidad, Miembro quienAbrio, Servicio servicio, Establecimiento establecimiento, Entidad entidad, String observaciones, LocalDateTime fechaHoraApertura, LocalDateTime fechaHoraCierre) {
        this.comunidad = comunidad;
        this.quienAbrio = quienAbrio;
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        this.entidad = entidad;
        this.observaciones = observaciones;
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
