package com.ee.repository;

import com.ee.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDetailsEntity, String> {
}
