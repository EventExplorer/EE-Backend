package com.ee.repository;

import com.ee.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
}
