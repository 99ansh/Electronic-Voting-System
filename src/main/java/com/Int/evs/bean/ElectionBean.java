package com.Int.evs.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import org.hibernate.annotations.GenericGenerator;

@SqlResultSetMappings(value = 
	{ 
		@SqlResultSetMapping(
				name = "defaultElection",
					columns= {@ColumnResult(name="electionId",type = Integer.class),
							@ColumnResult(name="name",type=String.class),
							@ColumnResult(name="electionDate",type=Timestamp.class),
							@ColumnResult(name="district",type=String.class),
							@ColumnResult(name="constituency",type=String.class),
							@ColumnResult(name="countingDate",type=Timestamp.class)
						}
					)
		}
)
@Entity
@Table(name="election")
public class ElectionBean {
	@Id
	@GeneratedValue(generator = "evs_seq_electionId")
    @GenericGenerator(
      name = "evs_seq_electionId",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
    	@Parameter(name = "sequence_name", value = "evs_seq_electionId"),
        @Parameter(name = "initial_value", value = "1000"),
        @Parameter(name = "increment_size", value = "1")
      })
	int electionId;
	@Column	String name;
	@Column @Temporal(TemporalType.DATE) @DateTimeFormat(pattern="yyyy-MM-dd") Date electionDate;
	@Column String district;
	@Column String constituency;
	@Column @Temporal(TemporalType.DATE) @DateTimeFormat(pattern="yyyy-MM-dd") Date countingDate;
	  
	//@OneToMany(cascade = CascadeType.ALL,mappedBy="electionBean")
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="electionId")
	private List<CandidateBean> candidates=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="electionId")
	private List<ResultBean> results=new ArrayList<>();	
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="electionId")
	private List<ResultBean> voter_details=new ArrayList<>();

	@Override
	public String toString() {
		return "ElectionBean [electionId=" + electionId + ", name=" + name + ", electionDate=" + electionDate
				+ ", district=" + district + ", constituency=" + constituency + ", countingDate=" + countingDate + "]";
	}

	public List<CandidateBean> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateBean> candidates) {
        this.candidates = candidates;
    }
	
	public List<ResultBean> getResults() {
		return results;
	}

	public void setResults(List<ResultBean> results) {
		this.results = results;
	}

	public List<ResultBean> getVoter_details() {
		return voter_details;
	}

	public void setVoter_details(List<ResultBean> voter_details) {
		this.voter_details = voter_details;
	}


	public Integer getElectionId() {
		return electionId;
	}
	public void setElectionId(Integer electionId) {
		this.electionId = electionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getElectionDate() {
		return electionDate;
	}
	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Date getCountingDate() {
		return countingDate;
	}
	public void setCountingDate(Date countingDate) {
		this.countingDate = countingDate;
	}
	
}
//
//package com.Int.evs.bean;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//@Entity
//@Table
//public class ElectionBean {
//    @Id
//    @GeneratedValue
//    private int id;
//    private String name;
//    
//
//}
