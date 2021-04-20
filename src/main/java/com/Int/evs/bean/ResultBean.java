package com.Int.evs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="result")
public class ResultBean {
	@Id
	@GeneratedValue(generator = "evs_seq_serialId")
    @GenericGenerator(
    	      name = "evs_seq_serialId",
    	      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    	      parameters = {
    	    	@Parameter(name = "sequence_name", value = "evs_seq_serialId"),
    	        @Parameter(name = "initial_value", value = "1000"),
    	        @Parameter(name = "increment_size", value = "1")
    	      })
	Integer serialNo;
	@Column Integer electionId;
	@Column Integer candidateId;
	@Column Integer voteCount;
	
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Integer getElectionId() {
		return electionId;
	}
	public void setElectionId(Integer electionId) {
		this.electionId = electionId;
	}
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	
}
