package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{
	//@Query(value="select * from team t where t.name=?",nativeQuery=true)
	@Query(value="select t from Customer t where t.Name=?1")
	public List<Customer> findByName(String Name);

	

}
