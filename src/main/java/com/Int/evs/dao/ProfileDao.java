package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;

import com.Int.evs.bean.ProfileBean;

public interface ProfileDao extends CrudRepository<ProfileBean, String> {

}
