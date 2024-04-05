package com.ee.repository;

import com.ee.entities.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, String> {
}
