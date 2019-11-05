package com.dgtl.datamanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author VS5051403
 *
 */
@Entity
public class Customers implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@SequenceGenerator(name = "seq", initialValue = 11111)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private int id;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private String dateOfBirth;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String country;
	@Column
	private Date createdDate;
	@Column
	private String createdBy;
	@Column
	private Date updatedDate;
	@Column
	private String updatedBy;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Policies> policies = new LinkedList<>();

	public Customers() {
		super();
	}

	public Customers(int id, String name, String gender, String dateOfBirth, String address, String city,
			String country, Date createdDate, String createdBy, Date updatedDate, String updatedBy,
			List<Policies> policies) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.country = country;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.policies = policies;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + ", city=" + city + ", country=" + country + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
				+ ", policies=" + policies + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public List<Policies> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policies> policies) {
		this.policies = policies;
	}
}