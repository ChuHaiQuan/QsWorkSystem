/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qs.common.BeanUtilsEx;
import com.qs.core.web.BaseController;
import com.qs.gx.services.model.ProjectType;
import com.qs.gx.services.service.IProjectTypeService;

/**
 * ProjectType Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@RequestMapping(value = "/projectType/*")
@Controller
public class ProjectTypeController extends BaseController {

	@Autowired
	private IProjectTypeService projectTypeService;

	@RequestMapping(value = "/projectMaintain", method = RequestMethod.GET)
	public String projectMaintain(HttpServletRequest request, Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 10;
		Page<ProjectType> page = projectTypeService.findOnePage(name,
				pageIndex, pageSize);
		if (request.getRemoteAddr().equals("192.168.64.210")) {
			model.addAttribute("ip", request.getRemoteAddr());
		}
		model.addAttribute("name", name);
		model.addAttribute("page", page);
		return "/ProjectCommand/projectMaintain";
	}

	@RequestMapping(value = "/addOrEditProject", method = RequestMethod.GET)
	public String addOrEditProject(Model model,
			@RequestParam(required = false) Long id) {
		if (id != null) {
			ProjectType projectType = projectTypeService.findById(id);
			model.addAttribute("projectType", projectType);
			model.addAttribute("id", id);
		}
		return "/ProjectCommand/publishProject";
	}

	@RequestMapping(value = "/updateOrSaveProject", method = RequestMethod.POST)
	public String updateOrSaveProject(Model model, ProjectType projectType,
			@RequestParam(required = false) Long id) throws SecurityException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		if (id == null) {
			projectTypeService.save(projectType);
		} else {
			ProjectType oldProject = projectTypeService.findById(id);
			BeanUtilsEx
					.copyPropertiesWithoutNullValues(oldProject, projectType);
			projectTypeService.save(oldProject);
		}
		return "redirect:/projectType/projectMaintain";
	}

	@RequestMapping(value = "/delById", method = RequestMethod.GET)
	public String delById(Model model, @RequestParam(required = false) Long id) {
		projectTypeService.delete(id);
		return "redirect:/projectType/projectMaintain";
	}

}
