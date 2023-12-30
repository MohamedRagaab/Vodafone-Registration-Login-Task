package com.mohamed.task.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testRegisterCustomer_Success() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );

        when(customerRepository.existsByUsernameOrEmail(any(), any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(customerRepository.save(any())).thenReturn(customer);

        ApiResponse response = customerService.registerCustomer(customer);

        assertThat(response.getMessage()).isEqualTo("Customer is saved successfully");
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void testRegisterCustomer_CustomerExists() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );

        when(customerRepository.existsByUsernameOrEmail(any(), any())).thenReturn(true);

        ApiResponse response = customerService.registerCustomer(customer);

        assertThat(response.getMessage()).isEqualTo("Customer with the same email or username already exists");
        assertThat(response.isSuccess()).isFalse();
    }

    @Test
    public void testRegisterCustomer_Exception() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );

        when(customerRepository.existsByUsernameOrEmail(any(), any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(customerRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(RegistrationException.class, () -> {
            customerService.registerCustomer(customer);
        });
    }
}
