package com.Int.evs.service;

import java.util.Date;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.ApplicationBean;
import com.Int.evs.bean.CandidateBean;
import com.Int.evs.bean.ElectionBean;
import com.Int.evs.bean.PartyBean;
import com.Int.evs.bean.ResultBean;
import com.Int.evs.dao.ApplicationDao;
import com.Int.evs.dao.CandidateDao;
import com.Int.evs.dao.ElectionDao;
import com.Int.evs.dao.PartyDao;
import com.Int.evs.dao.ResultDao;

@Service
public class AdminServiceImpl implements IAdminService {
	@PersistenceContext private EntityManager entityManager;

	@Autowired ElectionDao electionDao;
	@Autowired PartyDao partyDao;
	@Autowired CandidateDao candidateDao;
	@Autowired ApplicationDao applicationDao;
	@Autowired ResultDao resultDao;
	@Override
	public String addElection(ElectionBean electionBean) {
		// TODO Auto-generated method stub
		System.out.println(electionBean.getElectionDate());
		try {
			electionDao.save(electionBean);
			return "Success";
		}
		catch(Exception e) {
			System.out.println("Error in adding new election: "+e.toString());
			return "Failed";
		}
	}

	@Override
	public ArrayList<ElectionBean> viewAllUpcomingElections() {
		// TODO Auto-generated method stub
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String today = ft.format(dNow);
		System.out.println(today);
		ArrayList<ElectionBean> arrayList = new ArrayList<ElectionBean>(); 
		Query q = entityManager.createNativeQuery("select electionId,name,electionDate,district,constituency,countingDate from election where electionDate>=?","defaultElection");
		q.setParameter(1, today);
		List values = q.getResultList();
		Iterator iterator = values.iterator( );

	    while (iterator.hasNext()) {

	       Object[] result = (Object[])iterator.next(); // Iterating through array object 

	       Integer electionId = (Integer) result[0]; // Fetching the field from array
	       String name = (String) result[1]; // Fetching the field from array
	       Timestamp electionDate = (Timestamp) result[2]; // Fetching the field from array
	       String district = (String) result[3]; // Fetching the field from array
	       String constituency = (String) result[4]; // Fetching the field from array
	       Timestamp countingDate = (Timestamp) result[5]; // Fetching the field from array

	       /* Likewise for all the fields, casting accordingly to the sequence in SELECT query*/
	       System.out.println("ElectionBean [electionId=" + electionId + ", name=" + name + ", electionDate=" + electionDate
					+ ", district=" + district + ", constituency=" + constituency + ", countingDate=" + countingDate + "]");
	       
	       ElectionBean eb_temp = new ElectionBean();
	       try {
	    	   eb_temp.setConstituency(constituency);
	    	   eb_temp.setCountingDate((Date)new SimpleDateFormat("yyyy-MM-dd").parse(countingDate.toString()));
	    	   eb_temp.setDistrict(district);
	    	   eb_temp.setElectionDate((Date)new SimpleDateFormat("yyyy-MM-dd").parse(electionDate.toString()));
	    	   eb_temp.setElectionId(electionId);
	    	   eb_temp.setName(name);
	    	   System.out.println(eb_temp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //System.out.println(eb_temp.getElectionDate());

			arrayList.add(eb_temp);
	    }
		return arrayList;
	}

	@Override
	public ArrayList<ElectionBean> viewElections() {
		// TODO Auto-generated method stub
		ArrayList<ElectionBean> arrayList = new ArrayList<ElectionBean>(); 
		Iterator<ElectionBean> electionIterator=electionDao.findAll().iterator();
		while(electionIterator.hasNext()) {
			arrayList.add(electionIterator.next());
		}
		return arrayList;
	}

	@Override
	public String addParty(PartyBean partyBean) {
		// TODO Auto-generated method stub
		try {

			System.out.println("partyId="+partyBean.getPartyId());
			partyDao.save(partyBean);
			System.out.println("partyId="+partyBean.getPartyId());
			return "Success";
		}
		catch(Exception e) {
			System.out.println("Errorin adding party"+e.toString());
			return "Failed";
		}
	}
	
	@Override
	public ArrayList<PartyBean> viewAllParty() {
		// TODO Auto-generated method stub
		ArrayList<PartyBean> arrayList = new ArrayList<PartyBean>();
		Iterator<PartyBean> iterator = partyDao.findAll().iterator();
		while(iterator.hasNext()) {
			arrayList.add(iterator.next());
		}
		return arrayList;
	} 
	

	@Override
	public String addCandidate(CandidateBean candidateBean,int electionId,int partyId) {
		// TODO Auto-generated method stub
		try {
			Optional<ElectionBean> eop= electionDao.findById(electionId);
			ElectionBean eb_temp = eop.get();
			//candidateBean.setElectionBean(eb_temp);
			eb_temp.getCandidates().add(candidateBean);
			
			Optional<PartyBean> pop= partyDao.findById(partyId);
			PartyBean pb_temp = pop.get();
			//candidateBean.setPartyBean(pb_temp);
			pb_temp.getCandidates().add(candidateBean);
			
			candidateDao.save(candidateBean);
			return "Success";
		}
		catch(Exception e) {
			System.out.println("Errorin adding candidate"+e.toString());
			return "Failed";
		}
	}

	@Override
	public ArrayList<CandidateBean> viewCandidateDetailsByElection(Integer eId) {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select candidateId,name,electionId,partyId,district,constituency,dateofBirth,mobileNo,address,emailId from candidate where electionId = ?","defaultCandidate");		
		q.setParameter(1, eId);
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
	public ArrayList<ApplicationBean> viewAllAdminPendingApplications() {
		// TODO Auto-generated method stub
		ArrayList<ApplicationBean> arrayList = new ArrayList<>();
		Iterator<ApplicationBean> iterator= applicationDao.findAll().iterator();
		while(iterator.hasNext()) {
			ApplicationBean ab_temp=iterator.next();
			if (ab_temp.getPassedStatus()==1) {
				arrayList.add(ab_temp);
			}
		}
		return arrayList;
	}
	
	@Transactional
	@Override
	public boolean forwardVoterIDRequest(String userId) {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("update application set passedStatus=2 where userId=?");
		q.setParameter(1, userId);
		int status = q.executeUpdate();
		System.out.println("passedStatus update"+status);
		if (status==1) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<CandidateBean> viewAllCandidates() {
		// TODO Auto-generated method stub
		ArrayList<CandidateBean> arrayList = new ArrayList<CandidateBean>();
		Iterator<CandidateBean> iterator = candidateDao.findAll().iterator();
		while(iterator.hasNext()) {
			arrayList.add(iterator.next());
		}
		return arrayList;
	}

	@Override
	public void approveElectionResults(Integer eId) {
		Query q = entityManager.createNativeQuery("select electionId,candidateId,count(*) from voter_details group by electionId,candidateId having electionId=?");
		q.setParameter(1, eId);
		List values = q.getResultList();
		Iterator iterator = values.iterator( );
		System.out.println("number of rows="+values.size());
	    while (iterator.hasNext()) {
	       Object[] result = (Object[])iterator.next();// Iterating through array object 
	       Integer electionId = (Integer)result[0];
	       Integer candidateId = (Integer)result[1];
	       BigInteger votecount = (BigInteger)result[2];
	       
	       ResultBean rb_temp = new ResultBean();
	       rb_temp.setCandidateId(candidateId);
	       rb_temp.setElectionId(electionId);
	       rb_temp.setVoteCount(votecount.intValue());
	       
	       resultDao.save(rb_temp);
	    }
	    
	}


	@Override
	public ArrayList<CandidateBean> viewCandidateDetailsByParty(Integer pId) {
		Query q = entityManager.createNativeQuery("select candidateId,name,electionId,partyId,district,constituency,dateofBirth,mobileNo,address,emailId from candidate where partyId = ?","defaultCandidate");		
		q.setParameter(1, pId);
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

	@Transactional
	public String deleteElection(String electionId) {
		Query q = entityManager.createNativeQuery("delete from election where electionId=?");
		q.setParameter(1, electionId);
		int rows = q.executeUpdate();
		if (rows==1) {
			return "Success";
		}
		return "Failed";
	}
	@Transactional
	public String deleteParty(String partyId) {
		Query q = entityManager.createNativeQuery("delete from party where partyId=?");
		q.setParameter(1, partyId);
		int rows = q.executeUpdate();
		if (rows==1) {
			return "Success";
		}
		return "Failed";
	}
	@Transactional
	public String deletecandidate(String candidateId) {
		Query q = entityManager.createNativeQuery("delete from candidate where candidateId=?");
		q.setParameter(1, candidateId);
		int rows = q.executeUpdate();
		if (rows==1) {
			return "Success";
		}
		return "Failed";
	}

}
