package pet.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

    private final PetStoreDao petStoreDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao, EmployeeDao employeeDao, CustomerDao customerDao) {
        this.petStoreDao = petStoreDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore;

        if (petStoreId != null) {
            petStore = findPetStoreById(petStoreId);
            if (petStore == null) {
                throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
            }
        } else {
            petStore = createNewPetStore();
        }

        copyPetStoreFields(petStore, petStoreData);

        PetStore savedPetStore = petStoreDao.save(petStore);

        return new PetStoreData(savedPetStore);
    }

    private PetStore createNewPetStore() {
        PetStore newPetStore = new PetStore();
        petStoreDao.save(newPetStore); // Save the new pet store to generate an ID
        return newPetStore;
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
        Optional<PetStore> optionalPetStore = petStoreDao.findById(petStoreId);
        return optionalPetStore.orElse(null);
    }

    @Transactional
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee employee) {
        PetStore petStore = findPetStoreById(petStoreId);
        if (petStore == null) {
            throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
        }

        Employee newEmployee = findOrCreateEmployee(employee.getEmployeeId(), petStoreId);
        copyEmployeeFields(newEmployee, employee);
        newEmployee.setPetStore(petStore);

        Employee savedEmployee = employeeDao.save(newEmployee);
        return new PetStoreEmployee(savedEmployee);
    }

    private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
        if (employeeId != null) {
            return findEmployeeById(petStoreId, employeeId);
        } else {
            return new Employee();
        }
    }

    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId));

        if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
            throw new IllegalArgumentException("Employee with ID " + employeeId +
                    " does not belong to pet store with ID: " + petStoreId);
        }

        return employee;
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }

    @Transactional
    public void addCustomerToPetStore(Long petStoreId, PetStoreCustomer customer) {
        PetStore petStore = findPetStoreById(petStoreId);
        if (petStore == null) {
            throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
        }

        Customer newCustomer = findOrCreateCustomer(customer.getCustomerId(), petStoreId);
        copyCustomerFields(newCustomer, customer);

        if (!newCustomer.getPetStores().contains(petStore)) {
            newCustomer.getPetStores().add(petStore);
        }

        if (!petStore.getCustomers().contains(newCustomer)) {
            petStore.getCustomers().add(newCustomer);
        }

        // Save the changes
        petStoreDao.save(petStore);
    }

    private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
    customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
    customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
    customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
}


private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
        if (customerId != null) {
            return findCustomerById(petStoreId, customerId);
        } else {
            return new Customer();
        }
    }

    private Customer findCustomerById(Long petStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));

        if (!customer.getPetStores().stream().anyMatch(store -> store.getPetStoreId().equals(petStoreId))) {
            throw new IllegalArgumentException("Customer with ID " + customerId +
                    " does not belong to pet store with ID: " + petStoreId);
        }

        return customer;
    }

    @Transactional(readOnly = true)
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> petStoreDataList = new ArrayList<>();

        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData(petStore);
            petStoreData.getCustomers().clear();
            petStoreData.getEmployees().clear();

            petStoreDataList.add(petStoreData);
        }

        return petStoreDataList;
    }

    public PetStoreData retrievePetStoreById(Long petStoreId) {
        Optional<PetStore> optionalPetStore = petStoreDao.findById(petStoreId);

        if (optionalPetStore.isPresent()) {
            PetStore petStore = optionalPetStore.get();
            return new PetStoreData(petStore);
        }

        throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
    }

    @Transactional
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);

        if (petStore == null) {
            throw new NoSuchElementException("Pet store not found with ID: " + petStoreId);
        }

        petStoreDao.delete(petStore);
    }
}
