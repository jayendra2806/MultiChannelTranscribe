package com.dgtl.datamanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgtl.datamanagement.exception.RecordNotFoundException;
import com.dgtl.datamanagement.model.Policies;
import com.dgtl.datamanagement.repository.PoliciesRepository;
import com.dgtl.datamanagement.service.PoliciesService;

/**
 * 
 * @author VS5051403 
 * implementation class of PoliciesService interface
 *
 */
@Service
public class PoliciesServiceImpl implements PoliciesService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * creating bean of PoliciesRepository class.
	 */
	@Autowired
	private PoliciesRepository policiesRepository;

	/**
	 * findAll in interface CrudRepository<T,ID> that returns all entities of
	 * Policies.
	 */
	@Override
	public List<Policies> getAllPolicyDetails() {
		return policiesRepository.findAll();
	}

	/**
	 * returns the entity with the given id or Optional#empty() if none found
	 */
	@Override
	public Policies getPolicyDetails(Integer id) {
		Optional<Policies> policiesOpt = policiesRepository.findById(id);
		if (!policiesOpt.isPresent()) {
			throw new RecordNotFoundException("Invalid policy Id: " + id);
		}
		return policiesOpt.get();
	}

	/**
	 * Saves a Policies entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 */
	@Override
	public Policies savePolicyDetails(Policies policies) {
		return policiesRepository.save(policies);
	}

}