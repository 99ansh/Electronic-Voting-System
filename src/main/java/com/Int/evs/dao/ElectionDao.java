package com.Int.evs.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Int.evs.bean.ElectionBean;

@Repository
public interface ElectionDao extends CrudRepository<ElectionBean, Integer> {

}
