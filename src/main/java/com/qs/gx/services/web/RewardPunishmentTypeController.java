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
import com.qs.gx.services.service.IRewardPunishmentTypeService;

/**
 * RewardPunishmentType Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
@RequestMapping(value = "/rewardPunishmentType/*")
@Controller
public class RewardPunishmentTypeController extends BaseController {

	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;

}
