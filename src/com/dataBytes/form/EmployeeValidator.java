package com.dataBytes.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dataBytes.dto.Employee;

@Component("employeeValidator")
public class EmployeeValidator implements Validator {
	//which objects can be validated by this validator
		@Override
		public boolean supports(Class<?> paramClass) {
			return Employee.class.equals(paramClass);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			/*
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
			
			Employee emp = (Employee) obj;
			if(emp.getId() <=0){
				errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
			*/
		}

}
