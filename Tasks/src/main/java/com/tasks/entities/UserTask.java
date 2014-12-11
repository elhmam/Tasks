package com.tasks.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the user_task database table.
 * 
 */
@Entity
@Table(name="user_task")
@NamedQuery(name="UserTask.findAll", query="SELECT u FROM UserTask u")
public class UserTask extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String comment;

	//bi-directional many-to-one association to Task
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="task_id")
	private Task task;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to State	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	private State state;

	public UserTask() {
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String
				.format("UserTask [comment=%s, task=%s, user=%s, state=%s, id=%s, version=%s]",
						comment, task.getId(), user.getId(), state.getId(), id, version);
	}

}