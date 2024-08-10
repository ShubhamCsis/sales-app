package com.example.salesapp.repository;

import com.example.salesapp.model.SalesRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing sales records from the database.
 * Extends JpaRepository to provide CRUD operations on SalesRecord entities.
 */
public interface SalesRepository extends JpaRepository<SalesRecord, Long> {

    /**
     * Finds a SalesRecord by its ID.
     *
     * @param id the ID of the SalesRecord to find.
     * @return an Optional containing the found SalesRecord, or empty if not found.
     */
    Optional<SalesRecord> findById(Long id);
}
