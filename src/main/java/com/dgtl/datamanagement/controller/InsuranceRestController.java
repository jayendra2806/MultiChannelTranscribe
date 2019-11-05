package com.dgtl.datamanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgtl.datamanagement.model.Claims;
import com.dgtl.datamanagement.model.Customers;
import com.dgtl.datamanagement.model.Policies;
import com.dgtl.datamanagement.service.ClaimService;
import com.dgtl.datamanagement.service.CustomerService;
import com.dgtl.datamanagement.service.PoliciesService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author VS5051403 
 * Rest controller in which we are creating various rest-full
 * web services for data management services.
 *
 */
@RequestMapping("/api")
@RestController
public class InsuranceRestController {

	private static final Logger logger = LoggerFactory.getLogger(InsuranceRestController.class);

	/**
	 * creating bean of CustomerService class
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * creating bean of PoliciesService class
	 */
	@Autowired
	private PoliciesService policiesService;

	/**
	 * creating bean of ClaimService class
	 */
	@Autowired
	private ClaimService claimService;

	@ApiOperation(value = "View a list of available Customers")
	@GetMapping(value = "/customer/getCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Customers> getCustomers() {
		return customerService.getAllCustomerDetails();
	}

	@ApiOperation(value = "View a list of available Customers for specific customer Id")
	@GetMapping(value = "/customer/getCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Customers> getCustomer(@PathVariable Integer id) {
		return customerService.getCustomerDetails(id);
	}

	@ApiOperation(value = "save Customer details")
	@PostMapping(value = "/customer/saveCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customers saveCustomer(@RequestBody Customers customers) {
		return customerService.saveCustomerDetails(customers);
	}

	@ApiOperation(value = "update Customer details")
	@PutMapping(value = "/customer/updateCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> updateCustomer(@RequestBody Customers customers,
			@PathVariable Integer id) {
		return customerService.updateCustomerDetails(customers, id);
	}

	@ApiOperation(value = "delete Customers details for specific customer Id")
	@DeleteMapping(value = "customer/deleteCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCustomer(@PathVariable Integer id) {
		logger.debug("Deleted customer having customer Id- {}", id);
		customerService.deleteCustomer(id);
	}

	@ApiOperation(value = "View a list of available Policies")
	@GetMapping(value = "/customer/getPolicies", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Policies> getPolicies() {
		return policiesService.getAllPolicyDetails();
	}

	@ApiOperation(value = "View a list of available policies for specific policy Id")
	@GetMapping(value = "/policies/getPolicy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policies getPolicy(@PathVariable Integer id) {
		return policiesService.getPolicyDetails(id);
	}

	@ApiOperation(value = "save Policy details")
	@PostMapping(value = "/policies/savePolicy", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policies savePolicy(@RequestBody Policies customers) {
		return policiesService.savePolicyDetails(customers);
	}

	@ApiOperation(value = "View a list of available claims details for specific claim Id")
	@GetMapping(value = "/claims/getClaims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Claims getClaims(@PathVariable Integer id) {
		return claimService.getClaims(id);
	}

	@ApiOperation(value = "save claim details")
	@PostMapping(value = "/claims/saveClaims", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Claims saveClaims(@RequestBody Claims claims) {
		return claimService.saveClaims(claims);
	}

	@ApiOperation(value = "update claim details")
	@PutMapping(value = "/claims/updateClaims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> updateClaims(@RequestBody Claims claims, @PathVariable Integer id) {
		return claimService.updateClaims(claims, id);
	}

	@ApiOperation(value = "delete claim details for specific claim Id")
	@DeleteMapping(value = "claims/deleteClaims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteClaims(@PathVariable Integer id) {
		logger.debug("Deleted Claims having Claim Id- {}", id);
		claimService.deleteClaims(id);
	}
}