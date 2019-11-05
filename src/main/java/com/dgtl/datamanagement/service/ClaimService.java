package com.dgtl.datamanagement.service;

import org.springframework.http.ResponseEntity;

import com.dgtl.datamanagement.model.Claims;

/**
 * 
 * @author VS5051403 
 * ClaimService is an interface for Claims entity, in which we define methods for CRUD operations.
 *
 */
public interface ClaimService {

	Claims getClaims(Integer id);

	Claims saveClaims(Claims claims);

	ResponseEntity<Object> updateClaims(Claims claims, Integer id);

	void deleteClaims(Integer id);

}