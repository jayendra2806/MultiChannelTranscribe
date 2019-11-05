package com.dgtl.datamanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dgtl.datamanagement.exception.RecordNotFoundException;
import com.dgtl.datamanagement.model.Customers;
import com.dgtl.datamanagement.repository.CustomerRepository;
import com.dgtl.datamanagement.service.CustomerService;

/**
 * 
 * @author VS5051403 
 * implementation class of CustomerService interface
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * Add logger for CustomerServiceImpl class
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * Creating bean of customerRepository
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * findAll in interface CrudRepository<T,ID> that returns all entities of
	 * Customers.
	 */
	@Override
	public List<Customers> getAllCustomerDetails() {
		return customerRepository.findAll();
	}

	/**
	 * returns the entity with the given id or Optional#empty() if none found
	 */
	@Override
	public ResponseEntity<Customers> getCustomerDetails(Integer id) {
		Optional<Customers> customersOpt = customerRepository.findById(id);
		if (!customersOpt.isPresent()) {
			throw new RecordNotFoundException("Invalid Customer Id: " + id);
		}
		return ResponseEntity.ok(customersOpt.get());
	}

	/**
	 * Saves a Customers entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 */
	@Override
	public Customers saveCustomerDetails(Customers customers) {
		return customerRepository.save(customers);
	}

	/**
	 * update the Customer entity on basis of id.
	 */
	@Override
	public ResponseEntity<Object> updateCustomerDetails(Customers customers, Integer id) {
		Optional<Customers> customersOpt = customerRepository.findById(id);
		if (!customersOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		customers.setId(id);
		customerRepository.save(customers);
		return ResponseEntity.ok(customersOpt);
	}

	/**
	 * Deletes a given entity.
	 * 
	 * @throws IllegalArgumentException
	 *             - in case the given entity is null.
	 */
	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}

}