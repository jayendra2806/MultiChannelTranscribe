package com.dgtl.datamanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author VS5051403
 *
 */
@Entity
public class VehiclePolicyDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String manufacturer;
	@Column
	private String model;
	@Column
	private String manufacuring;
	@Column
	private String registrationNo;
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
	private Policies policies;

	public VehiclePolicyDetails() {
		super();
	}

	public VehiclePolicyDetails(int id, String manufacturer, String model, String manufacuring, String registrationNo,
			Date createdDate, String createdBy, Date updatedDate, String updatedBy, Policies policies) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.manufacuring = manufacuring;
		this.registrationNo = registrationNo;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.policies = policies;
	}

	@Override
	public String toString() {
		return "VehiclePolicyDetails [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model
				+ ", manufacuring=" + manufacuring + ", registrationNo=" + registrationNo + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
				+ ", policies=" + policies + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacuring() {
		return manufacuring;
	}

	public void setManufacuring(String manufacuring) {
		this.manufacuring = manufacuring;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
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

	public Policies getPolicies() {
		return policies;
	}

	public void setPolicies(Policies policies) {
		this.policies = policies;
	}
}