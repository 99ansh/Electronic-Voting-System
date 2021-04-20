package com.Int.evs.service;

import java.util.*;
import com.Int.evs.bean.*;

public interface IVoterService {
	String castVote(String userId, String electionId, String candiadteId);
	ArrayList<CandidateBean> viewCandidatesByElectionName(String electionName, String constituency);
	ArrayList<ResultBean> viewListOfElectionsResults();
	String requestVoterId(String userId,String constituency);
	String viewGeneratedVoterId(String userId, String constituency);
	ArrayList<ElectionBean> viewListOfElections();
	ProfileBean getProfile(String userId);
}
