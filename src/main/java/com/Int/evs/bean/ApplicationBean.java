package com.Int.evs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Application")

public class ApplicationBean {
	@Id
	String userId;
	@Column String constituency;
	@Column int passedStatus;
	@Column int approvedStatus;
	@Column String voterId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public int getPassedStatus() {
		return passedStatus;
	}
	public void setPassedStatus(int passedStatus) {
		this.passedStatus = passedStatus;
	}
	public int getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(int approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
}
