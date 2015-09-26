/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mail;

/**
 *
 * @author hemc
 */
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailSSL {
    private static final String FROM_EMAIL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String TEST_TO_EMAIL = "";
    private static final String SUBJECT = "Feliz Aniversário";
    private static final String EMAIL_CONTENT = "Dear Mail Crawler," +
					"\n\n No spam to my email, please!";
    
	public static void sendTestMail(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME,PASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(TEST_TO_EMAIL));
			message.setSubject(SUBJECT);
			message.setText(EMAIL_CONTENT);
 
			Transport.send(message);
                        /*
                        Session mailSession = Session.getDefaultInstance(props, null);
                        Transport transport = mailSession.getTransport();
                        transport.connect();
                        transport.sendMessage(message,
                            message.getRecipients(Message.RecipientType.TO));
                        transport.close();
                        */
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        public static void sendMailToListOfEmails(List<String> listaEmails) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME,PASSWORD);
				}
			});
                
                for(String s :listaEmails) {
                    try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(s));
			message.setSubject(SUBJECT);
			message.setText(EMAIL_CONTENT);
			Transport.send(message);
                    } catch (MessagingException e) {
                            //throw new RuntimeException(e);
                    }
                }
	}
}
