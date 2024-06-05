package com.example.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.model.Customer;
import com.example.repo.CustomerRepo;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService cs;
	
	@MockBean
	private CustomerRepo cr;
	
	@InjectMocks
	private CustomerController cc;
	
	private Customer cust1;
	private Customer cust2;
	
	@BeforeEach
	void setUp()
	{
		cust1=new Customer();
		cust1.setId(1);
		cust1.setName("Customer A");
		cust1.setUsername("Customer Username A");
		cust1.setPassword("Password A");
		cust1.setAge(20);
		cust1.setAddress("Bangalore");
		cust1.setEmail("CustomerA@gmail.com");
		cust1.setPhone("9000000000");
		cust1.setBalance(20000);
		
		
		cust2=new Customer();
		cust2.setId(2);
		cust2.setName("Customer B");
		cust2.setUsername("Customer Username B");
		cust2.setPassword("Password B");
		cust2.setAge(20);
		cust2.setAddress("Mumbai");
		cust2.setEmail("CustomerB@gmail.com");
		cust2.setPhone("8000000000");
		cust2.setBalance(30000);
		
		
	}
	
	@Test
	void testRead() throws Exception {
		List<Customer> Customers = Arrays.asList(cust1, cust2);
		when(cs.read()).thenReturn(Customers);
		
		mockMvc.perform(get("/cust/read"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name").value("Customer A"))
		.andExpect(jsonPath("$[1].name").value("Customer B"));
		
	}
	
	
//	@Test
//	void testreadByName() throws Exception {
//		List<Customer> Customers = Arrays.asList(cust1);
//		when(cr.findByName("Customer A")).thenReturn(Customers);
//		
//		mockMvc.perform(get("/cust/readname/Customer A"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$[0].name").value("Customer A"));
//		
//	}
	
	@Test
	void testreadOne() throws Exception {
		when(cs.readOne(1)).thenReturn(Optional.of(cust1));
		
		mockMvc.perform(get("/cust/readOne/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Customer A"));
	}
	
	@Test
	void testAdd() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String CustomerJson = objectMapper.writeValueAsString(cust1);
		
		mockMvc.perform(post("/cust/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(CustomerJson))
				.andExpect(status().isOk());
		verify(cs).add(cust1);
	 }
	
	@Test
	void testUpdate() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String CustomerJson = objectMapper.writeValueAsString(cust1);
		
		mockMvc.perform(put("/cust/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(CustomerJson))
				.andExpect(status().isOk());
		
		verify(cs).update(1, cust1);
	}
	
	@Test
	void testDelete() throws Exception {
		mockMvc.perform(delete("/cust/delete/1"))
		.andExpect(status().isOk());
		
		verify(cs).delete(1);
		
	}
 
}