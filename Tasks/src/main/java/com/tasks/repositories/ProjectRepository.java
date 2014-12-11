package com.tasks.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tasks.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Serializable>{
	
	@Query(nativeQuery = true,value="select * from Project pr, task t where pr.id=t.project_id")	
	Iterable<Project> getAllProjects();
	
	
	//Iterable<Integer> smallNums = nums.filter(n -> n < max);
	
}	
