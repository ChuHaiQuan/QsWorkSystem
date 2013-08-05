/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.IterationWork;
import com.qs.gx.services.model.IterationWorkList;
import com.qs.gx.services.model.ProjectType;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.UserIterationWork;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IIterationWorkListService;
import com.qs.gx.services.service.IIterationWorkService;
import com.qs.gx.services.service.IProjectTypeService;
import com.qs.gx.services.service.IRewardPunishmentDetailService;
import com.qs.gx.services.service.IUserIterationWorkService;
import com.qs.gx.services.support.IterationWorkStatus;
import com.qs.permission.user.service.IUserService;



/**
 * IterationWork Controller.
 * @author chuhaiquan
 * @since 2013-05-03
 */
@RequestMapping(value = "/iterationWork/*")
@Controller
public class IterationWorkController extends BaseController {

	@Autowired
	private IIterationWorkService iterationWorkService;
	
	@Autowired
	private IIterationWorkListService iterationWorkListService;
	
	@Autowired
	private IProjectTypeService projectTypeService;
	
	@Autowired
	private IUserIterationWorkService userIterationWorkService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IIterationService iterationService;
	
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;

	@RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
	public String add(Model model,
			@RequestParam(required=false) Long id,
			@RequestParam(required=true) Long iterationId) {
		if(id!=null){
			IterationWorkList iterationWorkList=iterationWorkListService.findById(id);
			model.addAttribute("iterationWorkList", iterationWorkList);
			model.addAttribute("id", id);
		}
		model.addAttribute("iterationId", iterationId);
		List<ProjectType> list=projectTypeService.findAll();
		model.addAttribute("projectList", list);
		model.addAttribute("userList", userService.findAll());
		return "/iteration/addIterationWork";
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public @ResponseBody
	void saveOrUpdate(Model model,
			IterationWork iterationWorkNew,
			@RequestParam(required=false) Long iterationWorkid,
			@RequestParam(required=false) Long projectId,
			@RequestParam(required=false) Long[] relativePerson,
			@RequestParam(required=true) Long iterationId) {
		iterationWorkService.saveOrUpdate(iterationId,iterationWorkid,iterationWorkNew,projectId,relativePerson);
	}
	
	@RequestMapping(value = "/delById", method = RequestMethod.GET)
	public  String delById(Model model,
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) Integer pageIndex,
			@RequestParam(required=true) Long iterationId) {
		iterationWorkService.delById(id);
		return "redirect:/iterationWorkList/list?id="+iterationId+"&pageIndex="+pageIndex;
	}
	
	@RequestMapping(value = "/completeById", method = RequestMethod.GET)
	public  String completeById(Model model,
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) Integer pageIndex,
			@RequestParam(required=true) Long iterationId) {
		IterationWork iterationWork=iterationWorkService.findById(id);
		iterationWork.setStatus(IterationWorkStatus.完成.getValue());
		iterationWorkService.update(iterationWork);
		return "redirect:/iterationWorkList/list?id="+iterationId+"&pageIndex="+pageIndex;
	}
	
	@RequestMapping(value = "/unCompleteById", method = RequestMethod.GET)
	public  String unCompleteById(Model model,
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) Integer pageIndex,
			@RequestParam(required=true) Long iterationId) {
		IterationWork iterationWork=iterationWorkService.findById(id);
		iterationWork.setStatus(IterationWorkStatus.未完成.getValue());
		iterationWorkService.update(iterationWork);
		iterationWorkService.publishRelativePersonPunish(id);
		return "redirect:/iterationWorkList/list?id="+iterationId+"&pageIndex="+pageIndex;
	}
}
