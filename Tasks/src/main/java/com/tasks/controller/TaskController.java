package com.tasks.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.tasks.entities.Project;
import com.tasks.entities.User;
import com.tasks.repositories.ProjectRepository;
import com.tasks.repositories.TaskRepository;
import com.tasks.repositories.UserRepository;
import com.tasks.services.ProjectService;

@Controller
public class TaskController {

	@Autowired
	UserRepository usr;
	
	@Autowired
	TaskRepository tskR;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectRepository prjRepo;
    
	@RequestMapping("/")
	public String welcome(Model model) {
		User user=usr.findOne(2L);
		model.addAttribute("user",user);
		model.addAttribute("name","Hello man");
		List<Project> projects=Lists.newArrayList(prjRepo.findAll());
		model.addAttribute("projects", projects);
		System.out.println("ALLO");

		return "index";
	}	
	
	
	

}
	
    
   
