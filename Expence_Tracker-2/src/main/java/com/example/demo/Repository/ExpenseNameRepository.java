package com.example.demo.Repository; 

import com.example.demo.Entity.ExpenseName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseNameRepository extends JpaRepository<ExpenseName, Integer> {
    // Query to fetch ExpenseName data
    @Query("SELECT new com.example.demo.Entity.ExpenseName(e.name) FROM ExpenseName e")
    List<ExpenseName> findAllExpenseDTOs();
}
