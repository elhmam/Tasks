package com.tasks.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.tasks.entities.User;


public interface UserRepository extends CrudRepository<User, Serializable>{

}
