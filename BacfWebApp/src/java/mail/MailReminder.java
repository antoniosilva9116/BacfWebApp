/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Voluntario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.mail.Message.RecipientType;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;

/**
 *
 * @author hemc
 */
@Named("mailReminder")
@Singleton
public class MailReminder {

    private static final String FROM_NAME = "Banco alimentar contra a fome";
    private static final String FROM_EMAIL = "bacfmailer@gmail.com";
    private static final String USERNAME = "bacfmailer@gmail.com";
    private static final String PASSWORD = "passwordbacf";
    private static final String SUBJECT = "Feliz Aniversário";
    private static final String EMAIL_CONTENT = "Dear Mail Crawler," + "\n\n No spam to my email, please!";
    private static final String EMAIL_CONTENT_HTML = "\n\n No spam to my email, please!";
    private static final String EMAIL_CONTENT_HTML_ANI = "<p> vimos por este meio felicitar pelo seu anivers&aacute;rio!</p> Com os melhores cumprimentos,<p> Banco Alimentar contra a fome </p>";

    private static final String TO_DEFAULT_NAME = "Aniversariante";
    private static final String TEST_DESTINATARIO = "nunoslvdr@gmail.com";
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final int SMTP_PORT_TLS = 587;
    private static final int SMTP_PORT_SSL = 465;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbFacade;

    private VoluntarioFacade getFacade() {
        return ejbFacade;
    }

    //daily at midnight (00:00:00).
    @Schedule(hour = "18", minute = "54", second = "0", persistent = false)
    public void run() {
        // Do your check and mail job here.
        List<Voluntario> voluntarios = getFacade().findAll();
        List<Voluntario> aniversariantes = new ArrayList<Voluntario>();

        Date date = new Date();

        for (Voluntario voluntario : voluntarios) {
            Date dateVoluntario = new Date();

            if (voluntario.getDataNascimento().getMonth() == date.getMonth() && voluntario.getDataNascimento().getDate() == date.getDate()) {
                aniversariantes.add(voluntario);
            }

        }

//        mandarEmailsTeste();
        if (aniversariantes.size() > 0) {
            mandarEmailsAniversariantes(aniversariantes);
        }
    }

    
    public void mandaEmailVoluntarios(String subject, String conteudo, List<Voluntario> voluntarios) {

        Mailer mailer = new Mailer(SMTP_SERVER, SMTP_PORT_SSL, USERNAME, PASSWORD, TransportStrategy.SMTP_SSL);

        for (Voluntario voluntario : voluntarios) {
            Email email = new Email();
            email.setFromAddress(FROM_NAME, FROM_EMAIL);
            email.setSubject(subject);
            email.addRecipient(voluntario.getNome(), voluntario.getEmail(), RecipientType.TO);
            //email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
            email.setText(conteudo);
            email.setTextHTML("<p>Caro " + voluntario.getNome() + ",</p>" + conteudo);

            // embed images and include downloadable attachments
            //email.addEmbeddedImage("wink1", imageByteArray, "image/png");
            //email.addEmbeddedImage("wink2", imageDatesource);
            //email.addAttachment("invitation", pdfByteArray, "application/pdf");
            //email.addAttachment("dresscode", odfDatasource);
            mailer.sendMail(email);
        }
    }

    public void mandarEmail(String subject, String nomeDestinatario, String emailDestinatario, String conteudo) {
//        SendMailSSL.sendTestMail(null);

        //SendMailSSL.sendMailToListOfEmails(listaEmailAniversariantes);
        Mailer mailer = new Mailer(SMTP_SERVER, SMTP_PORT_SSL, USERNAME, PASSWORD, TransportStrategy.SMTP_SSL);

        Email email = new Email();
        email.setFromAddress(FROM_NAME, FROM_EMAIL);
        email.setSubject(subject);
        email.addRecipient(nomeDestinatario, emailDestinatario, RecipientType.TO);
        //email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
        email.setText(conteudo);
//        email.setTextHTML("<p>Caro " + s.getNome() + ",</p>" + EMAIL_CONTENT_HTML_ANI);

        // embed images and include downloadable attachments
        //email.addEmbeddedImage("wink1", imageByteArray, "image/png");
        //email.addEmbeddedImage("wink2", imageDatesource);
        //email.addAttachment("invitation", pdfByteArray, "application/pdf");
        //email.addAttachment("dresscode", odfDatasource);
        mailer.sendMail(email);
        //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_SSL).sendMail(email);
        //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_TLS).sendMail(email);

    }

    public void mandarEmailsAniversariantes(List<Voluntario> listaEmailAniversariantes) {
//        SendMailSSL.sendTestMail(null);

        //SendMailSSL.sendMailToListOfEmails(listaEmailAniversariantes);
        Mailer mailer = new Mailer(SMTP_SERVER, SMTP_PORT_SSL, USERNAME, PASSWORD, TransportStrategy.SMTP_SSL);

        for (Voluntario s : listaEmailAniversariantes) {
            Email email = new Email();
            email.setFromAddress(FROM_NAME, FROM_EMAIL);
            email.setSubject(SUBJECT);
            email.addRecipient(s.getNome(), s.getEmail(), RecipientType.TO);
            //email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
            email.setText(EMAIL_CONTENT);
            email.setTextHTML("<p>Caro " + s.getNome() + ",</p>" + EMAIL_CONTENT_HTML_ANI);

            // embed images and include downloadable attachments
            //email.addEmbeddedImage("wink1", imageByteArray, "image/png");
            //email.addEmbeddedImage("wink2", imageDatesource);
            //email.addAttachment("invitation", pdfByteArray, "application/pdf");
            //email.addAttachment("dresscode", odfDatasource);
            mailer.sendMail(email);
            //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_SSL).sendMail(email);
            //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_TLS).sendMail(email);
        }
    }

    public void mandarEmailsTeste() {
//        SendMailSSL.sendTestMail(null);

        ArrayList<String> listaEmailAniversariantes = new ArrayList<>();
        listaEmailAniversariantes.add("nunoslvdr@gmail.com");

        //SendMailSSL.sendMailToListOfEmails(listaEmailAniversariantes);
        Mailer mailer = new Mailer("smtp.gmail.com", SMTP_PORT_SSL, USERNAME, PASSWORD, TransportStrategy.SMTP_SSL);

        for (String s : listaEmailAniversariantes) {
            Email email = new Email();
            email.setFromAddress(FROM_NAME, FROM_EMAIL);
            email.setSubject(SUBJECT);
            email.addRecipient(TO_DEFAULT_NAME, s, RecipientType.TO);
            //email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
            email.setText(EMAIL_CONTENT);
            email.setTextHTML(EMAIL_CONTENT_HTML);

            // embed images and include downloadable attachments
            //email.addEmbeddedImage("wink1", imageByteArray, "image/png");
            //email.addEmbeddedImage("wink2", imageDatesource);
            //email.addAttachment("invitation", pdfByteArray, "application/pdf");
            //email.addAttachment("dresscode", odfDatasource);
            mailer.sendMail(email);
            //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_SSL).sendMail(email);
            //new Mailer("smtp.host.com", 25, "username", "password", TransportStrategy.SMTP_TLS).sendMail(email);
        }
    }
}
