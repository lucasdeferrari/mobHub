package domain.notificaciones;

import domain.servicios.Incidente;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface MedioDeNotificacion {
    public void notificar(List<Incidente> incidentes) throws EmailException;

}
