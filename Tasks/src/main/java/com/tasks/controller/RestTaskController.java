package com.tasks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.collect.Lists;
import com.tasks.domain.Reponse;
import com.tasks.entities.Project;
import com.tasks.entities.Task;
import com.tasks.entities.User;
import com.tasks.entities.UserTask;
import com.tasks.repositories.ProjectRepository;
import com.tasks.repositories.TaskRepository;
import com.tasks.repositories.UserRepository;
import com.tasks.repositories.UserTaskRepository;
import com.tasks.services.ProjectService;

@RestController
public class RestTaskController {

	@Autowired
	UserRepository usrRepo;
	
	@Autowired
	ProjectRepository prjRepo;
	
	@Autowired
	TaskRepository tskRepo;

	@Autowired
	UserTaskRepository usrTskRepo;
	
	@Autowired
	ProjectService projectService;

	
	@RequestMapping(value="/{userId}/tasks", method = RequestMethod.GET)
	public List<UserTask> getUsersTasks(@PathVariable Long userId) {
		validateUser(userId);
		Iterable<UserTask> tasks = usrTskRepo.findByUserId(userId);		
		return Lists.newArrayList(tasks);
	}
	
	@RequestMapping(value="/tasks", method = RequestMethod.GET)
	public List<Task> getTasks() {
		Iterable<Task> tasks = tskRepo.findAll();	
		return Lists.newArrayList(tasks);
	}
	
	@RequestMapping(value="/projects", method = RequestMethod.GET)
	public Reponse getProjects() {
		//Iterable<Project> projects = prjRepo.findAll();	
		List<Project> projects=Lists.newArrayList(prjRepo.findAll());
		//List<Project> projects=projectService.getAllProjects();
		Map<String, Object> hash = new HashMap<String, Object>();
		List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
		
		for (Project project : projects) {
			Map<String, Object> hashTask = new HashMap<String, Object>();
			hashTask.put("project", project.getId());
			hashTask.put("tasks", project.getTasks());
			tasks.add(hashTask);
		}
		hash.put("projectsTasks",tasks);
		return new Reponse(0, hash);
	}
	
	@RequestMapping(value="/{userId}/tasks/{taskId}",method = RequestMethod.GET)
	public Reponse getUsersTsk(@PathVariable("userId") Long userId,@PathVariable Long taskId) {
		validateUser(userId);
		Task task = tskRepo.findOne(taskId);	
		
		return new Reponse(0, task);
	}
	
	@RequestMapping(value="/{userId}/tasks", method = RequestMethod.POST)
	ResponseEntity<?> addTaskToUser(@PathVariable("userId") Long userId, @RequestBody Task task) {		
		User user=validateUser(userId);
		
		Task tsk= tskRepo.findOne(task.getId());		
		UserTask ut=new UserTask();
		
		ut.setComment("comment 2");
		ut.setTask(tsk);
		ut.setUser(user);
	
		
		ut=usrTskRepo.save(ut);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tsk.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);		
	}
	
	@RequestMapping(value="/addTask", method = RequestMethod.POST)
	ResponseEntity<?> addTask(@RequestBody Task task) {			
		
		Project pr=projectService.getProjectById(task.getProject().getId());
		task.setProject(pr);
		Task tsk=tskRepo.save(task);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tsk.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);		
	}
	
	
	private User validateUser(Long userId) {
		User user = usrRepo.findOne(userId);
		if(user==null){
			throw  new UserNotFoundException(userId);
		}else {
			return user;
		}
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6230704207443767885L;

	public UserNotFoundException(Long userId) {
		super("could not find user '" + userId + "'.");
	}
}
