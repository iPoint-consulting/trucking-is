package com.ipoint.cargo4me.shared.domain;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4404172265980248038L;
	@Id
	private Long id;

	public BaseEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
