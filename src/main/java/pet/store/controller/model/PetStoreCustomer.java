package pet.store.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;

@Data
@NoArgsConstructor

public class PetStoreCustomer {
	
	@Id //indicates that the field 'id' is the primary key for the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) //
	private Long customerId; // This field declares a field name id and will serve as the PK for the customer entity 
	
	@Column(name = "customer_email") //Indicating that the field 'customerEmail' should be mapped to a column named "customer_email"
	private String customerEmail;
	
	private String customerFirstName;
	private String customerLastName;
	
	
	/*
	  * Adding a constructor that takes a customer as the parameter and then setting all matching 
	  * fields in the PetStoreCustomer class to the data in the PetStoreCustomer Class
	  */
	
	
    public PetStoreCustomer(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerFirstName = customer.getCustomerFirstName();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();
	
	
    }
}
