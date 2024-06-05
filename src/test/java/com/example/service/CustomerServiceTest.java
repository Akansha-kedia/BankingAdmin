package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.model.Customer;
import com.example.repo.CustomerRepo;
import com.example.service.CustomerService;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepo CustomerRepo;
	
	@InjectMocks
	private CustomerService CustomerService;
	
	private Customer cust1;
	private Customer cust2;
	
	@BeforeEach
	void setUp()
	{
		cust1=new Customer();
		cust1.setId(1);
		cust1.setName("Customer A");
		cust1.setUsername("Customer Username A");
		cust1.setAge(20);
		cust1.setAddress("Bangalore");
		cust1.setEmail("CustomerA@gmail.com");
		cust1.setPhone("9000000000");
		cust1.setBalance(20000);
		
		
		cust2=new Customer();
		cust2.setId(2);
		cust2.setName("Customer B");
		cust2.setUsername("Customer Username B");
		cust2.setAge(20);
		cust2.setAddress("Mumbai");
		cust2.setEmail("CustomerB@gmail.com");
		cust2.setPhone("8000000000");
		cust2.setBalance(30000);
		
		
	}
	
	@Test
	void testRead() {
		List<Customer> Customers=Arrays.asList(cust1,cust2);
		when(CustomerRepo.findAll()).thenReturn(Customers);
		
		List<Customer> result=CustomerService.read();
		assertEquals(2,result.size());
		assertEquals("Customer A",result.get(0).getName());
		assertEquals("Customer B",result.get(1).getName());
		
	}
	
	@Test
	void testReadOne() {
		when(CustomerRepo.findById(1)).thenReturn(Optional.of(cust1));
		Optional<Customer> result=CustomerService.readOne(1);
		assertTrue(result.isPresent());
		assertEquals("Customer A", result.get().getName());
	}
	
	@Test
	void testAdd()
	{
		CustomerService.add(cust1);
		verify(CustomerRepo).save(cust1);
	}
	
	@Test
	void testUpdate() {
		Customer updatedCustomer=new Customer();
		updatedCustomer.setName("Updated Customer A");
		updatedCustomer.setUsername("Updated Username A");
		updatedCustomer.setAge(22);
		updatedCustomer.setAddress("Updated Address");
		updatedCustomer.setEmail("Updated Email");
		updatedCustomer.setPhone("Updated 9000000000");
		
		when(CustomerRepo.findById(1)).thenReturn(Optional.of(cust1));
		CustomerService.update(1,updatedCustomer);
		assertEquals("Updated Customer A",cust1.getName());
		assertEquals("Updated Username A",cust1.getUsername());
		assertEquals(22,cust1.getAge());
		assertEquals("Updated Address",cust1.getAddress());
		assertEquals("Updated Email",cust1.getEmail());
		verify(CustomerRepo).save(cust1);
	}
	
	@Test
	void testDelete() {
		CustomerService.delete(1);
		verify(CustomerRepo).deleteById(1);
	}
}
