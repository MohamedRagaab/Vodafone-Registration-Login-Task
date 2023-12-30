package com.mohamed.task.customer;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class CustomerController {
	
    private CustomerService customerService;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
	@GetMapping("/")
	public String showHomePage() {
	    return "home";
	}
    
	@GetMapping("/login")
	public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
	    model.addAttribute("customer", new Customer());
	    return "login";
	}
	
	
	@GetMapping("/logout")
	public String showLogoutPage() {
	    return "redirect:/login";
	}
	
	@PostMapping("/logout")
	public String logout() {
	    return "redirect:/login";
	}
	

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("customer", new Customer());
	    return "registration";
	}
    
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
    	ApiResponse response = customerService.registerCustomer(customer);
    	if (response.isSuccess()) {
            return "redirect:/login";
    	} else {
    		model.addAttribute("errorMessage", response.getMessage());
    		return "registration";
    	}
    }
}
