package com.dgtl.datamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dgtl.datamanagement.model.Policies;

/**
 * 
 * @author VS5051403 
 * This interface used to perform CRUD operations for policies entity
 *
 */
@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Integer> {

}