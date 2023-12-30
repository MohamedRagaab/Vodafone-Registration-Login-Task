package com.mohamed.task.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_USERNAME", columnNames = "username"),
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "email")
})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false, unique = true)
	private String username;
    
    @NotEmpty
	private String password;
	
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
	private String email;
    
    @NotEmpty
	private String phone;
    
    @NotEmpty
    @Column(name = "first_name")
	private String firstName;
    
    @NotEmpty
    @Column(name = "last_name")
	private String lastName;
	
	public Customer() {}
	
	public Customer(Long id,
			String username,
			String password,
			String email,
			String phone,
			String firstName,
			String lastName
			) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(String username,
			String password,
			String email,
			String phone,
			String firstName,
			String lastName
			) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Customer {"
				+ " id = " + id +
				" username = " + username +
				" email = " + email +
				" phone = " + phone +
				" firstName " + firstName +
				" lastName = " + lastName +
				" }";
	}
}
