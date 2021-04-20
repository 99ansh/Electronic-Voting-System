package com.Int.evs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Int.evs.bean.*;
import com.Int.evs.dao.ElectionDao;
import com.Int.evs.service.AdminServiceImpl;
import com.Int.evs.util.Authentication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired Authentication userAuth;
	@Autowired private AdminServiceImpl adminServiceImpl;
	String currentUser = "admin";
	String userType="A";
	ModelAndView modelAndView = new ModelAndView();
	ArrayList<ElectionBean> electionBeanArrayList;
	ArrayList<PartyBean> partyBeanArrayList;
	ArrayList<CandidateBean> candidateBeanArrayList;
	ArrayList<CredentialsBean> credentialsBeanArrayList;
	String response;
	GsonBuilder gsonBuilder =  new GsonBuilder();
	
	
	@GetMapping("")
	public ModelAndView meth1(@ModelAttribute("currentUser")CredentialsBean credentialsBean){
		System.out.println("inside/admin");
	    System.out.println(credentialsBean.getUserId());
	    System.out.println(credentialsBean.getPassword());
	    System.out.println(credentialsBean.getUserType());
		modelAndView.setViewName(currentUser+"//admin_index");
		modelAndView.addObject("currentUser", credentialsBean);
		return modelAndView;
	}
