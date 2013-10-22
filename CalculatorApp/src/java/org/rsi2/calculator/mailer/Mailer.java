package org.rsi2.calculator.mailer;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    public void sendMail(String msg, String address) throws AddressException, MessagingException
    {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(props);
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject("siema eniu");
            message.setContent(msg, "text/plain");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", 587, "walm.arturwojno", "elomaniaelomania");
            transport.sendMessage(message, message.getAllRecipients());
    }
}
