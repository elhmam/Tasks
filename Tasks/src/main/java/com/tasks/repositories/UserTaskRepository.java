package com.tasks.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tasks.entities.UserTask;


public interface UserTaskRepository extends CrudRepository<UserTask, Serializable>{

	List<UserTask> findByUserId(Long id);

}
