/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Menu;
import com.qs.gx.services.service.IMenuService;



/**
 * Menu Controller.
 * @author chuhaiquan
 * @since 2012-11-11
 */

@Controller
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;
	 /**
     * 
     * 
     *  订餐页面
     *  @Author ChuHaiQuan
     * 
     */
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String apply(Model model
			){
	int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	    Page<Menu> page=menuService.findMenuByDayOfWeek(dayOfWeek);
	         model.addAttribute("page", page);
		return "/lunch/apply";
	}
}
