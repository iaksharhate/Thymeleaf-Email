package com.example.demofcrnotification.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.example.demofcrnotification.dto.MailRequest;
import com.example.demofcrnotification.dto.MailResponse;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public MailResponse sendMail(MailRequest mailRequest, Map<String,Object> details){
        
        MailResponse response = new MailResponse();

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                                StandardCharsets.UTF_8.name());

            Context context = new Context();

            // model.addAttribute("data", mailRequest.getFcrDetail());

            context.setVariables(mailRequest.getFcrDetailsMap());
            System.out.println(mailRequest.getFcrDetailsMap());

            String html = templateEngine.process("fcr", context);

            helper.setTo(mailRequest.getTo());
            helper.setFrom(mailRequest.getFrom());
            helper.setSubject(mailRequest.getSubject());
            helper.setText(html, true);

            emailSender.send(message);

            response.setMessage("Mail sent to : "+ mailRequest.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException exception) {
            // TODO: handle exception
            response.setMessage("Mail sending failure: "+ exception.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
