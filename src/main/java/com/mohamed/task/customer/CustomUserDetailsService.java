package com.mohamed.task.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
	private CustomerRepository customerRepository;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByUsername(username);
		if (customer != null)
		return new CustomUserDetails(customer);
		else 
		return new CustomUserDetails(new Customer());
	}

}
