package com.dgtl.datamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dgtl.datamanagement.model.Customers;

/**
 * 
 * @author VS5051403 
 * This interface used to perform CRUD operations for customers entity
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

}