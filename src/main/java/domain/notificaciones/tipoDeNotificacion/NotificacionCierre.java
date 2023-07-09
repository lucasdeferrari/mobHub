package domain.notificaciones.tipoDeNotificacion;

import domain.comunidad.Miembro;
import domain.servicios.Incidente;

public class NotificacionCierre implements Notificacion {
        private Incidente incidenteAsociado;

        public NotificacionCierre(Incidente incidenteEnviado) {
        incidenteAsociado = incidenteEnviado;
        }
        public String asunto() {
            return "Se cerro un incidente "; // SE DEBERIA AGREGAR INFO PROPIA DEL INCIDENTE
        }

        public String cuerpo(){
            return incidenteAsociado.getObservaciones(); // a chequear idealizando lo que manda mas adelante
        }

}

