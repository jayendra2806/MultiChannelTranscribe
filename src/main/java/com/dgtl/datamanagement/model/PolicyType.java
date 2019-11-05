package com.dgtl.datamanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author VS5051403
 *
 */
@Entity
public class PolicyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String typeCode;
	@Column
	private String name;
	@Column
	private String subType;
	@Column
	private String category;
	@Column
	private Date createdDate;
	@Column
	private String createdBy;
	@Column
	private Date updatedDate;
	@Column
	private String updatedBy;

	@JsonIgnore
	@ManyToMany
	private Set<Policies> policies = new HashSet<>();

	public PolicyType() {
		super();
	}

	public PolicyType(int id, String typeCode, String name, String subType, String category, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy, Set<Policies> policies) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.name = name;
		this.subType = subType;
		this.category = category;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.policies = policies;
	}

	@Override
	public String toString() {
		return "PolicyType [id=" + id + ", typeCode=" + typeCode + ", name=" + name + ", subType=" + subType
				+ ", category=" + category + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", policies=" + policies + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Set<Policies> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<Policies> policies) {
		this.policies = policies;
	}
}