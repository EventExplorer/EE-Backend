package com.ee.repository;

import com.ee.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity,String> {

    @Query("SELECT u FROM UserAccountEntity u WHERE u.email = :email")
    public Optional<UserAccountEntity> findByUserEmail(@Param("email") String email);




}
