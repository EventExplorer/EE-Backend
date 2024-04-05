package com.ee.repository;

import com.ee.entities.BillingInfoEntity;
import com.ee.entities.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<BillingInfoEntity, String> {

    @Query("SELECT u FROM BillingInfoEntity u WHERE u.email = :email")
    public List<BillingInfoEntity> findByUserEmail(@Param("email") String email);

   @Query("SELECT u FROM BillingInfoEntity u WHERE u.email = :email")
    public Optional<BillingInfoEntity> findCreditCardByUserEmail(@Param("email") String email);

    @Query("SELECT u FROM BillingInfoEntity u WHERE u.email = :email AND u.bankName = :bankName")
    public Optional<BillingInfoEntity> findByUserEmailAndBankName(@Param("email") String email, @Param("bankName") String bankName);

    @Query("SELECT u.billingUuid FROM BillingInfoEntity u WHERE u.email = :email AND u.bankName = :bankName")
    public String findBillingUuidByEmailAndBankName(@Param("email") String email, @Param("bankName") String bankName);
}
