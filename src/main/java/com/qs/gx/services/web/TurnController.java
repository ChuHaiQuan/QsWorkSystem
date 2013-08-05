/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.model.Turn;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IRewardPunishmentDetailService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.gx.services.service.ITurnService;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * Turn Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@RequestMapping(value = "/turn/*")
@Controller
public class TurnController extends BaseController {

	@Autowired
	private ITurnService turnService;
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;
	@Autowired
	private IIterationService iterationService;

	@RequestMapping(value = "/publishTurn", method = RequestMethod.GET)
	public String publishTurn(Model model) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("todayDate", formate.format(date));
		return "/turn/publishTurn";
	}

	@RequestMapping(value = "/addTurner", method = RequestMethod.POST)
	public String addTurner(Model model, Turn turn,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize) {
		User user = userService.findByIsMaster(true);
		if (user != null) {
			user.setIsMaster(false);
			userService.save(user);
		}
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 10;
		Page<Turn> page = turnService.findByDateAndCategory(turn.getWorkDate(),
				turn.getCategory(), pageIndex, pageSize);
		if (page.hasContent()) {
			model.addAttribute("msg", "今天该轮值已录入！");
			return "/turn/publishTurn";
		}
		turn.setUser(userService.findById(UserRequestContextInfo.getVisitorId()));
		turn.setUserName(userService.findById(UserRequestContextInfo.getVisitorId()).getName());
		turnService.save(turn);
		User userOld = userService.findById(UserRequestContextInfo
				.getVisitorId());
		userOld.setIsMaster(true);
		userService.save(userOld);
		RewardPunishmentDetail rewardPunishmentDetail = new RewardPunishmentDetail();
		rewardPunishmentDetail.setDate(turn.getWorkDate());
		rewardPunishmentDetail.setUser(userService
				.findById(UserRequestContextInfo.getVisitorId()));
		RewardPunishmentType rewardPunishmentType=rewardPunishmentTypeService.findById(new Long(45));
		rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
		rewardPunishmentDetail.setUserName(userService.findById(UserRequestContextInfo.getVisitorId()).getName());
		rewardPunishmentDetail.setPointReason(rewardPunishmentType);
		rewardPunishmentDetail.setRemark(rewardPunishmentType.getContent());
		rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(turn.getWorkDate()));
		rewardPunishmentDetailService.save(rewardPunishmentDetail);
		if(rewardPunishmentType.getPriorsRank()==null){
			rewardPunishmentType.setPriorsRank(1);
		}else{
			rewardPunishmentType.setPriorsRank(rewardPunishmentType.getPriorsRank().intValue()+1);
		}
		rewardPunishmentTypeService.save(rewardPunishmentType);
		return "redirect:/turn/turnList";
	}

	@RequestMapping(value = "/turnList", method = RequestMethod.GET)
	public String turnList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 10;
		Page<Turn> page = turnService.findOnePage(name, pageIndex, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		return "/turn/turnList";
	}

}
