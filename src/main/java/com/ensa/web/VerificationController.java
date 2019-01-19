package com.ensa.web;

import javax.validation.Valid;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.ensa.entity.Carte;

@Controller
public class VerificationController implements ErrorController{

	private static final String PATH = "/error";
	
	
	
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
