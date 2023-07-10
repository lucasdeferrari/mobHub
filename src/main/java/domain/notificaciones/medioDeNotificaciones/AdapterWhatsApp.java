package domain.notificaciones.medioDeNotificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;

public class AdapterWhatsApp implements MedioDeNotificacion {
    private Whatsapp adapter;
    public void notificar(Notificacion notificacion, Miembro miembro){
        this.adapter.notificar(notificacion, miembro);
    }
}
