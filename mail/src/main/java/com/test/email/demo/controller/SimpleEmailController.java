package com.test.email.demo.controller;

import com.test.email.demo.My_Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {

    @Autowired
    private JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/mail")
    public String sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("LeMailFonctionne@etudiant.univ-rennes1.fr");
        message.setTo(My_Contants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");
        /*message.setTo("kouassi-othniel.konan@etudiant.univ-rennes1.fr");*/


        // Send Message!
        this.emailSender.send(message);
        return "Email Sent";
    }

}
