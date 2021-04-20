package com.Int.evs.service;

import java.util.*;
import com.Int.evs.bean.*;

public interface IEOService {
	String generateVoterId(String userId, String status);
	ArrayList<ApplicationBean> viewAllVoterIdApplications();
}
