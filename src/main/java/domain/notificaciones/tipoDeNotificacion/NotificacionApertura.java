package domain.notificaciones.tipoDeNotificacion;

import domain.servicios.Incidente;

public class NotificacionApertura implements Notificacion {
    private Incidente incidenteAsociado;


    public NotificacionApertura(Incidente incidenteEnviado) {
        incidenteAsociado = incidenteEnviado;
    }


    public String asunto() {
        return "Se creo un nuevo incidente "; // SE DEBERIA AGREGAR INFO PROPIA DEL INCIDENTE
    }

    public String cuerpo(){
        return incidenteAsociado.getObservaciones(); // a chequear idealizando lo que manda mas adelante
    }

    public Boolean sigueAbierta() {
        return incidenteAsociado.sigueAbierto();
    }

}
