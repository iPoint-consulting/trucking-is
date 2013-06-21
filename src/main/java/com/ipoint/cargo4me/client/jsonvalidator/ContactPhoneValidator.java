package com.ipoint.cargo4me.client.jsonvalidator;

import com.google.gwt.regexp.shared.RegExp;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;

public class ContactPhoneValidator implements JSONValidator {

	public void validate(String phone) throws JSONValidationException {
		RegExp regExpPhone = RegExp.compile("\\d{10}", "i");
		if (!regExpPhone.test(phone))
			throw new JSONValidatorFactory.JSONValidationException();
	}
}
