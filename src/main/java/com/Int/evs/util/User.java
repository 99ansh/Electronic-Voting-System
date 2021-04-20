package com.Int.evs.util;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.CredentialsBean;
import com.Int.evs.bean.ProfileBean;
import com.Int.evs.dao.ProfileDao;
import com.Int.evs.dao.UserCredentialsDao;

@Service
public class User{
	@Autowired Authentication auth;
	@Autowired ProfileDao profileDao;
	@Autowired UserCredentialsDao userCredentialsDao;
	@PersistenceContext EntityManager entityManager;
	
	@Transactional
	public String login(CredentialsBean credentialsBean) {
		// TODO Auto-generated method stub
		boolean result = auth.authenticate(credentialsBean);
		if (result==true) {
			Query q2 = entityManager.createNativeQuery("update user_credentials set loginStatus=1 where userId=?");
			q2.setParameter(1, credentialsBean.getUserId());
			int status = q2.executeUpdate();
			System.out.println("status="+status);
			return userCredentialsDao.findById(credentialsBean.getUserId()).get().getUserType();
		}
		return "X";
	}
	
	@Transactional
	public boolean logout(String userId) {
		// TODO Auto-generated method stub
		Query q2 = entityManager.createNativeQuery("update user_credentials set loginStatus=0 where userId=?");
		q2.setParameter(1, userId);
		int status = q2.executeUpdate();
		System.out.println("status="+status);
		if (status==1)
			return true;
		return false;
	}
	
	@Transactional
	public String changePassword(CredentialsBean credentialsBean, String newPassword) {
		CredentialsBean cb_temp = userCredentialsDao.findById(credentialsBean.getUserId()).get();
		credentialsBean.setPassword(newPassword);
		credentialsBean.setLoginStatus(1);
		credentialsBean.setUserType(cb_temp.getUserType());
		userCredentialsDao.save(credentialsBean);
		System.out.println(credentialsBean.toString());
		if (credentialsBean.getUserType().equals("V")) {
			Query q2 = entityManager.createNativeQuery("update profile set password=? where userId=?");
			q2.setParameter(1, newPassword);
			q2.setParameter(2, credentialsBean.getUserId());
			int status = q2.executeUpdate();
			System.out.println("status="+status);
			if (status==1)
				return "Success";
			return "Failed";
		}
		return "Success";
		
	}

	@Transactional
	public String register(ProfileBean profilebean) {
		// TODO Auto-generated method stub
		Query q3 = entityManager.createNativeQuery("drop table if exists UserIdGenerator");
		int status = q3.executeUpdate();
		System.out.println("ststus="+status);
		
		Query q = entityManager.createNativeQuery("select next_val from evs_seq_userid");
		BigInteger id = (BigInteger)q.getSingleResult();
		BigInteger new_id = id.add(BigInteger.ONE);
		System.out.println(id);
		
		Query q2 = entityManager.createNativeQuery("update evs_seq_userid set next_val=? where next_val=?");
		q2.setParameter(1, new_id);
		q2.setParameter(2, id);
		status = q2.executeUpdate();
		System.out.println("status="+status);
		
		String userId = (String)id.toString();
		userId = profilebean.getFirstName().substring(0, 2)+userId;
		profilebean.setUserId(userId);
		CredentialsBean credentialsBean = new CredentialsBean();
		credentialsBean.setUserId(userId);
		credentialsBean.setPassword(profilebean.getPassword());
		credentialsBean.setUserType("V");
		credentialsBean.setLoginStatus(1);
		userCredentialsDao.save(credentialsBean);
		profileDao.save(profilebean);
		
		return "hello";
	}
	

}
