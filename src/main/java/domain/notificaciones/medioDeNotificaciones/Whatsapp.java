package domain.notificaciones.medioDeNotificaciones;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Whatsapp {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC8089600acca9fc93c63ba5bee993936f";
    public static final String AUTH_TOKEN = "941e609076b6204db1db10a32c1b0f77";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+5491157519116"),
                new PhoneNumber("whatsapp:+14155238886"),
                "la wea fue esta y esta").create();

        System.out.println(message.getSid());
    }
}