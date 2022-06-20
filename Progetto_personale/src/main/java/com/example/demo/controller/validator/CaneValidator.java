package com.example.demo.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Cane;
import com.example.demo.service.CaneService;

@Component
public class CaneValidator implements Validator {

	@Autowired 
	private CaneService caneService;

	@Override
	public boolean supports(Class<?> pClass) {
		return Cane.class.equals(pClass);
	}


	@Override
	public void validate(Object target, Errors errors) {
		if(this.caneService.alreadyExists((Cane) target)) {
			errors.reject("cane.duplicato");
		}		
	}

}
