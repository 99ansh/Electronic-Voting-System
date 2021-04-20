package com.Int.evs.service;

import java.util.*;
import com.Int.evs.bean.*;

public interface IAdminService {
	String addElection(ElectionBean electionBean);
	ArrayList<ElectionBean> viewAllUpcomingElections();
	ArrayList<ElectionBean> viewElections();
	String addParty(PartyBean partyBean);
	ArrayList<PartyBean> viewAllParty();
	String addCandidate(CandidateBean candidateBean,int electionId,int partyId);
	ArrayList<CandidateBean> viewAllCandidates();
	ArrayList<CandidateBean> viewCandidateDetailsByElection(Integer eId);
	ArrayList<ApplicationBean> viewAllAdminPendingApplications();
	boolean forwardVoterIDRequest(String userId);
	ArrayList<CandidateBean> viewCandidateDetailsByParty(Integer pId);
	void approveElectionResults(Integer electionId); //Note: Suitable data to be returned from Election and Result tables

}
