package com.lightning.school.mvc.delegate.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class MailSender {

    private JavaMailSender emailSender;

    private String TO;
    private String SUBJECT;
    private Map<String, String> PROPERTIES;
    private String TEMPLATE_CONTENT;

    private MailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public static MailSender buildMail(JavaMailSender javaMailSender){
        return new MailSender(javaMailSender);
    }

    public MailSender loadTemplate(String name){
        try {
            File file = ResourceUtils.getFile("classpath:mail/template/"+name+".html");
            new FileInputStream(file).read(TEMPLATE_CONTENT.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return this;
    }

    public MailSender appendTo(String to){
        this.TO = to;
        return this;
    }

    public MailSender appendSubject(String subject){
        this.SUBJECT = subject;
        return this;
    }

    public MailSender appendProperties(Map<String, String> properties){
        this.PROPERTIES = properties;
        return this;
    }

    public void send() throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(TO);
        message.setSubject(SUBJECT);
        message.setText(TEMPLATE_CONTENT);
        emailSender.send(message);
    }
}
