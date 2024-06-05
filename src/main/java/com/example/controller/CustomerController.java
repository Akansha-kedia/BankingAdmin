package com.example.controller;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;
import com.example.repo.CustomerRepo;
import com.example.service.CustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cust")
public class CustomerController {

	@Autowired
	CustomerService cs;
	 
	@GetMapping("/read")
	public List<Customer> read()
	{
		//return cs.findAll();
		return cs.read();
		}
	@GetMapping("/readname/{name}")
	public List<Customer> readByName(@PathVariable String name)
	{
		return cs.readByName(name);
	}
	
	@GetMapping("/readOne/{id}")
	public Optional<Customer> readOne(@PathVariable int id)
	{
		return cs.readOne(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Customer cust)
	{
		cs.add(cust);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id)
	{
	       cs.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable int id, @RequestBody Customer CustomerNew)
	{
		cs.update(id, CustomerNew);
	}

}