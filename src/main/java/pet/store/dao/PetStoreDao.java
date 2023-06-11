package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.PetStore;

/*
 * creating a new interface named PetStoreDao and extends JpaRepository
 * <PetStore, long>
 */

public interface PetStoreDao extends JpaRepository <PetStore, Long> {

}
