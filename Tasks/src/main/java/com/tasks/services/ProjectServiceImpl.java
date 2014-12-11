package com.tasks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tasks.entities.Project;
import com.tasks.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<Project> getAllProjects() {
		Iterable<Project> projects = projectRepository.getAllProjects();
		return Lists.newArrayList(projects);		
	}

	@Override
	public Project getProjectById(Long id) {		
		return projectRepository.findOne(id);
	}

}
