package com.Int.evs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Int.evs.bean.CredentialsBean;
import com.Int.evs.dao.UserCredentialsTrial;

@Service
public class TrialService {
	@Autowired private UserCredentialsTrial userCredentialsDAO;
	
	public void saveUser(CredentialsBean cb) {
		System.out.println("in saveuser method---service");
		userCredentialsDAO.save(cb);
	}
}
