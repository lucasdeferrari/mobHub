package domain.servicios;

import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;

import java.time.LocalDateTime;
import java.util.List;

public class Incidente {
    private String observaciones;
    private List<Comunidad> comunidades;
    private Miembro quienAbrio;
    private Miembro quienCerro;
    private Servicio servicio;
    private Establecimiento establecimiento;
    private Entidad entidad;
    private LocalDateTime fechaHoraApertura;
    private LocalDateTime fechaHoraCierre;

    public Incidente(List<Comunidad> comunidades, Miembro quienAbrio, Servicio servicio, Establecimiento establecimiento, Entidad entidad, LocalDateTime fechaHoraApertura) {
        this.comunidades = comunidades;
        this.quienAbrio = quienAbrio;
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        this.entidad = entidad;
        this.fechaHoraApertura = fechaHoraApertura;
    }
}

