package com.ipoint.cargo4me.client.jsonvalidator;

import com.google.gwt.regexp.shared.RegExp;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;

public class EmailValidator implements JSONValidator {

	public void validate(String email) throws JSONValidationException {
		String exp = "^[-a-z0-9!#$%&\'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)"
				+ "*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)"
				+ "*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
		RegExp regExp = RegExp.compile(exp, "i");
		if (!regExp.test(email) && !email.equals("none")) {
			throw new JSONValidatorFactory.JSONValidationException();
		}
	}

}
