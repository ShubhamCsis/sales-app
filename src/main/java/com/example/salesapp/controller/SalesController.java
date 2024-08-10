package com.example.salesapp.controller;

import com.example.salesapp.model.SalesRecord;
import com.example.salesapp.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Controller class that handles HTTP requests related to sales records.
 * This class interacts with the SalesService to perform business logic and returns the appropriate view.
 */
@Controller
public class SalesController {

    @Autowired
    private SalesService salesService;

    /**
     * Handles requests to the root URL ("/") and displays the main sales management page.
     * This page includes a form for adding new sales records, a summary of sales, and a list of existing records.
     *
     * @param model the model object used to pass data to the view.
     * @return the name of the view template to be rendered ("index").
     */
    @GetMapping("/")
    public String index(Model model) {
        List<SalesRecord> salesRecords = salesService.getAllSalesRecords();
        model.addAttribute("salesRecords", salesRecords);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter);
        model.addAttribute("today", formattedDate);

        model.addAttribute("newRecord", new SalesRecord());

        Map<String, Map<String, Double>> salesSummary = salesService.getSalesSummary();
        model.addAttribute("salesSummary", salesSummary);

        return "index";
    }

    /**
     * Handles the submission of the sales record form, saving the new record to the database.
     * After saving, the user is redirected back to the main sales management page.
     *
     * @param salesRecord the SalesRecord object populated from the form data.
     * @param model the model object used to pass data to the view in case of an error.
     * @return a redirect to the root URL ("/") on success, or the error view on failure.
     */
    @PostMapping("/add")
    public String addSalesRecord(@ModelAttribute SalesRecord salesRecord, Model model) {
        try {
            if (salesRecord.getTransactionDate() == null) {
                salesRecord.setTransactionDate(LocalDate.now());
            }
            salesService.saveSalesRecord(salesRecord);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the sales record: " + e.getMessage());
            return "error";
        }
        return "redirect:/";
    }

    /**
     * Handles requests to edit an existing sales record. The record's details are loaded and displayed in a form.
     *
     * @param id the ID of the sales record to edit.
     * @param model the model object used to pass the sales record data to the view.
     * @return the name of the view template to be rendered ("edit").
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        SalesRecord salesRecord = salesService.getSalesRecordById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction ID: " + id));
        model.addAttribute("salesRecord", salesRecord);
        return "edit";
    }

    /**
     * Handles the submission of the edited sales record form, updating the record in the database.
     * After saving, the user is redirected back to the main sales management page.
     *
     * @param id the ID of the sales record being edited.
     * @param salesRecord the SalesRecord object populated from the form data.
     * @return a redirect to the root URL ("/") on success.
     */
    @PostMapping("/edit/{id}")
    public String editSalesRecord(@PathVariable("id") Long id, @ModelAttribute SalesRecord salesRecord) {
        salesRecord.setTransactionId(id);
        salesService.saveSalesRecord(salesRecord);
        return "redirect:/";
    }

    /**
     * Handles requests to delete a sales record by its ID.
     * After deletion, the user is redirected back to the main sales management page.
     *
     * @param id the ID of the sales record to delete.
     * @return a redirect to the root URL ("/") after the record is deleted.
     */
    @GetMapping("/delete/{id}")
    public String deleteSalesRecord(@PathVariable("id") Long id) {
        salesService.deleteSalesRecord(id);
        return "redirect:/";
    }
}
