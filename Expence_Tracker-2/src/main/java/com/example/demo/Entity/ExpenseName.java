
package com.example.demo.Entity;



import jakarta.persistence.*;

@Entity
@Table(name = "master_expense")
public class ExpenseName {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Default constructor
    public ExpenseName() {}

//    // Constructor with parameters
    public ExpenseName(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setid(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }




}
