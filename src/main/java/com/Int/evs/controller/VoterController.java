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
import com.Int.evs.bean.ProfileBean;
import com.Int.evs.bean.ResultBean;
import com.Int.evs.service.AdminServiceImpl;
import com.Int.evs.service.VoterServiceImpl;
import com.Int.evs.util.Authentication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("voter")
public class VoterController {
	String currentUser = "voter";
	String userType="V";
	@Autowired VoterServiceImpl voterServiceImpl;
	@Autowired AdminServiceImpl adminServiceImpl;
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
		modelAndView.setViewName(currentUser+"//voter_index");
		modelAndView.addObject("currentUser", credentialsBean);
		return modelAndView;
	}
	@PostMapping("/profile")
	public String profile(@RequestParam("userId") String userId,@RequestParam("password") String password){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			ProfileBean pb_temp = voterServiceImpl.getProfile(userId);
			System.out.println(pb_temp);
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(pb_temp); 
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}	
	
	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("profileBean")ProfileBean profileBean){
		//if (userAuth.authenticate(userId, password, userType).equals("true")) {
			String temp = voterServiceImpl.updateProfile(profileBean);
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Success probably"); 
		//}
		//else {
		//	return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		//}
	}	
	
	@PostMapping("/requestVoterId")
	public String requestVoterId(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("constituency") String constituency){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			String resp = voterServiceImpl.requestVoterId(userId,constituency);
			return resp;
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
		
	}	
	
	@PostMapping("/viewGeneratedVoterId")
	public String viewGeneratedVoterId(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("constituency") String constituency){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			String resp = voterServiceImpl.viewGeneratedVoterId(userId,constituency);
			return resp;
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
		
	}
	
	@PostMapping("/viewListofElections")
	public String viewListofElections(@RequestParam("userId") String userId,@RequestParam("password") String password){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(voterServiceImpl.viewListOfElections());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	
	@PostMapping("/viewCandidatesByElectionName")
	public String viewCandidatesByElectionName(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("eName") String eName,@RequestParam("cName") String cName){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(voterServiceImpl.viewCandidatesByElectionName(eName, cName));
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	@PostMapping("/viewCastVote")
	public String viewCastVote(@RequestParam("userId") String userId,@RequestParam("password") String password){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return voterServiceImpl.getVoteCastingCandidates(userId);
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	@PostMapping("/castVote")
	public String castVote(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("electionId") String electionId,@RequestParam("candidateId") String candidateId){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(voterServiceImpl.castVote(userId,electionId, candidateId));
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	
	@PostMapping("/viewListOfElectionsResults")
	public String viewListOfElectionsResults(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(voterServiceImpl.viewListOfElectionsResults());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
		
	}
}
