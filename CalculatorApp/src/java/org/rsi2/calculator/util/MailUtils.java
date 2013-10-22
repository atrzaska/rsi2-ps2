package org.rsi2.calculator.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
    public static void sendMail(String address, String msg) throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
        message.setSubject("CalculatorService");
        message.setContent(msg, "text/plain");
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", 587, "atrzaska2@gmail.com", "948z5ls4kz");
        transport.sendMessage(message, message.getAllRecipients());
    }
}
