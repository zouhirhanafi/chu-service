package ma.ensaf.support.mail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.extern.slf4j.Slf4j;
import ma.ensaf.user.dto.UserDto;

@Service
@Slf4j
public class MailService {

    private static final String BASE_URL = "baseUrl";

	@Autowired
	private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    private String from;
    @Value("${app.baseUrl}")
    private String baseUrl;
    
//    @PostConstruct
    void test() {
//    	Mail m1 = Mail.builder().to("zouhirhanafi@gmail.com")
//    				.subject("validation compte")
//    				.body("validation compte")
//    				.build();
//    	
//    	sendEmail(m1);
//    	Mail m2 = Mail.builder().to("zouhirhanafi@gmail.com")
//    			.subject("validation compte")
//    			.templateName("mail/activationEmail")
//    			.build();
//    	Map<String, Object> model = new HashMap<String, Object>();
//    	model.put("user", UserDto.builder().firstName("Zouhir").email("email").build());
//    	sendEmailFromTemplate(m2, model);
    	
    	Map<String, Object> model = new HashMap<String, Object>();
        model.put("baseUrl", baseUrl);
        model.put("user", UserDto.builder().firstName("Zouhir").email("email").build());
        model.put("sign", "Java Developer");
        
        Mail m2 = Mail.builder().to("zouhirhanafi@gmail.com")
    			.subject("validation compte")
    			.templateName("mail/activationEmail")
    			.model(model)
    			.build();
        
        sendEmailFromTemplate(m2);
    }

	@Async
    public void sendEmail(Mail mail) {
        log.debug("Send email : {}", mail);
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            message.setTo(mail.getTo());
            if (!ObjectUtils.isEmpty(mail.getCc())) {
            	message.setCc(mail.getCc());
            }
            if (!ObjectUtils.isEmpty(mail.getBcc())) {
            	message.setBcc(mail.getBcc());
            }
            message.setFrom(from);
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody(), mail.isHtml());
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", mail.getTo());
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", mail.getTo(), e);
        }
    }

    @Async
    public void sendEmailFromTemplate(Mail mail) {
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariables(mail.getModel());
        context.setVariable(BASE_URL, baseUrl);
        String content = templateEngine.process(mail.getTemplateName(), context);
        mail.setBody(content);
        mail.setHtml(true);
        sendEmail(mail);
    }

//
//    @Async
//    public void sendActivationEmail(User user) {
//        log.debug("Sending activation email to '{}'", user.getEmail());
//        sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
//    }
//
//    @Async
//    public void sendCreationEmail(User user) {
//        log.debug("Sending creation email to '{}'", user.getEmail());
//        sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
//    }
//
//    @Async
//    public void sendPasswordResetMail(User user) {
//        log.debug("Sending password reset email to '{}'", user.getEmail());
//        sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
//    }

}
