package com.ee.service;


import com.ee.helper.BookingConfirmationEmailHelper;
import com.ee.helper.EventCreationEmailHelper;
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

    public void sendEventCreationEmail(EventCreationEmailHelper eventCreationEmailHelper) throws IOException{
        String subject = "New Event Created - Event Explorer";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try{
            messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(eventCreationEmailHelper.getUserEmail());
            messageHelper.setSubject(subject);
    }catch(MessagingException e){
        throw new RuntimeException(e);
    }
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("eventName",eventCreationEmailHelper.getEventName());
        thymeleafContext.setVariable("eventType",eventCreationEmailHelper.getEventType());
        thymeleafContext.setVariable("eventDate",eventCreationEmailHelper.getEventDate());
        thymeleafContext.setVariable("eventTime",eventCreationEmailHelper.getEventTime());
        thymeleafContext.setVariable("venueName",eventCreationEmailHelper.getVenueName());
        thymeleafContext.setVariable("capacity",eventCreationEmailHelper.getCapacity());
        thymeleafContext.setVariable("regularPrice",eventCreationEmailHelper.getRegularPrice());
        thymeleafContext.setVariable("vipPrice",eventCreationEmailHelper.getVipPrice());
        thymeleafContext.setVariable("disabledPrice",eventCreationEmailHelper.getDisabledPrice());
        thymeleafContext.setVariable("city",eventCreationEmailHelper.getCity());
        thymeleafContext.setVariable("state",eventCreationEmailHelper.getState());
        thymeleafContext.setVariable("contactNumber",eventCreationEmailHelper.getContactNumber());
        thymeleafContext.setVariable("venueType",eventCreationEmailHelper.getVenueType());
        thymeleafContext.setVariable("description",eventCreationEmailHelper.getDescription());
        thymeleafContext.setVariable("eventEmailAddress",eventCreationEmailHelper.getEventEmailAddress());

        String htmlContent = templateEngine.process("event_creation_confirmation_template",thymeleafContext);
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
    public void sendContactUsEmail(String toEmail, String subject, String userName, String message) throws IOException {
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

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("userName", userName);
        thymeleafContext.setVariable("message", message);

        String htmlContent = templateEngine.process("contactus.html", thymeleafContext);

        try {
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);
    }

    public void sendOrganiserConfirmationEmail(String email) throws IOException {

        //subject Your Tickets Are Booked - Confirmation Code from Event Explorer
        String subject = "Congratulations! You're Now an Organizer";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try{
            messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);

        }catch(MessagingException e){
            throw new RuntimeException(e);
        }

        Context thymeleafContext = new Context();

        String htmlContent = templateEngine.process("organiser_confirmation_template",thymeleafContext);
        try {
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);


    }
}


