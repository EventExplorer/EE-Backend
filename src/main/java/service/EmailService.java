package com.ee.service;


import com.ee.helper.BookingConfirmationEmailHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Service
public class EmailService {


    private final JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendBookingConfirmationEmail(BookingConfirmationEmailHelper bookingConfirmationEmailHelper) throws IOException {

        //subject Your Tickets Are Booked - Confirmation Code from Event Explorer
        String subject = "Your Tickets Are Booked - Confirmation Code From Event Explorer";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try{
            messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(bookingConfirmationEmailHelper.getEmail());
            messageHelper.setSubject(subject);

        }catch(MessagingException e){
            throw new RuntimeException(e);
        }

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("customerName",bookingConfirmationEmailHelper.getCustomerName());
        thymeleafContext.setVariable("eventName",bookingConfirmationEmailHelper.getEventName());
        thymeleafContext.setVariable("eventDate",bookingConfirmationEmailHelper.getEventDate());
        thymeleafContext.setVariable("eventTime",bookingConfirmationEmailHelper.getEventTime());
        thymeleafContext.setVariable("eventVenue",bookingConfirmationEmailHelper.getEventVenue());
        thymeleafContext.setVariable("numTickets",bookingConfirmationEmailHelper.getNumTickets());
        thymeleafContext.setVariable("confirmationCode",bookingConfirmationEmailHelper.getConfirmationCode());
        thymeleafContext.setVariable("seatNumber",bookingConfirmationEmailHelper.getSeatNumber());

        String htmlContent = templateEngine.process("booking_confirmation_template",thymeleafContext);
        try {
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);


    }

    public void sendEmail(String toEmail, String subject, String userName, String otp, String emailType) throws IOException {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // Create Thymeleaf context and set variables
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("userName", userName);
        thymeleafContext.setVariable("otp", otp);

        String htmlContent = "";
        if(emailType.equalsIgnoreCase("signup")) {
            // Process Thymeleaf template to generate HTML content
             htmlContent = templateEngine.process("verification-email-template", thymeleafContext);

        }else{
//            String resetLink = "http://Venkats-iMac.lan:8082/ee/v1/reset-password?"+otp;

            String resetOTP = otp;
            thymeleafContext.setVariable("resetLink", resetOTP);

            //http://example.com/reset-password?token=xxxxx&redirectUrl=http://frontend.com/reset-password-page
            htmlContent = templateEngine.process("password_reset_email_template", thymeleafContext);

        }
        try {
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        // Send the email
        javaMailSender.send(mimeMessage);


    }
}


