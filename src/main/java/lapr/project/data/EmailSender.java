/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lapr.project.ui.Log;

/**
 *
 * @author grupo 11
 */
public class EmailSender {
    
    /**
     * Enviar email
     * @param recepient destinatario
     * @param subject assunto
     * @param body corpo de mail
     */
    public static void sendMail(String recepient, String subject, String body ) {
    String user = System.getProperty("email.user");
    String password = System.getProperty("email.password");

    
    Session session = Session.getInstance(System.getProperties(), new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }
    });
    
    Message message = prepareMessage(session, user, recepient, subject, body);
           try {
               Transport.send(message);
           } catch (MessagingException ex) {
               Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
           }
}

    /**
     * Mensagem
     * @param session sessao
     * @param user utilizador
     * @param recepient destinatario
     * @param subject assunto
     * @param body corpo do mail
     * @return mensagem
     */
    private static Message prepareMessage(Session session, String user, String recepient, String subject, String body){
     try{
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
         message.setSubject(subject);
         message.setText(body);
         return message;
     }catch (Exception ex){
         Log.logWarning(ex.toString());
     }
         return null;
    }

}
