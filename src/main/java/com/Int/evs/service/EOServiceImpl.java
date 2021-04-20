package com.Int.evs.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.ApplicationBean;
import com.Int.evs.bean.ProfileBean;
import com.Int.evs.dao.ApplicationDao;
import com.Int.evs.dao.ElectionDao;
import com.Int.evs.dao.ProfileDao;
import com.Int.evs.dao.ResultDao;

@Service
public class EOServiceImpl implements IEOService {
	@Autowired ElectionDao electionDao;
	@Autowired ResultDao resultDao;
	@Autowired ProfileDao profileDao;
	@Autowired ApplicationDao applicationDao;
	@PersistenceContext EntityManager entityManager;
	
	@Transactional
	@Override
	public String generateVoterId(String userId, String status) {
		// TODO Auto-generated method stub
		Query q3 = entityManager.createNativeQuery("drop table if exists VoterIdGenerator");
		int x = q3.executeUpdate();
		System.out.println("ststus="+status);
		
		Query q = entityManager.createNativeQuery("select next_val from evs_seq_voterid");
		BigInteger id = (BigInteger)q.getSingleResult();
		BigInteger new_id = id.add(BigInteger.ONE);
		System.out.println(id);
		
		Query q2 = entityManager.createNativeQuery("update evs_seq_voterid set next_val=? where next_val=?");
		q2.setParameter(1, new_id);
		q2.setParameter(2, id);
		x = q2.executeUpdate();
		System.out.println("status="+status);
		String voterId = (String)id.toString();
		
		ProfileBean pb_temp = profileDao.findById(userId).get();
		ApplicationBean ab_temp = applicationDao.findById(userId).get();
		voterId = pb_temp.getFirstName().substring(0, 2)+ab_temp.getConstituency().substring(0, 2)+voterId;
		if (status.equals("1")) {
			Query q4 = entityManager.createNativeQuery("update application set approvedStatus=1,passedStatus=3,voterId=? where userId=?");
			q4.setParameter(1, voterId);
			q4.setParameter(2, userId);
			x = q4.executeUpdate();
		}
		else if(status.equals("0")) {
			Query q4 = entityManager.createNativeQuery("update application set approvedStatus=0,passedStatus=3 where userId=?");
			q4.setParameter(1, userId);
			x = q4.executeUpdate();
		}
		return "Generated VoterIds";
	}

	@Override
	public ArrayList<ApplicationBean> viewAllVoterIdApplications() {
		ArrayList<ApplicationBean> arrayList = new ArrayList<>();
		Iterator<ApplicationBean> iterator = applicationDao.findAll().iterator();
		while(iterator.hasNext()) {
			ApplicationBean ab_temp = iterator.next();
			if (ab_temp.getPassedStatus()==2) {
				arrayList.add(ab_temp);
			}
		}
		return arrayList;
	}

}
