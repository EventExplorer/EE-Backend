package com.ee.repository;

import com.ee.entities.BookingDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingDetailsEntity, String> {

    @Query("SELECT u FROM BookingDetailsEntity u WHERE u.seatNumber =:seatNumber")
    public Optional<BookingDetailsEntity> findBySeatNumber(@Param("seatNumber") String seatNumber);

    @Query("SELECT u FROM BookingDetailsEntity u WHERE u.emailId =:emailID")
    public List<BookingDetailsEntity> findByEmailId(@Param("emailID") String emailId);

    @Query("SELECT COALESCE(SUM(b.attendees), 0) FROM BookingDetailsEntity b WHERE b.eventId = :eventId")
    Integer getTotalAttendeesByEventId(@Param("eventId") String eventId);

    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM BookingDetailsEntity b WHERE b.eventId = :eventId")
    Integer getTotalPriceByEventId(@Param("eventId") String eventId);

}
