package com.ee.service;

import com.ee.customexception.BankInfoNotFoundException;
import com.ee.customexception.CustomerNotFoundException;
import com.ee.customexception.EventNotFoundException;
import com.ee.customexception.SeatBookedException;
import com.ee.dto.request.BookingRequest;
import com.ee.entities.*;
import com.ee.helper.BookingConfirmationEmailHelper;
import com.ee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class BookingService {

    private EventRepository eventRepository;
    private BookingRepository bookingRepository;
    private BillingRepository billingRepository;
    private TransactionRepository transactionRepository;
    private UserAccountRepository userAccountRepository;

    @Autowired
    private  EmailService emailService;

    @Autowired
    public BookingService(EventRepository eventRepository, BookingRepository bookingRepository, BillingRepository billingRepository, TransactionRepository transactionRepository, UserAccountRepository userAccountRepository){
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
        this.billingRepository = billingRepository;
        this.transactionRepository = transactionRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public String bookingInformation(BookingRequest bookingRequest) throws CustomerNotFoundException, BankInfoNotFoundException, EventNotFoundException, IOException {

        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(bookingRequest.getEmailId());




        BookingDetailsEntity bookingDetailsProfile = new BookingDetailsEntity();
        if (userProfile.isPresent()) {
            Optional<EventsEntity> eventProfile = eventRepository.findByEventId(bookingRequest.getEventId());

            if (eventProfile.isPresent()) {
                Optional<BillingInfoEntity> billingInfoProfile = billingRepository.findByUserEmailAndBankName(bookingRequest.getEmailId(), bookingRequest.getBankName());
                if ((billingInfoProfile.isPresent())) {

                    bookingDetailsProfile.setEmailId(bookingRequest.getEmailId());
                    bookingDetailsProfile.setEventId(bookingRequest.getEventId());
                    bookingDetailsProfile.setOrderNumber(UUID.randomUUID().toString());

                    Optional<BookingDetailsEntity> seatNumberVerification = bookingRepository.findBySeatNumber(Arrays.toString(bookingRequest.getSeatNumber()));
                    if(seatNumberVerification.isEmpty()){

                        System.out.println("seat numbers" + Arrays.toString(bookingRequest.getSeatNumber()));

                        bookingDetailsProfile.setSeatNumber(Arrays.toString(bookingRequest.getSeatNumber()));
                    }else{
                        throw new SeatBookedException("These seats are booked");
                    }


                    bookingDetailsProfile.setAttendees(Integer.parseInt(bookingRequest.getAttendees()));
                    bookingDetailsProfile.setTotalPrice(BigDecimal.valueOf(Double.parseDouble(bookingRequest.getTotalPrice())));
                    LocalDateTime currentTimeStamp = LocalDateTime.now();
                    bookingDetailsProfile.setCreateTs(Timestamp.valueOf(currentTimeStamp));
                    bookingDetailsProfile.setUpdateTs(Timestamp.valueOf(currentTimeStamp));

                    bookingDetailsProfile.setConfirmationCode(transactionCode());


                    String billingUuidProfile = billingRepository.findBillingUuidByEmailAndBankName(bookingRequest.getEmailId(), bookingRequest.getBankName());
                    bookingDetailsProfile.setBillingUuid(billingUuidProfile);

                    bookingRepository.save(bookingDetailsProfile);

                    TransactionDetailsEntity transactionDetailsProfile = new TransactionDetailsEntity();
                    transactionDetailsProfile.setBillingUuid(bookingDetailsProfile.getBillingUuid());
                    transactionDetailsProfile.setTransactionTs(Timestamp.valueOf(currentTimeStamp));
                    transactionDetailsProfile.setOrderNumber(bookingDetailsProfile.getOrderNumber());
                    transactionDetailsProfile.setBankName(bookingRequest.getBankName());
                    transactionDetailsProfile.setTransactionId(UUID.randomUUID().toString());
                    transactionDetailsProfile.setTotalPrice(bookingDetailsProfile.getTotalPrice());
                    transactionDetailsProfile.setStatus("Success");

                    transactionRepository.save(transactionDetailsProfile);

                    //email service
                    BookingConfirmationEmailHelper emailHelper = new BookingConfirmationEmailHelper();

                    emailHelper.setCustomerName(userProfile.get().getLastName());

                    emailHelper.setEventName(eventProfile.get().getEventName());
                    emailHelper.setEventDate(eventProfile.get().getEventDate());
                    emailHelper.setEventTime(eventProfile.get().getEventTime());
                    emailHelper.setEventVenue(eventProfile.get().getVenueName());
                    emailHelper.setNumTickets(bookingRequest.getAttendees());
                    emailHelper.setConfirmationCode(bookingDetailsProfile.getConfirmationCode());
                    emailHelper.setEmail(bookingRequest.getEmailId());
                    emailHelper.setSeatNumber(Arrays.toString(bookingRequest.getSeatNumber()));

                    emailService.sendBookingConfirmationEmail(emailHelper);

                    return bookingDetailsProfile.getConfirmationCode();
                }else{
                    throw new BankInfoNotFoundException("Required Bank Information");
                }
            } else {
                throw new EventNotFoundException("Event ID Not Found");
            }
        }else{
            throw new CustomerNotFoundException("Customer Not Found");
        }
    }

    public String transactionCode(){
        // Generate a 6-character alphanumeric OTP
        StringBuilder confirmationCode = new StringBuilder();

        // Define the characters that can be used in the code
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            confirmationCode.append(characters.charAt(index));
        }

        return confirmationCode.toString();
    }
}
