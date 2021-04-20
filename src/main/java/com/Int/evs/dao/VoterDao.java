package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;

import com.Int.evs.bean.VoterDetailsBean;

public interface VoterDao extends CrudRepository<VoterDetailsBean, Integer> {

}
