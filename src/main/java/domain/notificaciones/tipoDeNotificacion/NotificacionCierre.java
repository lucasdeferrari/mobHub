package domain.notificaciones.tipoDeNotificacion;

import domain.servicios.Incidente;

public class NotificacionCierre implements TipoNotificacion{
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

        public Boolean filtrarSegunTipo(Miembro miembro) {
            // se fija que se haya enviado la notificacion de apertura al miembro.
        }

}
