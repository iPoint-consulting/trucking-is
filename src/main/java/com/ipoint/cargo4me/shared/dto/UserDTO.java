package com.ipoint.cargo4me.shared.dto;

import java.io.Serializable;

abstract public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5054168098990223326L;

	private String userEmail;

	private String contactPhone;

	private String fullName;

	private String type;

	private String cost;

	private String comment;

	private String truckCategory;

	public UserDTO() {

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

	public String getTruckCategory() {
		return truckCategory;
	}

	public void setTruckCategory(String truckCategory) {
		this.truckCategory = truckCategory;
	}

	public String getJSON() {
		String json = "\"userEmail\":\"" + checkNull(getUserEmail()) + "\","
				+ "\"contactPhone\":\"" + checkNull(getContactPhone()) + "\","
				+ "\"fullName\":\"" + checkNull(getFullName()) + "\","
				+ "\"type\":\"" + checkNull(getType()) + "\"," + "\"cost\":\""
				+ checkNull(getCost()) + "\"," + "\"comment\":\""
				+ checkNull(getComment()) + "\"," + "\"truckCategory\":\""
				+ checkNull(getTruckCategory()) + "\"";

		return json;
	}

	protected String checkNull(String value) {
		return (null != value) ? value : "";
	}

}
