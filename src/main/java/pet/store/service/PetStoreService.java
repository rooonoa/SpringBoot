package pet.store.service;

import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service

public class PetStoreService {
	
	@Autowired //this make Spring to inject the DAO object into the variable
	private PetStoreDao petStoreDao;
	

	public PetStoreData savePetStore(PetStoreData petStoreData) {
		
		 Long petStoreId = petStoreData.getPetStoreId();
	        PetStore petStore;

	        if (petStoreId == null) {
	            petStore = findOrCreatePetStore(petStoreId);
	        } else {
	            petStore = findPetStoreById(petStoreId);
	        }

	        if (petStore == null) {
	            throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
	        }

	        copyPetStoreFields(petStore, petStoreData);

	        PetStore savedPetStore = petStoreDao.save(petStore);

	        return createPetStoreData(savedPetStore);
	    }

	   private PetStoreData createPetStoreData(PetStore savedPetStore) {
	        return new PetStoreData(savedPetStore);
	    }

	    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
	        petStore.setPetStoreName(petStoreData.getPetStoreName());
	        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	        petStore.setPetStoreState(petStoreData.getPetStoreState());
	        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	    }

	    private PetStore findPetStoreById(Long petStoreId) {
	        return petStoreDao.findById(petStoreId).orElse(null);
	    }

	    private PetStore findOrCreatePetStore(Long petStoreId) {
	        if (petStoreId != null) {
	            return findPetStoreById(petStoreId);
	        } else {
	            return new PetStore();
	        }
	    }
	}
