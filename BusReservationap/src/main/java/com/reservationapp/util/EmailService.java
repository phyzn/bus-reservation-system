package com.reservationapp.util;


import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailWithAttachment(String to, String subject,
                                        String text, byte[] attachment,
                                        String attachmentName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
         //Attach the PDF file
            Resource resource = new ByteArrayResource(attachment,"application/pdf");
            helper.addAttachment(attachmentName, resource);

            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

