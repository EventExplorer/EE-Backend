package com.ee.repository;

import com.ee.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT c.id FROM CategoryEntity c WHERE c.name = :name")
    Optional<CategoryEntity> findIdByName(@Param("name") String name);


    @Query("SELECT c.id FROM CategoryEntity c WHERE c.name = :name")
    Integer findIdValueByName(@Param("name") String name);


}
