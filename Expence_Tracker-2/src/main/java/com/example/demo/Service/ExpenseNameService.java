package com.example.demo.Service;

import com.example.demo.Entity.ExpenseName;
import com.example.demo.Repository.ExpenseNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseNameService {

    @Autowired
    private ExpenseNameRepository expenseNameRepository;

    public List<ExpenseName> getAllExpenseNames() {
        return expenseNameRepository.findAll(); // Fetch all expense names from the repository
    }
}
