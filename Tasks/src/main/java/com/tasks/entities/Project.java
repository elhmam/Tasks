package com.tasks.entities;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedNativeQuery(
	    name="allprojects",
	    query = "SELECT * FROM project prj, task tsk " +
	            "WHERE prj.id=tsk.project_id",
	    resultClass=Project.class)
public class Project extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	private String name;

	//bi-directional many-to-one association to Task
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy="project")
	private List<Task> tasks;

	public Project() {
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

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setProject(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setProject(null);

		return task;
	}

	@Override
	public String toString() {
		return String
				.format("Project [dateFrom=%s, dateTo=%s, name=%s,  id=%s, version=%s]",
						dateFrom, dateTo, name, id, version);
	}

		

}