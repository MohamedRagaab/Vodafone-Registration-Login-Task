package com.mohamed.task.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);

	boolean existsByUsernameOrEmail(String username, String email);
	
	Customer findByUsernameAndPassword(String username, String password);
}
