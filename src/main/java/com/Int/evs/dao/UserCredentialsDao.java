package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;

import com.Int.evs.bean.CredentialsBean;

public interface UserCredentialsDao extends CrudRepository<CredentialsBean, String> {

}
