/**
 * 
 */
package com.ipoint.cargo4me.shared.dto;

/**
 * @author Kirill Shchegolev
 * 
 */
public class CargoOwnerDTO extends UserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6223126331799739646L;

	private String volume;

	private String weight;

	private String dispatchOther;

	private String deliveryOther;

	private String dispatchDate;

	private String deliveryDate;

	private String featureTransport;

	private String typeOfLoadingUnloading;

	private String briefDescriptionCargo;

	public CargoOwnerDTO() {
		super();

	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDispatchOther() {
		return dispatchOther;
	}

	public void setDispatchOther(String dispatchOther) {
		this.dispatchOther = dispatchOther;
	}

	public String getDeliveryOther() {
		return deliveryOther;
	}

	public void setDeliveryOther(String deliveryOther) {
		this.deliveryOther = deliveryOther;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getFeatureTransport() {
		return featureTransport;
	}

	public void setFeatureTransport(String featureTransport) {
		this.featureTransport = featureTransport;
	}

	public String getTypeOfLoadingUnloading() {
		return typeOfLoadingUnloading;
	}

	public void setTypeOfLoadingUnloading(String typeOfLoadingUnloading) {
		this.typeOfLoadingUnloading = typeOfLoadingUnloading;
	}

	public String getBriefDescriptionCargo() {
		return briefDescriptionCargo;
	}

	public void setBriefDescriptionCargo(String briefDescriptionCargo) {
		this.briefDescriptionCargo = briefDescriptionCargo;
	}

	@Override
	public String getJSON() {
		String json = "{" + super.getJSON();
		json += ",\"volume\":\"" + checkNull(getVolume()) + "\","
				+ "\"weight\":\"" + checkNull(getWeight()) + "\","
				+ "\"dispatchOther\":\"" + checkNull(getDispatchOther())
				+ "\"," + "\"deliveryOther\":\""
				+ checkNull(getDeliveryOther()) + "\"," + "\"dispatchDate\":\""
				+ checkNull(getDispatchDate()) + "\"," + "\"deliveryDate\":\""
				+ checkNull(getDeliveryDate()) + "\","
				+ "\"featureTransport\":\"" + checkNull(getFeatureTransport())
				+ "\"," + "\"typeOfLoadingUnloading\":\""
				+ checkNull(getTypeOfLoadingUnloading()) + "\","
				+ "\"briefDescriptionCargo\":\""
				+ checkNull(getBriefDescriptionCargo()) + "\"}";

		return json;
	}

}
