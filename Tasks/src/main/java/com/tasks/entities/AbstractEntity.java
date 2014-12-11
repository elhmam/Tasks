package com.tasks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Version
	protected Long version;
	@Temporal(TemporalType.DATE)
	@Column(name="last_update")
	private Date lastUpdate;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	// initialisation
	public AbstractEntity build(Long id, Long version,Date lastUpdate) {
		this.id = id;
		this.version = version;
		this.lastUpdate=lastUpdate;
		return this;
	}

	@PrePersist
	public void setLastUpdate(){
		this.lastUpdate=new Date();
	}
	
	
	@Override
	public boolean equals(Object entity) {
		String class1 = this.getClass().getName();
		String class2 = entity.getClass().getName();
		if (!class2.equals(class1)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) entity;
		return this.id == other.id;
	}

	// getters et setters
	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
