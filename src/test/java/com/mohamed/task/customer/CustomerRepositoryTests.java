package com.mohamed.task.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer(
				"MohamedTest",
				"1234@test",
				"mrfa@test.com",
				"+201011111111",
				"Mohamed",
				"Test"
				);
		
		Customer savedCustomer = customerRepository.save(customer);
		
		Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());
		
		assertThat(existCustomer.getUsername()).isEqualTo(customer.getUsername());
	}
	
	@Test
	public void testCreateCustomerWithDuplicateUsernameOrEmail() {
		Customer customer1 = new Customer(
				"MohamedTest",
				"1234@test",
				"mrfa@test.com",
				"+201011111111",
				"Mohamed",
				"Test"
				);

		customerRepository.save(customer1);

		Customer customer2 = new Customer(
				"MohamedTest",
				"5678@test",
				"mrfa2@test.com",
				"+201022222222",
				"Mohamed",
				"Test"
				);


	    DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
	    	customerRepository.saveAndFlush(customer2);
	    });

	    assertThat(exception.getMessage()).contains("Unique index or primary key violation");
	}
	
    @Test
    public void testFindByUsername() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );
        customerRepository.save(customer);

        Customer foundCustomer = customerRepository.findByUsername("MohamedTest");

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getUsername()).isEqualTo("MohamedTest");
    }
    
    @Test
    public void testExistsByUsernameOrEmail() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );
        customerRepository.save(customer);

        boolean exists = customerRepository.existsByUsernameOrEmail("MohamedTest", "mrfa@test.com");
        assertTrue(exists);

        exists = customerRepository.existsByUsernameOrEmail("NonExistingUser", "nonexisting@test.com");
        assertFalse(exists);
    }
    
    @Test
    public void testFindByUsernameAndPassword() {
        Customer customer = new Customer(
                "MohamedTest",
                "1234@test",
                "mrfa@test.com",
                "+201011111111",
                "Mohamed",
                "Test"
        );
        customerRepository.save(customer);

        Customer foundCustomer = customerRepository.findByUsernameAndPassword("MohamedTest", "1234@test");

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getUsername()).isEqualTo("MohamedTest");
    }

}
