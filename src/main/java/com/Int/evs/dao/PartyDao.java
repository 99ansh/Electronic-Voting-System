package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Int.evs.bean.PartyBean;

@Repository
public interface PartyDao extends CrudRepository<PartyBean, Integer> {

}
