/**
 * 
 */
package com.ipoint.cargo4me.shared.dto;

/**
 * @author Kirill Shchegolev
 * 
 */
public class CarrierDTO extends UserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3903879380110657746L;

	private String cityOfRegistry;

	/**
	 * Transport brief description.
	 */
	private String name;

	private String averageRatingPerKm;

	private String averageRatingPerHour;

	private String carOwner;

	private String driver;

	private String carRegistrationData;

	private String cargoType;

	private String typeOfShipping;

	private String typeOfCar;

	public CarrierDTO() {
		super();
	}

	public String getCityOfRegistry() {
		return cityOfRegistry;
	}

	public void setCityOfRegistry(String cityOfRegistry) {
		this.cityOfRegistry = cityOfRegistry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAverageRatingPerKm() {
		return averageRatingPerKm;
	}

	public void setAverageRatingPerKm(String averageRatingPerKm) {
		this.averageRatingPerKm = averageRatingPerKm;
	}

	public String getAverageRatingPerHour() {
		return averageRatingPerHour;
	}

	public void setAverageRatingPerHour(String averageRatingPerHour) {
		this.averageRatingPerHour = averageRatingPerHour;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getCarRegistrationData() {
		return carRegistrationData;
	}

	public void setCarRegistrationData(String carRegistrationData) {
		this.carRegistrationData = carRegistrationData;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getTypeOfShipping() {
		return typeOfShipping;
	}

	public void setTypeOfShipping(String typeOfShipping) {
		this.typeOfShipping = typeOfShipping;
	}

	public String getTypeOfCar() {
		return typeOfCar;
	}

	public void setTypeOfCar(String typeOfCar) {
		this.typeOfCar = typeOfCar;
	}

	@Override
	public String getJSON() {
		String json = "{" + super.getJSON();
		json += ",\"cityOfRegistry\":\"" + checkNull(getCityOfRegistry())
				+ "\"," + "\"name\":\"" + checkNull(getName()) + "\","
				+ "\"averageRatingPerKm\":\""
				+ checkNull(getAverageRatingPerKm()) + "\","
				+ "\"averageRatingPerHour\":\""
				+ checkNull(getAverageRatingPerHour()) + "\","
				+ "\"carOwner\":\"" + checkNull(getCarOwner()) + "\","
				+ "\"driver\":\"" + checkNull(getDriver()) + "\","
				+ "\"carRegistrationData\":\""
				+ checkNull(getCarRegistrationData()) + "\","
				+ "\"cargoType\":\"" + checkNull(getCargoType()) + "\","
				+ "\"typeOfShipping\":\"" + checkNull(getTypeOfShipping())
				+ "\"," + "\"typeOfCar\":\"" + checkNull(getTypeOfCar())
				+ "\"}";

		return json;
	}
}
