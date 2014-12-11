package com.tasks.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.tasks.entities.Project;
import com.tasks.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Serializable>{

	Iterable<Task> findByProject(Project project);
	
}
