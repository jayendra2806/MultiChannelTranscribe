package com.dgtl.datamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dgtl.datamanagement.model.Claims;

/**
 * 
 * @author VS5051403 
 * This interface used to perform CRUD operations for claims entity
 *
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claims, Integer> {

}