//	@GetMapping("/addElectionView")
//	public ModelAndView meth2() {
//		modelAndView.setViewName(currentUser+"//addElection");
//		return modelAndView;
//	}
	
	@PostMapping("/addElection")
	public String meth3(@ModelAttribute("election") ElectionBean electionBean) {
		ArrayList<ElectionBean> arrayList = adminServiceImpl.viewAllUpcomingElections();
		if(electionBean.getElectionDate().after(electionBean.getCountingDate())) {
			return "Failed-ElectionDate after CountingDate";
		}
		for (ElectionBean eb_temp : arrayList) {
			if (String.valueOf(eb_temp.getElectionDate()).equals(String.valueOf(electionBean.getElectionDate()))){
				return "Failed-Two elections same date";
			}

		}
		String s = adminServiceImpl.addElection(electionBean);
		return "Success";
		
	}
	@PostMapping("/getElection")
	public String getElection(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("electionId")String electionId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			ArrayList<ElectionBean> al = adminServiceImpl.viewElections();
			for (ElectionBean electionBean : al) {
				if(electionBean.getElectionId()==Integer.parseInt(electionId)) {
					return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(electionBean);
				}
			}
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson("Error");
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}	
	
	@PostMapping("/deleteElection")
	public String deleteElection(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("electionId")String electionId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.deleteElection(electionId));
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	@PostMapping("/getParty")
	public String getParty(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("partyId")String partyId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			ArrayList<PartyBean> al = adminServiceImpl.viewAllParty();
			for (PartyBean partyBean : al) {
				if(partyBean.getPartyId()==Integer.parseInt(partyId)) {
					return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(partyBean);
				}
			}
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson("Error");
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	@PostMapping("/deleteParty")
	public String deleteParty(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("partyId")String partyId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.deleteParty(partyId));
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	@PostMapping("/getCandidate")
	public String getCandidate(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("candidateId")String candidateId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			ArrayList<CandidateBean> al = adminServiceImpl.viewAllCandidates();
			for (CandidateBean candidateBean : al) {
				if(candidateBean.getCandidateId()==Integer.parseInt(candidateId)) {
					return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(candidateBean);
				}
			}
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson("Error");
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	
	@PostMapping("/deleteCandidate")
	public String deleteCandidate(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("candidateId")String candidateId) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.deletecandidate(candidateId));
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	@PostMapping("/viewAllElections")
	public String viewAllElections(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.viewElections());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	@PostMapping("/viewAllUpcomingElections")
	public String viewAllUpcomingElections(@RequestParam("userId") String userId,@RequestParam("password") String password){
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.viewAllUpcomingElections());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
//	@GetMapping("/addPartyView")
//	public ModelAndView meth6(){
//		modelAndView.setViewName(currentUser+"//addParty");
//		return modelAndView;
//	}
	@PostMapping("/addParty")
	public String meth7(@ModelAttribute("party") PartyBean partyBean){
		return adminServiceImpl.addParty(partyBean);
	}
	@PostMapping("/viewAllParty")
	public String viewAllParty(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.viewAllParty());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}

	@PostMapping("/addCandidateView")
	public String addCandidateView(@RequestParam("userId") String userId,@RequestParam("password") String password){
		modelAndView.setViewName(currentUser+"//addCandidate");
		electionBeanArrayList = adminServiceImpl.viewAllUpcomingElections();
		Map<Integer, String> electionMap = new HashMap<>();
		for (ElectionBean eb_temp : electionBeanArrayList) {
			electionMap.put(eb_temp.getElectionId(), eb_temp.getElectionId().toString()+"-"+eb_temp.getName());
		}
		partyBeanArrayList = adminServiceImpl.viewAllParty();
		Map<Integer, String> partyMap = new HashMap<>();
		for (PartyBean pb_temp : partyBeanArrayList) {
			partyMap.put(pb_temp.getPartyId(), pb_temp.getPartyId().toString()+"-"+pb_temp.getName());
		}
		modelAndView.addObject("electionMap", electionMap);
		modelAndView.addObject("partyMap", partyMap);
		JsonElement jsonElement = new JsonObject();
		
		jsonElement.getAsJsonObject().addProperty("electionMap", new Gson().toJson(electionMap));
		jsonElement.getAsJsonObject().addProperty("partyMap", new Gson().toJson(partyMap));
		
		return new Gson().toJson(jsonElement);

	      
	}
	
	@PostMapping(path="/addCandidate",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String meth10(@ModelAttribute("candidate") CandidateBean candidateBean,@RequestParam("electionId") String electionId,@RequestParam("partyId") String partyId){
		System.out.println(candidateBean.toString());
		System.out.println(electionId);
		System.out.println(partyId);
		return adminServiceImpl.addCandidate(candidateBean,Integer.parseInt(electionId),Integer.parseInt(partyId));
	}
	
	@GetMapping("/viewCandidateDetailsByElectionView")
	public ModelAndView meth11() {
		modelAndView.setViewName(currentUser+"//viewCandidateDetailsByElection");
		electionBeanArrayList = adminServiceImpl.viewElections();
		Map<Integer,String> electionMap = new HashMap<Integer,String>();
		for (ElectionBean eb_temp : electionBeanArrayList) {
			electionMap.put(eb_temp.getElectionId(),eb_temp.getElectionId().toString()+"-"+eb_temp.getName());
		}
		modelAndView.addObject("electionMap", electionMap);
		return modelAndView;
	}
	@PostMapping("/viewAllCandidates")
	public String viewAllCandidates(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			electionBeanArrayList = adminServiceImpl.viewAllUpcomingElections();
			Map<Integer, String> electionMap = new HashMap<>();
			for (ElectionBean eb_temp : electionBeanArrayList) {
				electionMap.put(eb_temp.getElectionId(), eb_temp.getElectionId().toString()+"-"+eb_temp.getName());
			}
			partyBeanArrayList = adminServiceImpl.viewAllParty();
			Map<Integer, String> partyMap = new HashMap<>();
			for (PartyBean pb_temp : partyBeanArrayList) {
				partyMap.put(pb_temp.getPartyId(), pb_temp.getPartyId().toString()+"-"+pb_temp.getName());
			}
			JsonElement jsonElement = new JsonObject();
			
			jsonElement.getAsJsonObject().addProperty("electionMap", new Gson().toJson(electionMap));
			jsonElement.getAsJsonObject().addProperty("partyMap", new Gson().toJson(partyMap));
			jsonElement.getAsJsonObject().addProperty("candidates",new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.viewAllCandidates()));
			
			return new Gson().toJson(jsonElement);
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	
	
	@PostMapping("/viewCandidateDetailsByElection")
	public ArrayList<CandidateBean> meth13(@RequestParam(name="electionId") Integer electionId) {
		return adminServiceImpl.viewCandidateDetailsByElection(electionId);
	}
	
	@GetMapping("/viewCandidateDetailsByPartyView")
	public ModelAndView meth14() {
		modelAndView.setViewName(currentUser+"//viewCandidateDetailsByParty");
		partyBeanArrayList = adminServiceImpl.viewAllParty();
		Map<Integer,String> partyMap = new HashMap<Integer,String>();
		for (PartyBean pb_temp : partyBeanArrayList) {
			partyMap.put(pb_temp.getPartyId(),pb_temp.getPartyId().toString()+"-"+pb_temp.getName());
		}
		modelAndView.addObject("partyMap", partyMap);
		return modelAndView;
	}

	@PostMapping("/viewCandidateDetailsByParty")
	public ArrayList<CandidateBean> meth15(@RequestParam(name="partyId") Integer partyId) {
		return adminServiceImpl.viewCandidateDetailsByParty(partyId);
	}
	
	@PostMapping("/viewAllAdminPendingApplications")
	public String viewAllAdminPendingApplications(@RequestParam("userId") String userId,@RequestParam("password") String password) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson(adminServiceImpl.viewAllAdminPendingApplications());
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	
	@PostMapping("/forwardVoterIDRequest")
	public String forwardVoterIDRequest(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("requests")String requests) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			System.out.println("requests = "+requests);
			Gson gson = new Gson();
			String[] userIds = gson.fromJson(requests, String[].class);
			for (int i=0;i<userIds.length;i++) {
				boolean resp = adminServiceImpl.forwardVoterIDRequest(userIds[i]);
				System.out.println(resp);
			}
			return requests;
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Access denied");
		}
	}
	
	@PostMapping("/approveElectionResults")
	public String approveElectionResults(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("eIds")String eIds) {
		if (userAuth.authenticate(userId, password, userType).equals("true")) {
			Gson gson = new Gson();
			Integer[] approveRequests = gson.fromJson(eIds, Integer[].class);
			for (int i=0;i<approveRequests.length;i++) {
				adminServiceImpl.approveElectionResults(approveRequests[i]);
			}
			return gsonBuilder.setDateFormat("yyyy-MM-dd").create().toJson("Approved");
		}
		else {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("");
		}
	}
	
	@GetMapping("/trial")
	public ModelAndView meth100(){
		modelAndView.setViewName(currentUser+"//trial");
		return modelAndView;
	}
}
