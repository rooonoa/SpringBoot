package pet.store;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * this is our class with the main method that starts the Spring Boot that initialize 
 * the application, perform auto configuration and start an embedded web server to serve the 
 * application
 */

@SpringBootApplication  
public class PetStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreApplication.class, args); 
	


	}

}
