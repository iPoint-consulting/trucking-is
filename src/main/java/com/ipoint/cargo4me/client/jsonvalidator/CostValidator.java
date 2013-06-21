package com.ipoint.cargo4me.client.jsonvalidator;

import com.google.gwt.regexp.shared.RegExp;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;

public class CostValidator implements JSONValidator {

	public void validate(String cost) throws JSONValidationException {
		RegExp regExpCost = RegExp.compile("^\\d+([\\,\\.]\\d{1,2})?$", "i");
		if (!regExpCost.test(cost) && !equals("0.0")) {
			throw new JSONValidatorFactory.JSONValidationException();
		}
	}

}
