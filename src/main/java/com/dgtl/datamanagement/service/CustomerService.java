package com.dgtl.datamanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dgtl.datamanagement.model.Customers;

/**
 * 
 * @author VS5051403 
 * CustomerService is an interface for Customer entity, in which we define methods for CRUD operations.
 *
 */
public interface CustomerService {

	List<Customers> getAllCustomerDetails();

	ResponseEntity<Customers> getCustomerDetails(Integer id);

	Customers saveCustomerDetails(Customers customers);

	ResponseEntity<Object> updateCustomerDetails(Customers customers, Integer id);

	void deleteCustomer(Integer id);

}