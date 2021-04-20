package com.Int.evs.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.CredentialsBean;



@Component
public class Authentication{
	@PersistenceContext
	private EntityManager entityManager;
	public boolean authenticate(CredentialsBean user) {
		// TODO Auto-generated method stub
		CredentialsBean temp=entityManager.find(CredentialsBean.class,user.getUserId());
		if (temp.getPassword().equals(user.getPassword())) {
			System.out.println("Auth success");
			boolean resp = changeLoginStatus(user,1);
			return true;
		}
		System.out.println(temp.getUserId());
		System.out.println(temp.getPassword());
		System.out.println(temp.getUserType());
		System.out.println("Auth failed");
		return false;
	}

	public String authenticate(String userId,String password, String userType) {
		// TODO Auto-generated method stub
		try {
			CredentialsBean temp=entityManager.find(CredentialsBean.class,userId);
			if (temp.getPassword().equals(password) && temp.getUserType().equals(userType)) {
				System.out.println("Auth success");
				return "true";
			}
			return "false";
		}
		catch(Exception e){
		return "false";
		}
	}
	
	public boolean changeLoginStatus(CredentialsBean user, int loginStatus) {
		// TODO Auto-generated method stub
		return false;
	}

}
