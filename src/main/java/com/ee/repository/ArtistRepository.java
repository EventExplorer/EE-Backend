package com.ee.repository;

import com.ee.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {
    @Query("SELECT u FROM ArtistEntity u WHERE u.categoryId = :categoryId")
    public Optional<List<ArtistEntity>> findArtistById(@Param("categoryId") String categoryId);

    @Query("SELECT u FROM ArtistEntity u WHERE u.name = :name")
    public Optional<ArtistEntity> findArtistByName(@Param("name") String name);

    @Query("SELECT u FROM ArtistEntity u WHERE u.categoryId = :categoryId")
    public List<ArtistEntity> findArtistByCategoryId(@Param("categoryId") String categoryId);

}
