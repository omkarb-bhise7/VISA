package com.example.demo.Repository;

import com.example.demo.Entity.Expense;
import com.example.demo.Entity.ExpenseReportDTO;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	  // Query to find expenses by name and date range
	//!R. Custom query to filter expenses by name and date range
	  @Query("SELECT e FROM Expense e WHERE e.name LIKE %?1% AND e.date BETWEEN ?2 AND ?3")
	  List<Expense> findByNameAndDateRange(String expenseName, LocalDate startDate, LocalDate endDate);
	  
	//2R.
		    @Query("SELECT e.name, e.amount FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
		    List<Object[]> findExpensesBetweenDates(LocalDate startDate, LocalDate endDate);
	//3R.

			// Custom query to get unique expense names and total amount
			@Query("SELECT new com.example.demo.Entity.ExpenseReportDTO(e.name, SUM(e.amount)) " +
		           "FROM Expense e GROUP BY e.name")
			List<ExpenseReportDTO> findTotalAmountByExpenseName();
		    


}


		
	



