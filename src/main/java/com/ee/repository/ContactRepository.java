package com.ee.repository;

import com.ee.entities.ContactUsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactUsEntity, Integer> {
}
