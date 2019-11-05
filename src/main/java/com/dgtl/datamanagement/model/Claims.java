package com.dgtl.datamanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Claims implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private Date requestDate;
	@Column
	private Date closureDate;
	@Column
	private Long requestedAmount;
	@Column
	private Long closureAmount;
	@Column
	private String status;
	@Column
	private String remarks;
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Resolutions resolutions;

	public Claims() {
		super();
	}

	public Claims(int id, Date requestDate, Date closureDate, Long requestedAmount, Long closureAmount, String status,
			String remarks, Date createdDate, String createdBy, Date updatedDate, String updatedBy, Policies policies,
			Resolutions resolutions) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.closureDate = closureDate;
		this.requestedAmount = requestedAmount;
		this.closureAmount = closureAmount;
		this.status = status;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.policies = policies;
		this.resolutions = resolutions;
	}

	@Override
	public String toString() {
		return "Claims [id=" + id + ", requestDate=" + requestDate + ", closureDate=" + closureDate
				+ ", requestedAmount=" + requestedAmount + ", closureAmount=" + closureAmount + ", status=" + status
				+ ", remarks=" + remarks + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", policies=" + policies
				+ ", resolutions=" + resolutions + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	public Long getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(Long requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public Long getClosureAmount() {
		return closureAmount;
	}

	public void setClosureAmount(Long closureAmount) {
		this.closureAmount = closureAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Resolutions getResolutions() {
		return resolutions;
	}

	public void setResolutions(Resolutions resolutions) {
		this.resolutions = resolutions;
	}
}