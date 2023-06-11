package pet.store.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor

public class PetStoreEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(name = "employee_email")
	private String employeeEmail;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	
	/*
	  * Adding a constructor that takes a employee as the parameter and then setting all matching 
	  * fields in the PetStoreEmployee class to the data in the PetStore Class
	  */
	
	public PetStoreEmployee(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeFirstName = employee.getEmployeeFirstName();
        this.employeeLastName = employee.getEmployeeLastName();
        this.employeePhone = employee.getEmployeePhone();
        this.employeeJobTitle = employee.getEmployeeJobTitle();
    }

}
