package pet.store.controller.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;



@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

    private final PetStoreService petStoreService;

    @Autowired
    public PetStoreController(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }
   
    @GetMapping
    public List<PetStoreData> retrieveAllPetStores() {
        return petStoreService.retrieveAllPetStores();
    }
   
    @GetMapping("/{petStoreId}")
    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
        return petStoreService.retrievePetStoreById(petStoreId);
    }
   
    @DeleteMapping("/{storeId}")
    public Map<String, String> deletePetStoreById(@PathVariable Long storeId) {
        log.info("Received request to delete PetStore with ID: {}", storeId);
        petStoreService.deletePetStoreById(storeId);
       
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet store deleted successfully");
        return response;
    }

    // Existing method to create a new pet store
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Received request to create PetStore: {}", petStoreData);
        PetStoreData savedPetStoreData = petStoreService.savePetStore(petStoreData);
        return savedPetStoreData;
    }

    // New method to update an existing pet store
    @PutMapping("/{storeId}")
    public PetStoreData updatePetStoreData(
            @PathVariable Long storeId,
            @RequestBody PetStoreData petStoreData
    ) {
        log.info("Received request to update PetStore with ID {}: {}", storeId, petStoreData);
        petStoreData.setPetStoreId(storeId);
        PetStoreData updatedPetStoreData = petStoreService.savePetStore(petStoreData);
        return updatedPetStoreData;
    }

    // New method that allows an employee to be added to a pet store
    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreEmployee employee
    ) {
        log.info("Received request to add employee to PetStore with ID {}: {}", petStoreId, employee);
        PetStoreEmployee savedEmployee = petStoreService.saveEmployee(petStoreId, employee);
        return savedEmployee;
    }

    // New method that allows a customer to be added to a pet store
    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreCustomer customer
    ) {
        log.info("Received request to add customer to PetStore with ID {}: {}", petStoreId, customer);
        petStoreService.addCustomerToPetStore(petStoreId, customer);
        return customer;
    }
}
