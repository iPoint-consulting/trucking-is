package com.ipoint.cargo4me.client.jsonvalidator;

import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;

public class TypeValidator implements JSONValidator {

	@Override
	public void validate(String type) throws JSONValidationException {
		if (!type.equals("send") && !type.equals("transport")
				&& !type.equals("предложение")) {
			throw new JSONValidatorFactory.JSONValidationException();
		}
	}

}
