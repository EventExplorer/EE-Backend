package com.ee.repository;

import com.ee.entities.AccountVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountVerificationRepository extends JpaRepository<AccountVerificationEntity, String> {
}
