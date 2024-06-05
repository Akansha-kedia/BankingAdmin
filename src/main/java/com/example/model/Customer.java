package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
	public class Customer
	{
		@Id //primary key
		@GeneratedValue(strategy=GenerationType.AUTO)
	    private int id;
		private String Name;
		private String Username;
		private String Password;
		private int Age;
		private String Address;
		private String Email;
		private String Phone;
		private float Balance;
	}
