package com.example.demo.Service;




import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Expense;
import com.example.demo.Entity.ExpenseReportDTO;
import com.example.demo.Repository.ExpenseRepository;
@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;
	
	 //1R. Method to filter expenses by name and date range
	   public List<Expense> filterExpenses(String expenseName, LocalDate startDate, LocalDate endDate) {
	       return expenseRepository.findByNameAndDateRange(expenseName, startDate, endDate);
	   }
	   
	 //2R.
	   public List<Map<String, Object>> getExpensesBetweenDates(String startDate, String endDate) {
		    try {
		        // Trim the date strings to remove any extra whitespace or newlines
		        LocalDate start = LocalDate.parse(startDate.trim());
		        LocalDate end = LocalDate.parse(endDate.trim());

		        List<Object[]> results = expenseRepository.findExpensesBetweenDates(start, end);
		        List<Map<String, Object>> expenses = new ArrayList<>();

		        for (Object[] result : results) {
		            Map<String, Object> expense = new HashMap<>();
		            expense.put("name", result[0]);  // Expense name
		            expense.put("amount", result[1]);  // Expense amount
		            expenses.add(expense);
		        }

		        return expenses;
		    } catch (DateTimeParseException e) {
		        throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
		    }
		}
	   public List<ExpenseReportDTO> getTotalAmountByExpenseName() {
	        return expenseRepository.findTotalAmountByExpenseName();
	    }


}

