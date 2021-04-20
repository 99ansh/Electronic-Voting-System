package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;

import com.Int.evs.bean.ApplicationBean;

public interface ApplicationDao extends CrudRepository<ApplicationBean, String> {

}
