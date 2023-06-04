package pet.store.entity;

	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;
	import lombok.Data;
	
	
	/*
	* Adding class level annotations 
	*/
	
	@Entity
	@Table(name = "employee")
	@Data
	
	public class Employee {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long employeeId;
		
		@Column(name = "employee_email")
		private String employeeEmail;
		
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "pet_store_id")
		private PetStore petStore;
		
		
	}
