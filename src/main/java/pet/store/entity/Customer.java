	package pet.store.entity;
	
	
	import java.util.Set;

	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.ManyToMany;
	import jakarta.persistence.Table;
	import lombok.Data;
	import lombok.EqualsAndHashCode;
	import lombok.ToString;
	
	
	/*
	* adding @entity, @Data and @Table class levels annotation 
	*/
	@Entity //indicates class instances will be mapped onto a database table 
	@Table(name = "customer") //the class will be mapped to the database table named "customer"
	@Data //this automatically generates standard methods such as getters and setters for the fields in the class
	
	
	public class Customer {
		
		
	/*
	* adding instance variables and annotation and relationship variables
	*/
		
		@Id //indicates that the field 'id' is the primary key for the entity
		@GeneratedValue(strategy = GenerationType.IDENTITY) //
		private Long customerId; // This field declares a field name id and will serve as the PK for the customer entity 
		
		@Column(name = "customer_email") //Indicating that the field 'customerEmail' should be mapped to a column named "customer_email"
		private String customerEmail;
		
		private String customerFirstName;
		private String customerLastName;
		
		
		/*
		 * defining a many to many relationship between the customer entity and 
		 * the PetStore entity. The relationship is mapped through the customer 
		 * property in the PetStore entity 
		 */
		
		@ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private Set<PetStore> petStores;


		public void setPetStore(PetStore petStore) {
			// TODO Auto-generated method stub
			
		}


		
			
		}
		
		
		
	