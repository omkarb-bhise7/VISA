package com.example.demo.Entity;


public class ExpenseReportDTO {
    private String expenseName;
    private Double totalAmount;

    // Constructor
    public ExpenseReportDTO(String expenseName, Double totalAmount) {
        this.expenseName = expenseName;
        this.totalAmount = totalAmount;
    }

    // Getters
    public String getExpenseName() {
        return expenseName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}

