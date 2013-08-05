/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWorkList;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IIterationWorkListService;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.service.IUserService;



/**
 * IterationWorkList Controller.
 * @author chuhaiquan
 * @since 2013-05-03
 */
@RequestMapping(value = "/iterationWorkList/*")
@Controller
public class IterationWorkListController extends BaseController {

	@Autowired
	private IIterationWorkListService iterationWorkListService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IIterationService iterationService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,
			@RequestParam(required=false) Integer pageIndex,
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Long id) {
		Iteration iteration=iterationService.findById(id);
		Page<IterationWorkList> page=iterationWorkListService.getOnePage(iteration,pageIndex,pageSize);
		model.addAttribute("page", page);
		model.addAttribute("vistor", userService.findById(UserRequestContextInfo.getVisitorId()));
		model.addAttribute("iterationId", id);
		return "/iteration/iterationWorkList";
	}
	
	

}
