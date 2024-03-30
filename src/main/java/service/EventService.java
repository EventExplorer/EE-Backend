package com.ee.service;

import com.ee.customexception.CustomerNotFoundException;
import com.ee.customexception.EventNotFoundException;
import com.ee.dto.request.BookingRequest;
import com.ee.dto.request.DeleteEventRequest;
import com.ee.dto.request.EventRequest;
import com.ee.dto.response.EventResponse;
import com.ee.dto.response.EventStatisticsResponse;
import com.ee.dto.response.PastAndUpcomingEventsResponse;
import com.ee.entities.*;
import com.ee.helper.EventCreationEmailHelper;
import com.ee.repository.*;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserAccountRepository userAccountRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    private  EmailService emailService;

    @Autowired
    public EventService(EventRepository eventRepository, UserAccountRepository userRepository, BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.userAccountRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }


    public String createEventFor(EventRequest eventRequest) throws CustomerNotFoundException, IOException {
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(eventRequest.getUserEmail());

        if (userProfile.isPresent()) {
            UserAccountEntity userAccountEntity = userProfile.get();
            EventsEntity eventsEntity = new EventsEntity();

            eventsEntity.setUserEmail(eventRequest.getUserEmail());
            eventsEntity.setCustomerId(UUID.randomUUID().toString());
            if(eventRequest.getEventId().isEmpty()){
                eventsEntity.setEventId(UUID.randomUUID().toString());

            }else {
                eventsEntity.setEventId(eventRequest.getEventId());
            }
            eventsEntity.setEventName(eventRequest.getEventName());
            eventsEntity.setEventType(eventRequest.getEventType());
            eventsEntity.setEventDate(Date.valueOf(eventRequest.getEventDate()));
            eventsEntity.setEventTime(Time.valueOf(eventRequest.getEventTime()));
            eventsEntity.setVenueName(eventRequest.getVenueName());
            eventsEntity.setCapacity(Integer.parseInt(eventRequest.getCapacity()));
            LocalDateTime currentTimeStamp = LocalDateTime.now();
            eventsEntity.setCreateTs(Timestamp.valueOf(currentTimeStamp));
            eventsEntity.setUpdateTs(Timestamp.valueOf(currentTimeStamp));
            eventsEntity.setRegularPrice(BigDecimal.valueOf(Double.parseDouble(eventRequest.getRegularPrice())));
            eventsEntity.setVipPrice(BigDecimal.valueOf(Double.parseDouble(eventRequest.getVipPrice())));
            eventsEntity.setDisabledPrice(BigDecimal.valueOf(Double.parseDouble(eventRequest.getDisabledPrice())));
            eventsEntity.setCity(eventRequest.getCity());
            eventsEntity.setState(eventRequest.getState());
            eventsEntity.setContactNumber(eventRequest.getContactNumber());
            eventsEntity.setVenueType(eventRequest.getVenueType());
            eventsEntity.setDescription(eventRequest.getDescription());
            eventsEntity.setEventEmailAddress(eventRequest.getEventEmailAddress());


            //save the created event to the database
            eventRepository.save(eventsEntity);

            //email Service
            EventCreationEmailHelper emailHelper = new EventCreationEmailHelper();

            emailHelper.setUserEmail(eventRequest.getUserEmail());
            emailHelper.setEventName(eventRequest.getEventName());
            emailHelper.setEventType(eventRequest.getEventType());
            emailHelper.setEventDate(eventRequest.getEventDate());
            emailHelper.setEventTime(eventRequest.getEventTime());
            emailHelper.setVenueName(eventRequest.getVenueName());
            emailHelper.setCapacity(eventRequest.getCapacity());
            emailHelper.setRegularPrice(eventRequest.getRegularPrice());
            emailHelper.setVipPrice(eventRequest.getVipPrice());
            emailHelper.setDisabledPrice(eventRequest.getDisabledPrice());
            emailHelper.setCity(eventRequest.getCity());
            emailHelper.setState(eventRequest.getState());
            emailHelper.setContactNumber(eventRequest.getContactNumber());
            emailHelper.setVenueType(eventRequest.getVenueType());
            emailHelper.setDescription(eventRequest.getDescription());
            emailHelper.setEventEmailAddress(eventRequest.getEventEmailAddress());


            emailService.sendEventCreationEmail(emailHelper);

            return "Event Created";
        }
        throw new CustomerNotFoundException("Account Not Found");

    }

    public List<EventResponse> getAllEvents() {
        List<EventsEntity> eventsEntities = eventRepository.findAll();
        List<EventResponse> responses = new ArrayList<>();

        for (EventsEntity eventsEntity : eventsEntities) {

            if(eventsEntity.getEventEmailAddress().equals("eventexplorer099@gmail.com")){
                //skip adding the response
            }else{
                EventResponse response = convertToResponseDTO(eventsEntity);
                responses.add(response);
            }

        }
        return responses;
    }

    private EventResponse convertToResponseDTO(EventsEntity eventsEntity) {
        EventResponse response = new EventResponse();
        // Set only the fields you want to expose in the API response
        response.setEventId(eventsEntity.getEventId());
        response.setEventName(eventsEntity.getEventName());
        response.setEventType(eventsEntity.getEventType());
        response.setEventDate(eventsEntity.getEventDate());
        response.setEventTime(eventsEntity.getEventTime());
        response.setVenueName(eventsEntity.getVenueName());
        response.setCapacity(eventsEntity.getCapacity());
        response.setRegularPrice(eventsEntity.getRegularPrice());
        response.setVipPrice(eventsEntity.getVipPrice());
        response.setDisabledPrice(eventsEntity.getDisabledPrice());
        response.setCity(eventsEntity.getCity());
        response.setState(eventsEntity.getState());
        response.setContactNumber(eventsEntity.getContactNumber());
        response.setVenueType(eventsEntity.getVenueType());
        response.setDescription(eventsEntity.getDescription());
        response.setEventEmailAddress(eventsEntity.getEventEmailAddress());
        response.setUserEmail(eventsEntity.getUserEmail());
        response.setCreateTimeStamp(eventsEntity.getCreateTs().toLocalDateTime());

        return response;
    }

    public List<EventResponse> getAllEventsById(String email) {
        List<EventsEntity> userList = eventRepository.findByUserEmail(email);
        List<EventResponse> resultList = new ArrayList<>();

        if (!userList.isEmpty()) {
            for (EventsEntity entity : userList) {
                EventResponse response = convertToResponseDTO(entity);
                resultList.add(response);
            }
            return resultList;
        }
        throw new CustomerNotFoundException("Email ID Not Found");
    }


    public String deleteEvent(DeleteEventRequest deleteEventRequest) {
        Optional<EventsEntity> optionalEvent = eventRepository.findById((deleteEventRequest.getEventId()));
        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(deleteEventRequest.getEventId());
        } else {
            throw new EventNotFoundException("Event with ID " + deleteEventRequest.getEventId() + " not found");
        }
        return "Deleted";
    }

    public List<PastAndUpcomingEventsResponse> pastEvents(String email) throws CustomerNotFoundException, EventNotFoundException{
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);

        List<PastAndUpcomingEventsResponse> pastEvents = new ArrayList<>();
        PastAndUpcomingEventsResponse response = null;
        LocalDate currentDate = LocalDate.now();

        if(userProfile.isPresent()){
            List<BookingDetailsEntity> bookingProfile = bookingRepository.findByEmailId(email);

            if(!(bookingProfile.isEmpty())) {

                for (BookingDetailsEntity b : bookingProfile) {
                    Optional<EventsEntity> eventProfile = eventRepository.findByEventId(b.getEventId());
                    Date eventDate = eventProfile.get().getEventDate();

                    if(eventDate.before(Date.valueOf(currentDate))){
                        response = new PastAndUpcomingEventsResponse();
                        response.setEventDate(eventDate);
                        response.setOrderNumber(b.getOrderNumber());
                        response.setEventName(eventProfile.get().getEventName());
                        response.setVenueName(eventProfile.get().getVenueName());
                        response.setVenueType(eventProfile.get().getVenueType());
                        response.setCity(eventProfile.get().getCity());
                        response.setState(eventProfile.get().getState());

                        pastEvents.add(response);
                    }

                }
            }else{
                throw new EventNotFoundException("You have No Events ");
            }

        }else{
            throw new CustomerNotFoundException("Customer Not Found");
        }
        return pastEvents;

    }

    public List<PastAndUpcomingEventsResponse> futureEvents(String email) throws CustomerNotFoundException, EventNotFoundException{
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);
        List<PastAndUpcomingEventsResponse> futureEvents = new ArrayList<>();
        PastAndUpcomingEventsResponse response = null;
        LocalDate currentDate = LocalDate.now();

        if(userProfile.isPresent()){
            List<BookingDetailsEntity> bookingProfile = bookingRepository.findByEmailId(email);

            if(!(bookingProfile.isEmpty())) {

                for (BookingDetailsEntity b : bookingProfile) {
                    Optional<EventsEntity> eventProfile = eventRepository.findByEventId(b.getEventId());

                    Date eventDate = eventProfile.get().getEventDate();

                    if(eventDate.after(Date.valueOf(currentDate))){
                        response = new PastAndUpcomingEventsResponse();
                        response.setEventDate(eventDate);
                        response.setOrderNumber(b.getOrderNumber());
                        response.setEventName(eventProfile.get().getEventName());
                        response.setVenueName(eventProfile.get().getVenueName());
                        response.setVenueType(eventProfile.get().getVenueType());
                        response.setCity(eventProfile.get().getCity());
                        response.setState(eventProfile.get().getState());

                        futureEvents.add(response);
                    }

                }
            }else{
                throw new EventNotFoundException("You have No Events ");
            }

        }else{
            throw new CustomerNotFoundException("Customer Not Found");
        }
        return futureEvents;

    }
    public List<EventStatisticsResponse> eventStatistics(String email){
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);
        List<EventStatisticsResponse> eventStatsResponseList = new ArrayList<>();

        if (userProfile.isPresent()) {
            List<EventsEntity> eventsEntityProfile = eventRepository.findByUserEmail(email);

            if(!(eventsEntityProfile.isEmpty())) {

                for(EventsEntity eventsEntity: eventsEntityProfile) {
                    //Optional<EventsEntity> eventsEntityProfile = eventRepository.findByEventId(bookingDetailsEntity.getEventId());

                    EventStatisticsResponse eventStatisticsResponse = new EventStatisticsResponse();

                    eventStatisticsResponse.setEventName(eventsEntity.getEventName());
                    eventStatisticsResponse.setEventDescription(eventsEntity.getDescription());
                    eventStatisticsResponse.setEventType(eventsEntity.getEventType());
                    eventStatisticsResponse.setEventCity(eventsEntity.getCity());
                    eventStatisticsResponse.setEventState(eventsEntity.getState());
                    eventStatisticsResponse.setEventCapacity(String.valueOf(eventsEntity.getCapacity()));
                    eventStatisticsResponse.setEventDate(eventsEntity.getEventDate());

                    int eventSoldTickets = bookingRepository.getTotalAttendeesByEventId(eventsEntity.getEventId());

                    eventStatisticsResponse.setSoldCapacity(String.valueOf(eventSoldTickets));

                    int remainingTickets = eventsEntity.getCapacity() - eventSoldTickets;
                    eventStatisticsResponse.setRemainingCapacity(String.valueOf(remainingTickets));

                    BigDecimal totalEventRevenue = eventsEntity.getRegularPrice().multiply(BigDecimal.valueOf(eventSoldTickets));

                    BigDecimal totalEventRevenue1 = BigDecimal.valueOf(bookingRepository.getTotalPriceByEventId(eventsEntity.getEventId()));
                    eventStatisticsResponse.setTotalRevenue(String.valueOf(totalEventRevenue1));

                    eventStatsResponseList.add(eventStatisticsResponse);
                }
            }else{
                throw new EventNotFoundException("No Events");
            }
        }else{
            throw new CustomerNotFoundException("Account Not Found");

        }
        return eventStatsResponseList;
    }
}