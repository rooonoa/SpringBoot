package pet.store.entity;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.persistence.JoinColumn;


	/*
	* adding class level annotations 
	*/
	
	@Entity
	@Table(name = "pet_store")
	@Data
	
	public class PetStore {
		
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
		
		
		@ManyToMany(cascade = CascadeType.PERSIST)
		@JoinTable(
		name = "pet_store_customer",
		joinColumns = @JoinColumn(name = "pet_store_id"),
		inverseJoinColumns = @JoinColumn(name = "customer_id")
		)
		
		
		/*
		* adding the @EquqlsAndHashCode.Exclude and @ToString.Exclude to all the recursive
		* relationship variables to prevent recursion from occurring when the .toString(),
		* .equals(), or hashCode() methods are called
		*/
		
		
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private Set<Customer> customers;
		
		
		@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private Set<Employee> employees;
		
		
		}
