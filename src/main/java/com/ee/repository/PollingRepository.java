package com.ee.repository;

import com.ee.entities.BillingInfoEntity;
import com.ee.entities.PollingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PollingRepository extends JpaRepository<PollingEntity, Integer> {

    /*
    @Query("SELECT p.categoryId, p.artistName, COUNT(p.votes) AS count " +
            "FROM PollingEntity p " +
            "GROUP BY p.categoryId, p.artistName")
    List<Object[]> findDistinctCategoryStateArtistCount();
    */



    @Query("SELECT p.artistName, COUNT(p.votes) AS totalVotes FROM PollingEntity p WHERE p.categoryId = :categoryId GROUP BY p.artistName")
    List<Object[]> totalVotesByCategoryId(@Param("categoryId") String categoryId);

    @Query("SELECT u FROM PollingEntity u WHERE u.email = :email")
    public Optional<PollingEntity> findByUserEmailAndaAndArtistName(@Param("email") String email);

}


