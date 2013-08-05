package com.qs.gx.services.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qs.permission.core.support.UserRequestContextInfo;




@Controller
public class MainController {
		
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String publish(Model model, @RequestParam(required = false) Long id) {
		return "/home";
	}
}
