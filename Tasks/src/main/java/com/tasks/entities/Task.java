package com.tasks.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	private String name;

	//bi-directional many-to-one association to Project
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;

	//bi-directional many-to-one association to UserTask
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy="task")
	private List<UserTask> userTasks;

	public Task() {
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<UserTask> getUserTasks() {
		return this.userTasks;
	}

	public void setUserTasks(List<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	public UserTask addUserTask(UserTask userTask) {
		getUserTasks().add(userTask);
		userTask.setTask(this);

		return userTask;
	}

	public UserTask removeUserTask(UserTask userTask) {
		getUserTasks().remove(userTask);
		userTask.setTask(null);

		return userTask;
	}

	@Override
	public String toString() {
		return String
				.format("Task [dateFrom=%s, dateTo=%s, name=%s, project=%s, id=%s, version=%s]",
						dateFrom, dateTo, name, project.getId(),  id, version);
	}

	
}