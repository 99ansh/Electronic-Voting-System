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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

@SqlResultSetMappings(value = 
{ 
	@SqlResultSetMapping(
			name = "defaultCandidate",
				columns= {@ColumnResult(name="candidateId",type = Integer.class),
						@ColumnResult(name="name",type=String.class),
						@ColumnResult(name="electionId",type = Integer.class),
						@ColumnResult(name="partyId",type = Integer.class),
						@ColumnResult(name="district",type=String.class),
						@ColumnResult(name="constituency",type=String.class),
						@ColumnResult(name="dateOfBirth",type=Timestamp.class),
						@ColumnResult(name="mobileNo",type=String.class),
						@ColumnResult(name="address",type=String.class),
						@ColumnResult(name="emailId",type=String.class),
					}
				) 
}
)

@Entity
@Table(name="candidate")
public class CandidateBean {
	@Id
	@GeneratedValue(generator = "evs_seq_candidateId")
    @GenericGenerator(
      name = "evs_seq_candidateId",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
    	@Parameter(name = "sequence_name", value = "evs_seq_candidateId"),
        @Parameter(name = "initial_value", value = "1000"),
        @Parameter(name = "increment_size", value = "1")
      })
	Integer candidateId;
	@Column String name;
	@Column String district;
	@Column String constituency;
	@Column @Temporal(TemporalType.DATE) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth;
	@Column String mobileNo;
	@Column String address;
	@Column String emailId;
	@Column Integer electionId;
	@Column Integer partyId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="candidateId")
	private List<ResultBean> results=new ArrayList<>();	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="candidateId")
	private List<ResultBean> voter_details=new ArrayList<>();
	
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
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="electionId")
//	private ElectionBean electionBean;


//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="partyId")
//	private PartyBean partyBean;
//	
//	public ElectionBean getElectionBean() {
//		return electionBean;
//	}
//	public void setElectionBean(ElectionBean electionBean) {
//		this.electionBean = electionBean;
//	}
//	public PartyBean getPartyBean() {
//		return partyBean;
//	}
//	public void setPartyBean(PartyBean partyBean) {
//		this.partyBean = partyBean;
//	}
	
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "CandidateBean [candidateId=" + candidateId + ", name=" + name + ", district=" + district
				+ ", constituency=" + constituency + ", dateOfBirth=" + dateOfBirth + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", emailId=" + emailId + "]";
	}
}

//package com.Int.evs.bean;
//
//import javax.persistence.*;
//import java.util.*;
//
//
//@Entity
//@Table
//public class CandidateBean {
//    @Id
//    @GeneratedValue
//    private  int id;
//    private String name;
//
//}
