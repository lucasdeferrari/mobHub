package domain.notificaciones.medioDeNotificaciones;

import domain.comunidad.Miembro;
import domain.notificaciones.notificacion.Notificacion;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    private static final String username = "diseniodesistemas123@gmail.com";
    private static final String password = "utndisenio2023";

    public static void main(Notificacion notificacion, Miembro miembro) {


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(miembro.getCorreoElectronico()));
            message.setSubject(notificacion.asunto);
            message.setText(notificacion.cuerpo);

            Transport.send(message);

            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void notificar(Notificacion notificacion, Miembro unMiembro) {
        // Llamada al método main
        EmailSender.main(notificacion, unMiembro);
    }
}