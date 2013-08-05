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
import com.qs.gx.services.model.TrainingStatisticsAttach;
import com.qs.gx.services.service.ITrainingStatisticsAttachService;

/**
 * TrainingStatisticsAttach Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-17
 */
@RequestMapping(value = "/trainingStatisticsAttach/*")
@Controller
public class TrainingStatisticsAttachController extends BaseController {

	@Autowired
	private ITrainingStatisticsAttachService trainingStatisticsAttachService;

	@RequestMapping(value = "/findByDate", method = RequestMethod.GET)
	public String findByDate(Model model,
			@RequestParam(required = false) String date,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize) {
		if (pageIndex == null) {
			pageIndex = 0;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		Page<TrainingStatisticsAttach> page = trainingStatisticsAttachService
				.findByDate(date, pageIndex, pageSize);
		model.addAttribute("page", page);
		return "/trainingStatistics/trainingStatisticsAttachList";
	}

}
