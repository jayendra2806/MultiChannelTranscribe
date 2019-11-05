package com.dgtl.datamanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author VS5051403
 *
 */
@Entity
public class Policies implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String typeCode;
	@Column
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	private Date createdDate;
	@Column
	private String createdBy;
	@Column
	private Date updatedDate;
	@Column
	private String updatedBy;

	@JsonIgnore
	@ManyToOne
	private Customers customers;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VehiclePolicyDetails> vehiclePolicyDetails = new LinkedList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Claims> claims = new LinkedList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PolicyType> policyType = new HashSet<>();

	public Policies() {
		super();
	}

	public Policies(int id, String typeCode, Date startDate, Date endDate, Date createdDate, String createdBy,
			Date updatedDate, String updatedBy, Customers customers, List<VehiclePolicyDetails> vehiclePolicyDetails,
			List<Claims> claims, Set<PolicyType> policyType) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.customers = customers;
		this.vehiclePolicyDetails = vehiclePolicyDetails;
		this.claims = claims;
		this.policyType = policyType;
	}

	@Override
	public String toString() {
		return "Policies [id=" + id + ", typeCode=" + typeCode + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", customers=" + customers + ", vehiclePolicyDetails="
				+ vehiclePolicyDetails + ", claims=" + claims + ", policyType=" + policyType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public List<VehiclePolicyDetails> getVehiclePolicyDetails() {
		return vehiclePolicyDetails;
	}

	public void setVehiclePolicyDetails(List<VehiclePolicyDetails> vehiclePolicyDetails) {
		this.vehiclePolicyDetails = vehiclePolicyDetails;
	}

	public List<Claims> getClaims() {
		return claims;
	}

	public void setClaims(List<Claims> claims) {
		this.claims = claims;
	}

	public Set<PolicyType> getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Set<PolicyType> policyType) {
		this.policyType = policyType;
	}
}