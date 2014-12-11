package com.tasks.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to UserTask
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserTask> userTasks;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<UserTask> getUserTasks() {
		return this.userTasks;
	}

	public void setUserTasks(List<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	public UserTask addUserTask(UserTask userTask) {
		getUserTasks().add(userTask);
		userTask.setUser(this);

		return userTask;
	}

	public UserTask removeUserTask(UserTask userTask) {
		getUserTasks().remove(userTask);
		userTask.setUser(null);

		return userTask;
	}

	@Override
	public String toString() {
		return String
				.format("User [email=%s, firstName=%s, lastName=%s, id=%s, version=%s]",
						email, firstName, lastName,  id, version);
	}

	
}