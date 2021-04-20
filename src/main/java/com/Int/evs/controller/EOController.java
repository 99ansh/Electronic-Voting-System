package com.Int.evs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Int.evs.bean.CandidateBean;
import com.Int.evs.bean.CredentialsBean;
import com.Int.evs.bean.ElectionBean;
import com.Int.evs.bean.PartyBean;
import com.Int.evs.service.EOServiceImpl;
import com.Int.evs.service.VoterServiceImpl;
import com.Int.evs.util.Authentication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("eo")
public class EOController {

	String currentUser = "eo";
	String userType="E";
	@Autowired EOServiceImpl eoServiceImpl;
	@Autowired Authentication userAuth; 
	ModelAndView modelAndView = new ModelAndView();
	ArrayList<ElectionBean> electionBeanArrayList;
	ArrayList<PartyBean> partyBeanArrayList;
	ArrayList<CandidateBean> candidateBeanArrayList;
	ArrayList<CredentialsBean> credentialsBeanArrayList;
	String response;
	
	@GetMapping("")
	public ModelAndView meth0(@ModelAttribute("currentUser")CredentialsBean credentialsBean) {
		System.out.println(credentialsBean.toString());
		modelAndView.setViewName(currentUser+"//eo_index");
		modelAndView.addObject("currentUser", credentialsBean);
		return modelAndView;
	}
	
	@PostMapping("/generateVoterId")
	public String generateVoterId(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("approve")String approve,@RequestParam("reject")String reject) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			
			Gson gson = new Gson();
			String[] approveRequests = gson.fromJson(approve, String[].class);
			String[] rejectRequests = gson.fromJson(reject, String[].class);
			for (int i=0;i<approveRequests.length;i++) {
				String resp = eoServiceImpl.generateVoterId(approveRequests[i],"1");
				System.out.println(approveRequests[i]+resp);
			}
			for (int i=0;i<rejectRequests.length;i++) {
				String resp = eoServiceImpl.generateVoterId(rejectRequests[i],"0");
				System.out.println(rejectRequests[i]+resp);
			}
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Generated voterIds");
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	@PostMapping("/viewAllVoterIdApplications")
	public String viewAllVoterIdApplications(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(eoServiceImpl.viewAllVoterIdApplications());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
}