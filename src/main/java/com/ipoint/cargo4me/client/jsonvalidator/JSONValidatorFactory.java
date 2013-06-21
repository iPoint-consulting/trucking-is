package com.ipoint.cargo4me.client.jsonvalidator;

/**
 * 
 * @author burovv
 * 
 */

public class JSONValidatorFactory {

	public JSONValidator createValidator(String type)
			throws JSONValidationException {

		JSONValidator jsonValidator = null;

		if (type.equals("phone")) {
			jsonValidator = new ContactPhoneValidator();
		} else if (type.equals("email")) {
			jsonValidator = new EmailValidator();
		} else if (type.equals("name")) {
			jsonValidator = new NameValidator();
		} else if (type.equals("type")) {
			jsonValidator = new TypeValidator();
		} else if (type.equals("cost")) {
			jsonValidator = new CostValidator();
		} else if (type.equals("volume")) {
			jsonValidator = new NumberValidator();
		} else if (type.equals("weight")) {
			jsonValidator = new NumberValidator();
		} else if (type.equals("dispatchOther")) {
			jsonValidator = new AddressValidator();
		} else if (type.equals("deliveryOther")) {
			jsonValidator = new AddressValidator();
		} else if (type.equals("dispatchDate")) {
			jsonValidator = new TimeValidator();
		} else if (type.equals("deliveryDate")) {
			jsonValidator = new TimeValidator();
		} else if (type.equals("featureTransport")) {
			jsonValidator = new FeatureTransportValidator();
		} else if (type.equals("typeOfLoadingUnloading")) {
			jsonValidator = new TypeOfLoadingUnloadingValidator();
		} else if (type.equals("briefDescriptionCargo")) {
			jsonValidator = new BriefDescriptionCargoValidator();
		} else if (type.equals("truckCategory")) {
			jsonValidator = new TruckCategoryValidator();
		} else if (type.equals("trucName")) {
			jsonValidator = new TruckNameValidator();
		} else if (type.equals("averageRatingPerKm")) {
			jsonValidator = new CostValidator();
		} else if (type.equals("averageRatingPerHour")) {
			jsonValidator = new CostValidator();
		} else if (type.equals("cityOfRegistry")) {
			jsonValidator = new CityOfTruckRegistryValidator();
		} else if (type.equals("carOwner")) {
			jsonValidator = new NameValidator();
		} else if (type.equals("driver")) {
			jsonValidator = new NameValidator();
		} else if (type.equals("carRegistrationData")) {
			jsonValidator = new CarRegistrationDataValidator();
		} else if (type.equals("cargoType")) {
			jsonValidator = new CargoTypeValidator();
		} else if (type.equals("typeOfShipping")) {
			jsonValidator = new TypeOfShippingValidator();
		} else if (type.equals("typeOfCar")) {
			jsonValidator = new TypeOfCarValidator();
		}

		else {
			throw new JSONValidationException();
		}
		return jsonValidator;
	}

	public static class JSONValidationException extends Exception {

		private static final long serialVersionUID = 2122457746286406836L;

	}
}
