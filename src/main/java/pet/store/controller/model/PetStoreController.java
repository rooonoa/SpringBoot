package pet.store.controller.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;
import pet.store.service.PetStoreService;

@RestController // Tells spring that this class is a REST controller hence 
// expects and returns JSON in the request/response bodies and it also tells Spring 
// to map HTTP request to class method

@RequestMapping("/pet_store")// this tells Spring that URI for every HTTP request is mapped
// to a method in this controller class must start with "/pet_store"

@Slf4j //this Lombok annotation creates an SLF4J logger. It adds the logger as a instance variable named log 

public class PetStoreController {
	
	@Autowired //this make Spring to inject the service object into the variable
	private PetStoreService petStoreService;
	
	/*
	 * created a public method that maps an HTTP POST request to "/pet_store".  This method
	 * return a PetStoreData object, log the request, call a metod in service class (savePetStore)
	 * that will insert or modify the pet store data 
	 */
	
	 @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
	        log.info("Received request to create PetStore: {}", petStoreData);
	        // Call the savePetStore method in the PetStoreService class
	        // to insert or modify the pet store data
	        PetStoreData savedPetStoreData = petStoreService.savePetStore(petStoreData);
	        return savedPetStoreData;
	}
	 
	 /*
	  * we have the @PutMapping annotation to map the PUT request to this method and 
	  * specify that a pet store ID is present in the in the HTTP request URI.The method 
	  * parameter are the pet Store ID and the pet store data. The method return
	  * petStoreData
	  */

	 @PutMapping("/{petStoreId}")
	 public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		 petStoreData.setPetStoreId(petStoreId);
	        System.out.println("Updating pet store with ID: " + petStoreId);
	        return petStoreService.savePetStore(petStoreData);
	 
} 
}










