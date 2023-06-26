package domain.notificaciones;

import domain.servicios.Incidente;

import java.util.List;

public interface FormaNotificacion {
    public void notificar(Incidente incidente);
}