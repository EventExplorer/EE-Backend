package com.ee.repository;

import com.ee.entities.CountiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountiesRepository extends JpaRepository<CountiesEntity, Integer> {
}
