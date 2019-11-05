package com.dgtl.datamanagement.service;

import java.util.List;

import com.dgtl.datamanagement.model.Policies;

/**
 * 
 * @author VS5051403 
 * PoliciesService is an interface for Policies entity, in which we define methods for CRUD operations.
 *
 */
public interface PoliciesService {

	List<Policies> getAllPolicyDetails();

	Policies getPolicyDetails(Integer id);

	Policies savePolicyDetails(Policies policies);

}