package com.mohamed.task.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse registerCustomer(Customer customer) {
    	try {
            if (customerRepository.existsByUsernameOrEmail(customer.getUsername(), customer.getEmail())) {
            	return new ApiResponse("Customer with the same email or username already exists", false);
            }

            // Encode the password before saving
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            
            return new ApiResponse("Customer is saved successfully", true);
    	} catch (Exception e) {
            throw new RegistrationException("Something Went wrong!");
    	}
    }

}
