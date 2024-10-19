package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expense")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "master_expense_id")
    private Integer masterExpenseId;

    // Default constructor
    public Expense() {}

    // Constructor with parameters
    public Expense(String name, Double amount, LocalDate date, Integer masterExpenseId) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.masterExpenseId = masterExpenseId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Integer getMasterExpenseId() { return masterExpenseId; }
    public void setMasterExpenseId(Integer masterExpenseId) { this.masterExpenseId = masterExpenseId; }
}

