package com.qs.gx.services.web;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.TrainingStatistics;
import com.qs.gx.services.model.Turn;
import com.qs.gx.services.service.IDailyWorkService;
import com.qs.gx.services.service.IProjectTypeService;
import com.qs.gx.services.service.IRewardPunishmentStasticsService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.gx.services.service.ITrainingStatisticsService;
import com.qs.gx.services.service.ITurnService;
import com.qs.permission.user.service.IUserService;


@RequestMapping(value = "/homeAttach/*")
@Controller
public class HomeAttachController extends BaseController {
		
	@Autowired
	private IDailyWorkService dailyWorkService;
	
	@Autowired
	private IProjectTypeService projectTypeService;

	@Autowired
	private IRewardPunishmentStasticsService rewardPunishmentStasticsService;

	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ITurnService turnService;
	@Autowired
    private ITrainingStatisticsService trainingStatisticsService;
	
	//首页获取今天轮值的人员
	@RequestMapping(value = "/getTodayTurnUser", method = RequestMethod.GET)
	public @ResponseBody
	List<Turn> getTodayTurnUser(Model model) {
		Date date=new Date(System.currentTimeMillis());
		Format format=new SimpleDateFormat("yyyy-MM-dd");
		List<Turn> turnList=turnService.findByDateAndCategoryForList(format.format(date));
		return turnList;
	}
	
	//首页获取今天培训的人员
	@RequestMapping(value = "/getTodayTrainer", method = RequestMethod.GET)
	public @ResponseBody
	List<TrainingStatistics> getTodayTrainer(Model model) {
		Date date=new Date(System.currentTimeMillis());
		Format format=new SimpleDateFormat("yyyy-MM-dd");
		List<TrainingStatistics> trainingStatisticsList=trainingStatisticsService.findByTime(format.format(date));
		return trainingStatisticsList;
	}
}
