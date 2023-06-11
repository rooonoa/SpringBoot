package pet.store.controller.model;

import java.util.List;

import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor

public class PetStoreData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	
	@Column(name = "petstore_name")
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	/*
	 * changing the data type of the customers field to PetStoreCustomer and the employees
	 * field to PetStoreEmployee
	 */
	private List<PetStoreCustomer> customers;
	private List<PetStoreEmployee> employees;
	
	 /*
	  * Adding a constructor that takes a PetStore as the parameter and then setting all matching 
	  * fields in the PetStoreData class to the data in the PetStoreClass
	  */
	
	
	public PetStoreData (PetStore petStore) {
		
		this.petStoreId = petStore.getPetStoreId();
		this.petStoreName = petStore.getPetStoreName();
		this.petStoreAddress = petStore.getPetStoreAddress();
		this.petStoreCity = petStore.getPetStoreCity();
		this.petStoreState = petStore.getPetStoreState();
		this.petStoreZip = petStore.getPetStoreZip();
		this.petStorePhone = petStore.getPetStorePhone();
		
	
		
		
	}


	 public String getName() {
	        return petStoreName;
	    }
	 public String getLocation() {
	        return petStoreCity + ", " + petStoreState + " " + petStoreZip;
	    }
	
	
	}


