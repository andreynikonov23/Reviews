package nick.pack.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String emailTo, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}
