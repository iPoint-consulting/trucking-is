package com.ipoint.cargo4me.client.jsonvalidator;

import com.google.gwt.regexp.shared.RegExp;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;

public class NameValidator implements JSONValidator {

	public void validate(String name) throws JSONValidationException {
		RegExp regExpPhone = RegExp.compile("[а-яА-Я -]*", "i");
		if(!regExpPhone.test(name)) {
			throw new JSONValidatorFactory.JSONValidationException();
		}
	}

}
