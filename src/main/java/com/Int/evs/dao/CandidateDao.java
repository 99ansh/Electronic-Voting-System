package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Int.evs.bean.CandidateBean;

@Repository
public interface CandidateDao extends CrudRepository<CandidateBean, Integer> {

}
