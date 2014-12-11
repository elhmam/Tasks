package com.tasks.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State extends AbstractEntity {
	private static final long serialVersionUID = 1L;


	private String label;

	//bi-directional many-to-one association to UserTask
	@JsonIgnore
	@OneToMany(mappedBy="state")
	private List<UserTask> userTasks;

	public State() {
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<UserTask> getUserTasks() {
		return this.userTasks;
	}

	public void setUserTasks(List<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	public UserTask addUserTask(UserTask userTask) {
		getUserTasks().add(userTask);
		userTask.setState(this);

		return userTask;
	}

	public UserTask removeUserTask(UserTask userTask) {
		getUserTasks().remove(userTask);
		userTask.setState(null);

		return userTask;
	}

	@Override
	public String toString() {
		return String.format(
				"State [label=%s, id=%s, version=%s]", label,
				 id, version);
	}

}