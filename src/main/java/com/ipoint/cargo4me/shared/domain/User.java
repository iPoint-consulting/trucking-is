package com.ipoint.cargo4me.shared.domain;

import com.google.gwt.view.client.ProvidesKey;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
public class User extends BaseEntity {

	private static final long serialVersionUID = 6601120606812364253L;

	@Index
	private String userEmail;

	@Index
	private String contactPhone;

	private String fullName;

	private String type;

	private String cost;

	private String comment;
	
	
	public static final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
		@Override
		public Object getKey(User item) {
			return item == null ? null : item.getUserEmail();
		}
	};

	public User() {

	}

	public User(String userEmail, String contactPhone, String fullName,
			String type, String cost, String comment) {
		super();
		this.userEmail = userEmail;
		this.contactPhone = contactPhone;
		this.fullName = fullName;
		this.type = type;
		this.cost = cost;
		this.comment = comment;

	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
