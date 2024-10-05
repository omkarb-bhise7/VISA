package com.example.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.EmailDetails;

@Repository
public interface EmailDetailsRepository extends JpaRepository<EmailDetails, Long> {
    
   // @Query("SELECT e.recipient FROM email_details e WHERE e.recipient LIKE %:query%")
	//@Query("SELECT e.recipient FROM email_details e WHERE LOWER(e.recipient) LIKE LOWER(CONCAT('%', :query, '%'))")
    //<String> findEmailSuggestions(@Param("query") String query);
	
	@Query("SELECT e.recipient FROM EmailDetails e WHERE e.recipient LIKE CONCAT('%', :query, '%')")
	List<String> findEmailSuggestions(@Param("query") String query);


}
