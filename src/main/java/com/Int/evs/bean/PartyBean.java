package com.Int.evs.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="party")

public class PartyBean {
	@Id
	@GeneratedValue(generator = "evs_seq_partyId")
	@GenericGenerator(
			name = "evs_seq_partyId",
		    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    parameters = {
		    	@Parameter(name = "sequence_name", value = "evs_seq_partyId"),
		        @Parameter(name = "initial_value", value = "1000"),
		        @Parameter(name = "increment_size", value = "1")
		      })
	Integer partyId;
	@Column String name;
	@Column String leader;
	@Column String symbol;
	
//  @OneToMany(cascade = CascadeType.ALL,mappedBy="partyBean")
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="partyId")
    private List<CandidateBean> candidates=new ArrayList<>();

	public List<CandidateBean> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<CandidateBean> candidates) {
		this.candidates = candidates;
	}
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
