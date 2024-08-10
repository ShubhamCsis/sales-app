package com.example.salesapp.service;

import com.example.salesapp.model.SalesRecord;
import com.example.salesapp.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit test class for SalesService using JUnit and Mockito.
 * This class tests the functionality of the SalesService class,
 * focusing on displaying, retrieving, and deleting records.
 */
@ExtendWith(MockitoExtension.class)
public class SalesServiceTest {

    @InjectMocks
    private SalesService salesService;

    @Mock
    private SalesRepository salesRepository;

    private SalesRecord record1;
    private SalesRecord record2;

    /**
     * Initializes test data before each test method is executed.
     * Two SalesRecord objects are created to simulate database records.
     */
    @BeforeEach
    void setUp() {
        record1 = new SalesRecord();
        record1.setTransactionId(1L);
        record1.setSalesmanName("John Doe");
        record1.setItemType("Washing Machine");
        record1.setSalesAmount(1500.0);
        record1.setTransactionDate(LocalDate.of(2024, 8, 1));

        record2 = new SalesRecord();
        record2.setTransactionId(2L);
        record2.setSalesmanName("Jane Doe");
        record2.setItemType("Refrigerator");
        record2.setSalesAmount(2500.0);
        record2.setTransactionDate(LocalDate.of(2024, 8, 2));
    }

    /**
     * Tests the retrieval of all sales records.
     * Ensures that the service correctly interacts with the repository
     * and returns the expected list of records.
     */
    @Test
    void testGetAllSalesRecords() {
        // Arrange: Mock the findAll method of the repository to return predefined records.
        when(salesRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Act: Call the service method to get all sales records.
        List<SalesRecord> result = salesService.getAllSalesRecords();

        // Assert: Verify the size and content of the returned list.
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getSalesmanName());
        assertEquals("Jane Doe", result.get(1).getSalesmanName());
        verify(salesRepository, times(1)).findAll();
    }

    /**
     * Tests the deletion of a sales record by ID.
     * Ensures that the service correctly interacts with the repository
     * to delete a record by its ID.
     */
    @Test
    void testDeleteSalesRecord() {
        // Arrange: Mock the deleteById method of the repository.
        doNothing().when(salesRepository).deleteById(anyLong());

        // Act: Call the service method to delete a sales record by ID.
        salesService.deleteSalesRecord(1L);

        // Assert: Verify that the repository's deleteById method was called with the correct ID.
        verify(salesRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests the retrieval of a single sales record by ID.
     * Ensures that the service correctly retrieves the record from the repository.
     */
    @Test
    void testGetSalesRecordById() {
        // Arrange: Mock the findById method of the repository to return a specific record.
        when(salesRepository.findById(1L)).thenReturn(Optional.of(record1));

        // Act: Call the service method to retrieve the record by ID.
        Optional<SalesRecord> result = salesService.getSalesRecordById(1L);

        // Assert: Verify that the returned record is present and contains the expected data.
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getSalesmanName());
        verify(salesRepository, times(1)).findById(1L);
    }
}
