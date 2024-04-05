package com.ee.repository;

import com.ee.entities.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<EventsEntity, String> {

    @Query("SELECT u FROM EventsEntity u WHERE u.userEmail = :email")
    public List<EventsEntity> findByUserEmail(@Param("email") String email);


    @Query("SELECT u FROM EventsEntity u WHERE u.eventId = :id")
    public Optional<EventsEntity> findByEventId(@Param("id") String id);

    void deleteByEventId(String eventId);





}