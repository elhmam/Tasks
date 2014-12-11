package com.tasks.services;

import java.util.List;

import com.tasks.entities.Project;

public interface ProjectService {
	
	public List<Project> getAllProjects();

	public Project getProjectById(Long id);

}
