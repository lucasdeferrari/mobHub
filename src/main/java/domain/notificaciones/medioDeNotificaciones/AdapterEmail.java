package domain.notificaciones.medioDeNotificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;

public class AdapterEmail implements MedioNotificacion {
    private EmailSender adapter;

    public void notificar(Notificacion notificacion, Miembro miembro){
        this.adapter.notificar(notificacion, miembro);
    }


}
