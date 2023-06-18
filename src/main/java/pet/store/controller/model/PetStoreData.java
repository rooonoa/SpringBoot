package pet.store.controller.model;

import java.util.HashSet;


import java.util.Set;

import lombok.Data;

import lombok.NoArgsConstructor;

import pet.store.entity.Customer;

import pet.store.entity.Employee;

import pet.store.entity.PetStore;

@Data

@NoArgsConstructor

public class PetStoreData {

	private Long petStoreId;
	
	private String petStoreName;
	
	private String petStoreAddress;
	
	private String petStoreCity;
	
	private String petStoreState;
	
	private String petStoreZip;
	
	private String petStorePhone;
	
	private Set<PetStoreCustomer> customers = new HashSet<>();
	
	private Set<PetStoreEmployee> employees = new HashSet<>();




public PetStoreData(PetStore petStore) {

	this.petStoreId = petStore.getPetStoreId();
	
	this.petStoreName = petStore.getPetStoreName();
	
	this.petStoreAddress = petStore.getPetStoreAddress();
	
	this.petStoreCity = petStore.getPetStoreCity();
	
	this.petStoreState = petStore.getPetStoreState();
	
	this.petStoreZip = petStore.getPetStoreZip();
	
	this.petStorePhone = petStore.getPetStorePhone();

// Set customers

for (Customer customer : petStore.getCustomers()) {

PetStoreCustomer petStoreCustomer = new PetStoreCustomer(customer);

customers.add(petStoreCustomer);

}

// Set employees

	for (Employee employee : petStore.getEmployees()) {
	
	PetStoreEmployee petStoreEmployee = new PetStoreEmployee(employee);
	
	employees.add(petStoreEmployee);

}

}

@Data //class level annotations

@NoArgsConstructor

public static class PetStoreCustomer {
	
	//setting class constants 
	
	private Long customerId;
	
	private String customerFirstName;
	
	private String customerLastName;
	
	private String customerEmail;
	
	
	// creating the method PetStoreCustomer
public PetStoreCustomer(Customer customer) {
	
	this.customerId = customer.getCustomerId();
	
	this.customerFirstName = customer.getCustomerFirstName();
	
	this.customerLastName = customer.getCustomerLastName();
	
	this.customerEmail = customer.getCustomerEmail();
	
	}
	
	}

@Data //Class level annotations 

@NoArgsConstructor

//creating class constants 

public static class PetStoreEmployee {
		
	private Long employeeId;
		
	private String employeeFirstName;
		
	private String employeeLastName;
		
	private String employeePhone;
		
	private String employeeJobTitle;
	
	
	//Creating PetStoreEmployee method 
		
public PetStoreEmployee(Employee employee) {
		
		this.employeeId = employee.getEmployeeId();
		
		this.employeeFirstName = employee.getEmployeeFirstName();
		
		this.employeeLastName = employee.getEmployeeLastName();
		
		this.employeePhone = employee.getEmployeePhone();
		
		this.employeeJobTitle = employee.getEmployeeJobTitle();
		
		}
		
		}
		
		}