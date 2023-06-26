package domain.notificaciones;

import domain.servicios.Incidente;

import java.util.ArrayList;
import java.util.List;

public class AlertarCuandoSucede implements FormaNotificacion {

    private MedioDeNotificacion receptor;

    public void notificar(Incidente unIncidente) {
        List<Incidente> incidente = new ArrayList<>();
        incidente.add(unIncidente);
        //receptor.notificar(incidente);
        //TODO
    }


}
