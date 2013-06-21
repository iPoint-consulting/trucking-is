package com.ipoint.cargo4me.client.jsonvalidator;

public interface JSONValidator {

	public void validate(String validateValue)
			throws JSONValidatorFactory.JSONValidationException;

}
