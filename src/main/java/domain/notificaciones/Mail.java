package domain.notificaciones;

import domain.servicios.Incidente;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;
//public static void main(String[] args){

public class Mail {
    public static void main(String[] args) throws EmailException {

       // private static final String HOST = "smtp.gmail.com";

        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("diseniodesistemas123@gmail.com", "utndisenio2023"));
        email.setSSLOnConnect(true);
        email.setFrom("diseniodesistemas123@gmail.com");
        email.setSubject("Mi correo electrónico");
        email.setMsg("sos re puto general gariboto, lambeculo");
        email.addTo("lucasdeferrari03@gmail.com");
        email.addTo("alecaragiulo@gmail.com");
        email.addTo("juaniaguirre2001@gmail.com");
        email.addTo("gbeluccigaribotto@frba.utn.edu.ar");
        email.send();

//        String userName = "username@gmail.com";
//        String password = "password";
//
//        String fromAddress="lucasdeferrari03@gmail.com";
//        String toAddress =  "quickprogrammer@gmail.com";
//        String subject = "Test Mail";
//        String message = "Hello from Apache Mail";
//
//        try {
//            Email email = new SimpleEmail();
//            email.setHostName(HOST);
//            email.setSmtpPort(PORT);
//            email.setAuthenticator(new DefaultAuthenticator(userName, password));
//            email.setSSLOnConnect(SSL_FLAG);
//            email.setFrom(fromAddress);
//            email.setSubject(subject);
//            email.setMsg(message);
//            email.addTo(toAddress);
//            email.send();
//        }catch(Exception ex){
//            System.out.println("Unable to send email");
//            System.out.println(ex);
//        }
//
//
   }}
//}
//
//
//
//// Java program to send simple email using apache commons email
//// Uses the Gmail SMTP servers
//public class SimpleEmailSender {
//    private static final String HOST = "smtp.gmail.com";
//    private static final int PORT = 465;
//    private static final boolean SSL_FLAG = true;
//
//    public static void main(String[] args) {
//        SimpleEmailSender sender = new SimpleEmailSender();
//        sender.sendSimpleEmail();
//    }
//
//    private void sendSimpleEmail() {
//
//        String userName = "username@gmail.com";
//        String password = "password";
//
//        String fromAddress="username@gmail.com";
//        String toAddress =  "quickprogrammer@gmail.com";
//        String subject = "Test Mail";
//        String message = "Hello from Apache Mail";
//
//        try {
//            Email email = new SimpleEmail();
//            email.setHostName(HOST);
//            email.setSmtpPort(PORT);
//            email.setAuthenticator(new DefaultAuthenticator(userName, password));
//            email.setSSLOnConnect(SSL_FLAG);
//            email.setFrom(fromAddress);
//            email.setSubject(subject);
//            email.setMsg(message);
//            email.addTo(toAddress);
//            email.send();
//        }catch(Exception ex){
//            System.out.println("Unable to send email");
//            System.out.println(ex);
//        }
//    }
//
//    //javac -cp "javax.mail.jar;commons-email-1.4.jar;." SimpleEmailSender.java