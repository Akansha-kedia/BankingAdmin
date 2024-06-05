package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Customer;
import com.example.repo.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
    CustomerRepo cr;
	
	public List<Customer> read()
	{
		return cr.findAll();
		}
	
	public List<Customer> readByName(String name)
	{
		return cr.findByName(name);
	}
	public Optional<Customer> readOne(int id)
	{
		return cr.findById(id);
	}
	public void add(Customer tm)
	{
		cr.save(tm);
	}
	public void delete(int id)
	{
	       cr.deleteById(id);
	}
	public void update(int id,Customer CustomerNew)
	{
		Optional<Customer> CustomerOld=cr.findById(id);
        CustomerOld.get().setName(CustomerNew.getName());
        CustomerOld.get().setAddress(CustomerNew.getAddress());
        CustomerOld.get().setAge(CustomerNew.getAge());
        CustomerOld.get().setBalance(CustomerNew.getBalance());
        CustomerOld.get().setEmail(CustomerNew.getEmail());
        CustomerOld.get().setPassword(CustomerNew.getPassword());
        CustomerOld.get().setPhone(CustomerNew.getPhone());
        CustomerOld.get().setUsername(CustomerNew.getUsername());
        cr.save(CustomerOld.get());
	}
}
