package com.example.salesapp.service;

import com.example.salesapp.model.SalesRecord;
import com.example.salesapp.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that provides business logic related to sales records.
 * This class interacts with the SalesRepository to perform CRUD operations and additional business logic.
 */
@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    /**
     * Saves a new or existing SalesRecord to the database.
     *
     * @param salesRecord the SalesRecord to save.
     * @return the saved SalesRecord.
     */
    public SalesRecord saveSalesRecord(SalesRecord salesRecord) {
        return salesRepository.save(salesRecord);
    }

    /**
     * Retrieves all sales records from the database.
     *
     * @return a list of all SalesRecord entities.
     */
    public List<SalesRecord> getAllSalesRecords() {
        return salesRepository.findAll();
    }

    /**
     * Retrieves a SalesRecord by its ID.
     *
     * @param id the ID of the SalesRecord to retrieve.
     * @return an Optional containing the found SalesRecord, or empty if not found.
     */
    public Optional<SalesRecord> getSalesRecordById(Long id) {
        return salesRepository.findById(id);
    }

    /**
     * Deletes a SalesRecord by its ID.
     *
     * @param id the ID of the SalesRecord to delete.
     */
    public void deleteSalesRecord(Long id) {
        salesRepository.deleteById(id);
    }

    /**
     * Generates a summary of sales, grouped by salesman and item type.
     *
     * @return a map where the key is the salesman's name, and the value is another map
     *         where the key is the item type and the value is the total sales amount.
     */
    public Map<String, Map<String, Double>> getSalesSummary() {
        List<SalesRecord> salesRecords = getAllSalesRecords();

        return salesRecords.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getSalesmanName,
                        Collectors.groupingBy(
                                SalesRecord::getItemType,
                                Collectors.summingDouble(SalesRecord::getSalesAmount)
                        )
                ));
    }
}
