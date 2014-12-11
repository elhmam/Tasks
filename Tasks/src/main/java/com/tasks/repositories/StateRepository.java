package com.tasks.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.tasks.entities.State;


public interface StateRepository extends CrudRepository<State, Serializable>{

}
