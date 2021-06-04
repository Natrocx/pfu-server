package de.dhbw_mannheim.pfu_server.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl{

    @Autowired
    private JavaMailSender emailSender;

    public boolean sendSimpleMessage(
            String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            String sender = "pfustudconnect@gmail.com";

            message.setFrom(sender);
            message.setReplyTo(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);

            return true;
        } catch (Exception e){  //https://www.google.com/settings/security/lesssecureapps -> On
            return false;
        }

    }

    private SimpleMailMessage mailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is an automated message to verify your %s. Enter this code in the App to verify:" +
                        "\n\n%s" +
                        "\n\n" +
                        "Greetings, \n" +
                        "your team at StudConnect.");
        return message;
    }

    public boolean sendTemplatedMessage(String to, String subject, String target, String key){
        String text = String.format(mailTemplate().getText(), target, key);
        return sendSimpleMessage(to, subject, text);
    }
}
