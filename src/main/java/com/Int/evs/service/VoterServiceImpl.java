package com.Int.evs.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.ApplicationBean;
import com.Int.evs.bean.CandidateBean;
import com.Int.evs.bean.CredentialsBean;
import com.Int.evs.bean.ElectionBean;
import com.Int.evs.bean.ProfileBean;
import com.Int.evs.bean.ResultBean;
import com.Int.evs.bean.VoterDetailsBean;
import com.Int.evs.dao.ApplicationDao;
import com.Int.evs.dao.ElectionDao;
import com.Int.evs.dao.ProfileDao;
import com.Int.evs.dao.ResultDao;
import com.Int.evs.dao.VoterDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class VoterServiceImpl implements IVoterService {
	@Autowired ElectionDao electionDao;
	@Autowired ResultDao resultDao;
	@Autowired ProfileDao profileDao;
	@Autowired ApplicationDao applicationDao;
	@Autowired VoterDao voterDao;
	
	@PersistenceContext EntityManager entityManager;
	
	@Transactional
	@Override
	public String castVote(String userId, String electionId, String candiadteId) {
		String vid=applicationDao.findById(userId).get().getVoterId();
		VoterDetailsBean vb_temp = new VoterDetailsBean();
		try {
			Iterator<VoterDetailsBean> iterator = voterDao.findAll().iterator();
			while(iterator.hasNext()) {
				vb_temp = iterator.next();
				if (vb_temp.getVoterId().equals(vid) && vb_temp.getElectionId()==Integer.parseInt(electionId)) {
					Query q = entityManager.createNativeQuery("update voter_details set candidateId=? where voterId=? and electionId=?");
					q.setParameter(1, candiadteId);
					q.setParameter(2, vid);
					q.setParameter(3, electionId);
					int numRows = q.executeUpdate();
					return "Updated";
				}
			}
			VoterDetailsBean vb_temp2 = new VoterDetailsBean();
			vb_temp2.setVoterId(vid);
			vb_temp2.setCandidateId(Integer.parseInt(candiadteId));
			vb_temp2.setElectionId(Integer.parseInt(electionId));
			
			voterDao.save(vb_temp2);
			return "Successfully voted!";
		}
		catch(Exception e){
			return "Failed";
		}
	}

	@Override
	public ArrayList<CandidateBean> viewCandidatesByElectionName(String eName, String cName) {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select candidateId,name,electionId,partyId,district,constituency,dateofBirth,mobileNo,address,emailId from candidate where electionId = any(select electionId from election where name=? and constituency=?)","defaultCandidate");		
		q.setParameter(1, eName);
		q.setParameter(2, cName);
		ArrayList<CandidateBean> arrayList = new ArrayList<CandidateBean>();
		List values = q.getResultList();
		Iterator iterator = values.iterator( );
		System.out.println("number of rows="+values.size());
	    while (iterator.hasNext()) {
	       Object[] result = (Object[])iterator.next();// Iterating through array object 
	       Integer candidateId = (Integer)result[0];
	       String name = (String)result[1];
	       Integer electionId = (Integer)result[2];
	       Integer partyId = (Integer)result[3];
	       String district = (String)result[4];
	       String constituency = (String)result[5];
	       Timestamp dateofBirth = (Timestamp)result[6];
	       String mobileNo = (String)result[7];
	       String address = (String)result[8];
	       String emailId = (String)result[9];
	       
	       CandidateBean cb_temp= new CandidateBean();
	       try {
	    	   cb_temp.setAddress(address);
		       cb_temp.setCandidateId(candidateId);
		       cb_temp.setConstituency(constituency);
	    	   cb_temp.setDateOfBirth((Date)new SimpleDateFormat("yyyy-MM-dd").parse(dateofBirth.toString()));
	    	   cb_temp.setDistrict(district);
	    	   cb_temp.setEmailId(emailId);
	    	   cb_temp.setMobileNo(mobileNo);
	    	   cb_temp.setName(name);
	    	   cb_temp.setElectionId(electionId);
	    	   cb_temp.setPartyId(partyId);
//	    	   cb_temp.setElectionBean(electionDao.findById(electionId).get());
//	    	   cb_temp.setPartyBean(partyDao.findById(partyId).get());

	       } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       arrayList.add(cb_temp);
	    }
	    return arrayList;
	}

	@Override
	public ArrayList<ResultBean> viewListOfElectionsResults() {
		// TODO Auto-generated method stub
		Iterator<ResultBean> iterator = resultDao.findAll().iterator();
		ArrayList<ResultBean> arrayList = new ArrayList<>();
		while(iterator.hasNext()) {
			arrayList.add(iterator.next());
		}
		return arrayList;
	}
	
	@Transactional
	@Override
	public String requestVoterId(String userId,String constituency) {
		try {
			ApplicationBean ab_temp = applicationDao.findById(userId).get();
			
			if (ab_temp.getApprovedStatus()==1) {
				return "Approved";
			}
			else if (ab_temp.getApprovedStatus()==0 && ab_temp.getPassedStatus()==3){
				ApplicationBean ab_temp2 = new ApplicationBean();
				ab_temp2.setApprovedStatus(0);
				ab_temp2.setConstituency(constituency);
				ab_temp2.setPassedStatus(1);
				ab_temp2.setUserId(userId);
				ab_temp2.setVoterId("");
				applicationDao.save(ab_temp2);
				return "Application was Rejected by EO, resubmitted";
			}
			else {
				return "Applied earlier, application is pending with EO or Admin";
			}
		}
		catch(Exception e){
			try {
				// TODO Auto-generated method stub
				ApplicationBean ab_temp = new ApplicationBean();
				ab_temp.setApprovedStatus(0);
				ab_temp.setConstituency(constituency);
				ab_temp.setPassedStatus(1);
				ab_temp.setUserId(userId);
				ab_temp.setVoterId("");
				applicationDao.save(ab_temp);
				return "Applied now, application is pending with EO or Admin";
			}
			catch(Exception e2) {
				System.out.println(e2);
				return "Failed to apply for some reason";
			}
		}
	}

	@Transactional
	@Override
	public String viewGeneratedVoterId(String userId, String constituency) {
		try {
			ApplicationBean ab_temp = applicationDao.findById(userId).get();
			
			if (ab_temp.getApprovedStatus()==1) {
				return ab_temp.getVoterId();
			}
			else if (ab_temp.getApprovedStatus()==0 && ab_temp.getPassedStatus()==3){
				return "Application was Rejected by EO, please re-submit";
			}
			else {
				return "Applied earlier, application is pending with EO or Admin";
			}
		}
		catch(Exception e) {
			return "Request for voterId not generated";
		}
		
	}

	@Override
	public ArrayList<ElectionBean> viewListOfElections() {
		// TODO Auto-generated method stub
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String today = ft.format(dNow);
		System.out.println(today);
		
		Iterator<ElectionBean> iterator = electionDao.findAll().iterator();
		ArrayList<ElectionBean> arrayList = new ArrayList<>();
		
		while(iterator.hasNext()) {
			ElectionBean eb_temp = iterator.next();
			
			System.out.println(eb_temp.getElectionDate() + String.valueOf(today.equals(String.valueOf(eb_temp.getElectionDate()))));
			System.out.println(eb_temp.getElectionDate().after(dNow));
			if (eb_temp.getElectionDate().after(dNow) || today.equals(String.valueOf(eb_temp.getElectionDate()))){
				arrayList.add(eb_temp);
			}
		}
		return arrayList;
	}

	@Override
	public ProfileBean getProfile(String userId) {
		return profileDao.findById(userId).get();
	}
	
	public String getVoteCastingCandidates(String userId){
		try {
			ApplicationBean ab_temp = applicationDao.findById(userId).get();
			if (ab_temp.getApprovedStatus()==1) {
				ArrayList<ElectionBean> upcomingElections = viewListOfElections();
				JsonElement jsonElement = new JsonObject();
				jsonElement.getAsJsonObject().addProperty("constituency", ab_temp.getConstituency());
				jsonElement.getAsJsonObject().addProperty("elections", new Gson().toJson(upcomingElections));
				return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(jsonElement);
			}
			else {
				return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Pending VoterId");
			}
		}
		catch(Exception e) {
			return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson("Pending VoterId");
		}
		
	}
	
	public String updateProfile(ProfileBean profileBean) {
		profileDao.save(profileBean);
		return "Success";
	}
}
