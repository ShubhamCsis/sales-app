package com.example.salesapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

/**
 * Entity class representing a sales record in the database.
 * This class is annotated with @Entity, indicating that it is a JPA entity.
 */
@Entity
public class SalesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private String salesmanName;
    private String itemType;
    private double salesAmount;
    private LocalDate transactionDate;

    // Getters and setters

    /**
     * Gets the transaction ID of the sales record.
     *
     * @return the transaction ID.
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID of the sales record.
     *
     * @param transactionId the transaction ID to set.
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the name of the salesman associated with this sales record.
     *
     * @return the salesman's name.
     */
    public String getSalesmanName() {
        return salesmanName;
    }

    /**
     * Sets the name of the salesman associated with this sales record.
     *
     * @param salesmanName the salesman's name to set.
     */
    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    /**
     * Gets the type of item sold in this sales record.
     *
     * @return the item type.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the type of item sold in this sales record.
     *
     * @param itemType the item type to set.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the sales amount for this sales record.
     *
     * @return the sales amount.
     */
    public double getSalesAmount() {
        return salesAmount;
    }

    /**
     * Sets the sales amount for this sales record.
     *
     * @param salesAmount the sales amount to set.
     */
    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    /**
     * Gets the transaction date for this sales record.
     *
     * @return the transaction date.
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date for this sales record.
     *
     * @param transactionDate the transaction date to set.
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
