package com.example.demo.Controller;

import com.example.demo.Entity.Expense;
import com.example.demo.Entity.ExpenseName;
import com.example.demo.Entity.ExpenseReportDTO;
import com.example.demo.Repository.ExpenseRepository;
import com.example.demo.Service.ExpenseNameService;
import com.example.demo.Service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseNameService expenseNameService;
    
    @Autowired
    private ExpenseService expenseService;
    

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping("/names")
    public List<ExpenseName> getAllExpenseDTOs() {
        return expenseNameService.getAllExpenseNames();
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

 
//http://localhost:8080/api/expenses/filter?expenseName=Rent&startDate=2024-10-10&endDate=2024-10-15
// Endpoint to filter expenses based on name and date range
@GetMapping("/filter")
public ResponseEntity<List<Expense>> filterExpenses(
      @RequestParam String expenseName,
      @RequestParam String startDate,
      @RequestParam String endDate) {
  
  try {
      // Parse the date strings into LocalDate objects
      LocalDate from = LocalDate.parse(startDate);
      LocalDate to = LocalDate.parse(endDate);
      
      // Call the service method to filter expenses
      List<Expense> expenses = expenseService.filterExpenses(expenseName, from, to);
      return ResponseEntity.ok(expenses);
  } catch (DateTimeParseException e) {
      return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if date parsing fails
  } catch (Exception e) {
      e.printStackTrace(); // Log the error for debugging
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 Internal Server Error
  }
}

@GetMapping("/expensesBetweenDates")
public ResponseEntity<?> getExpensesBetweenDates(@RequestParam String startDate, @RequestParam String endDate) {
    try {
        List<Map<String, Object>> expenses = expenseService.getExpensesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(expenses);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());  // Bad Request for invalid input
    } catch (Exception e) {
        e.printStackTrace();  // Print exception details
        return ResponseEntity.status(500).body("Error fetching expenses");
    }
}
       //3R. End_point to get the expense report
@GetMapping("/total")
public List<ExpenseReportDTO> getTotalAmountByExpenseName() {
    return expenseService.getTotalAmountByExpenseName();
}


}

    


