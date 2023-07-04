package domain.notificaciones.tipoDeNotificacion;

import domain.servicios.Incidente;

public interface TipoNotificacion {
    public String asunto();
    public String cuerpo();
}
