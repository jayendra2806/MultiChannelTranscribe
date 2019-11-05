package com.dgtl.datamanagement.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dgtl.datamanagement.exception.RecordNotFoundException;
import com.dgtl.datamanagement.model.Claims;
import com.dgtl.datamanagement.repository.ClaimRepository;
import com.dgtl.datamanagement.service.ClaimService;

/**
 * 
 * @author VS5051403 
 * implementation class of ClaimService interface
 *
 */
@Service
public class ClaimServiceImpl implements ClaimService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * creating bean of ClaimRepository class
	 */
	@Autowired
	private ClaimRepository claimRepository;

	/**
	 * returns the entity with the given id or Optional#empty() if none found
	 */
	@Override
	public Claims getClaims(Integer id) {
		Optional<Claims> claimsOpt = claimRepository.findById(id);
		if (!claimsOpt.isPresent()) {
			throw new RecordNotFoundException("Invalid claim Id: " + id);
		}
		return claimsOpt.get();
	}

	/**
	 * Saves a Claims entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 */
	@Override
	public Claims saveClaims(Claims claims) {
		return claimRepository.save(claims);
	}

	/**
	 * update the Claims entity on basis of id.
	 */
	@Override
	public ResponseEntity<Object> updateClaims(Claims claims, Integer id) {
		Optional<Claims> claimsOpt = claimRepository.findById(id);
		if (!claimsOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		claims.setId(id);
		claimRepository.save(claims);
		return ResponseEntity.ok(claimsOpt);
	}

	/**
	 * Deletes a given entity.
	 * 
	 * @throws IllegalArgumentException
	 *             - in case the given entity is null.
	 */
	@Override
	public void deleteClaims(Integer id) {
		claimRepository.deleteById(id);
	}

}