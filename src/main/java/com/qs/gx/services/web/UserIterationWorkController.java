/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qs.core.web.BaseController;
import com.qs.gx.services.service.IUserIterationWorkService;



/**
 * UserIterationWork Controller.
 * @author chuhaiquan
 * @since 2013-05-03
 */
@RequestMapping(value = "/userIterationWork/*")
@Controller
public class UserIterationWorkController extends BaseController {

	@Autowired
	private IUserIterationWorkService userIterationWorkService;

}
