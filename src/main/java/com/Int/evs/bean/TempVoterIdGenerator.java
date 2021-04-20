package com.Int.evs.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity
public class TempVoterIdGenerator {
	@Id
	@GeneratedValue(generator = "evs_seq_voterId")
    @GenericGenerator(
      name = "evs_seq_voterId",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
    	@Parameter(name = "sequence_name", value = "evs_seq_voterId"),
        @Parameter(name = "initial_value", value = "1000"),
        @Parameter(name = "increment_size", value = "1")
      })
	String voterId;
}
