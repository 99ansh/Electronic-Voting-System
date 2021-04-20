package com.Int.evs.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Int.evs.bean.CredentialsBean;

@Repository
public class UserCredentialsTrial {
    private EntityManager entityManager;

    @Autowired
    public UserCredentialsTrial(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Transactional
    public void save(CredentialsBean cb) {
    	System.out.println("in save method ------ daoS");
    	entityManager.persist(cb);
    }
}