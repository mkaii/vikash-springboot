package org.geekster;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandlerBase {

    public void sendMail()
    {

        //properties

        //something like a map
        Properties sysPropertiesMap = System.getProperties();


        //in this map I want to add 4 more key value pairs : mailing

        sysPropertiesMap.put("mail.smtp.host","smtp.gmail.com");
        sysPropertiesMap.put("mail.smtp.port","465");
        sysPropertiesMap.put("mail.smtp.ssl.enable","true");
        sysPropertiesMap.put("mail.smtp.auth","true");

        //authentication

        Authenticator mailAuthenticator = new CustomizedMailAuthenticator();


        //session
        Session mailSession = Session.getInstance(sysPropertiesMap,mailAuthenticator);


        //build the mail
        //Mime message

        MimeMessage mailMessage = new MimeMessage(mailSession);

        try {
            mailMessage.setFrom(MailConstants.SENDER);
            mailMessage.setSubject("vikas Mailing class");
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("vikash.kosaraju1234@gmail.com"));
            mailMessage.setText("vikash is reaching you through java!!!");

            Transport.send(mailMessage);

            System.out.println("Mail sent !!!");
        }
        catch(Exception ex)
        {
            System.out.println("Some error while preparing mail body!!!!");
            System.out.println(ex.getMessage());
        }






    }
}
