/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.BeanUtilsEx;
import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWorkList;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IIterationWorkListService;
import com.qs.gx.services.service.IRewardPunishmentDetailService;
import com.qs.gx.services.service.IRewardPunishmentStasticsService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.gx.services.support.IterationWorkStatus;
import com.qs.gx.services.support.RewardPunishmentStatus;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.service.IUserService;



/**
 * Iteration Controller.
 * @author chuhaiquan
 * @since 2013-02-28
 */
@RequestMapping(value = "/iteration/*")
@Controller
public class IterationController extends BaseController {

	@Autowired
	private IIterationService iterationService;
	
	@Autowired
	private IIterationWorkListService iterationWorkListService;
	
	@Autowired
	
	private IRewardPunishmentStasticsService   rewardPunishmentStasticsService;
	
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;

	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/iterationList", method = RequestMethod.GET)
	public String iterationList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize) {
		if(pageIndex==null){
			pageIndex=0;
		}if(pageSize==null){
			pageSize=10;
		}
		Page<Iteration> page=iterationService.findOnePageInfo(pageIndex,pageSize);
		model.addAttribute("page", page);
		model.addAttribute("vistor", userService.findById(UserRequestContextInfo.getVisitorId()));
		return "/iteration/iterationList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addIteration(Model model,
			@RequestParam(required=false) Long id) {
		if(id!=null){
			Iteration iteration=iterationService.findById(id);
			model.addAttribute("iteration", iteration);
		}
		return "/iteration/add";
	}
	
	@RequestMapping(value = "/doAddOrUpdate", method = RequestMethod.POST)
	public String doAddIteration(Model model,Iteration iteration) 
			throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Iteration iterationOld=null;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		iteration.setCreateTime(formate.format(date));
		if(iteration.getId()!=null){
			iterationOld=iterationService.findById(iteration.getId());
		}
		if(iterationOld==null){
			iterationService.save(iteration);
		}else{
			BeanUtilsEx.copyPropertiesWithoutNullValues(iterationOld, iteration);
			iterationService.update(iterationOld);
		}
		return "redirect:/iteration/iterationList";
	}
	
	@RequestMapping(value = "/delById", method = RequestMethod.GET)
	public String delById(Model model,@RequestParam(required=true) Long id) {
		iterationService.delete(id);
		return "redirect:/iteration/iterationList";
	}
	
	
	@RequestMapping(value = "/statistic", method = RequestMethod.GET)
	public String statistic(Model model,
			@RequestParam(required=true) Long id) {
		List<RewardPunishmentDetail> needToConfirmList=rewardPunishmentDetailService.findByStatus(RewardPunishmentStatus.待确认.getValue());
		model.addAttribute("needToConfirmList", needToConfirmList);
		Iteration iteration=iterationService.findById(id);
		model.addAttribute("iteration", iteration);
		List<RewardPunishmentType> typeList=null;
		//全勤奖列表
		//判断是否已经确认
		RewardPunishmentType rewardPunishmentType= rewardPunishmentTypeService.findById((long)56);
		typeList=new ArrayList<RewardPunishmentType>();
		typeList.add(rewardPunishmentType);
		List<RewardPunishmentDetail> fullAttendenRewardPunishmentDetaillist=rewardPunishmentDetailService.findByIterationAndType(iteration,typeList);
		model.addAttribute("fullAttendenRewardPunishmentDetaillist", fullAttendenRewardPunishmentDetaillist);
		List<Object[]> list=iterationService.findFullAttendenceAwardByIteration(iteration);
		model.addAttribute("fullAttendenceAwardUserList", list);
		//查询每个迭代任务未完成的人
		List<IterationWorkList> iterationWorkListList=iterationWorkListService.findByIterationAndStatus(IterationWorkStatus.未完成.getValue(),iteration);
		model.addAttribute("iterationWorkListList", iterationWorkListList);
		//查询bug奖包括(组外和里程碑)
		RewardPunishmentType rewardPunishmentType1= rewardPunishmentTypeService.findById((long)39);
		typeList=new ArrayList<RewardPunishmentType>();
		typeList.add(rewardPunishmentType1);
		List<RewardPunishmentDetail> theHighestBugPointPunishmentDetaillist=rewardPunishmentDetailService.findByIterationAndType(iteration,typeList);
		model.addAttribute("theHighestBugPointPunishmentDetaillist", theHighestBugPointPunishmentDetaillist);
		List<Object[]> theHighestBugPointUsersList=rewardPunishmentStasticsService.findTheHighestBugPointUsers(iteration);
		model.addAttribute("theHighestBugPointUsersList", theHighestBugPointUsersList);
		//查询迭代综合分数最高的人
		RewardPunishmentType rewardPunishmentType2= rewardPunishmentTypeService.findById((long)51);
		typeList=new ArrayList<RewardPunishmentType>();
		typeList.add(rewardPunishmentType2);
		List<RewardPunishmentDetail> theHighestAllPointPunishmentDetaillist=rewardPunishmentDetailService.findByIterationAndType(iteration,typeList);
		model.addAttribute("theHighestAllPointPunishmentDetaillist", theHighestAllPointPunishmentDetaillist);
		List<Object[]> theHighesAlltPointUsersList=rewardPunishmentStasticsService.findTheHighesAlltPointUsers(iteration);
		model.addAttribute("theHighesAlltPointUsersList", theHighesAlltPointUsersList);
		//查询迭代扣分最多的人
		RewardPunishmentType rewardPunishmentType3= rewardPunishmentTypeService.findById((long)50);
		typeList=new ArrayList<RewardPunishmentType>();
		typeList.add(rewardPunishmentType3);
		List<RewardPunishmentDetail> theHighestLowPointPunishmentDetaillist=rewardPunishmentDetailService.findByIterationAndType(iteration,typeList);
		model.addAttribute("theHighestLowPointPunishmentDetaillist", theHighestLowPointPunishmentDetaillist);
		List<Object[]> theHighestLowPointUsersList=rewardPunishmentStasticsService.findTheHighestLowPointUsers(iteration);
		model.addAttribute("theHighestLowPointUsersList", theHighestLowPointUsersList);
		model.addAttribute("vistor", userService.findById(UserRequestContextInfo.getVisitorId()));
		return "/iteration/statistic";
	}
	
	
	/**
	 * 确认全勤奖
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "/confirmFullAttendenceAward", method = RequestMethod.POST)
	public @ResponseBody
	void confirmFullAttendenceAward(Model model,
			@RequestParam(required=true) Long id) {
		Iteration iteration=iterationService.findById(id);
		RewardPunishmentType rewardPunishmentType= rewardPunishmentTypeService.findById((long)56);
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> list=iterationService.findFullAttendenceAwardByIteration(iteration);
		RewardPunishmentDetail rewardPunishmentDetail=null;
		for(Object[] object:list){
			rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(iteration.getEndTime());
			rewardPunishmentDetail.setPointReason(rewardPunishmentType);
			rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
			rewardPunishmentDetail.setRemark("全勤奖得分");
			rewardPunishmentDetail.setIteration(iteration);
			rewardPunishmentDetail.setUser(userService.findById(((BigInteger)object[0]).longValue()));
			rewardPunishmentDetail.setUserName(userService.findById(((BigInteger)object[0]).longValue()).getName());
			rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	}
	
	/**
	 * 得分最高者名单
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "/confirmHighestAllPointAward", method = RequestMethod.POST)
	public @ResponseBody
	void confirmHighestAllPointAward(Model model,
			@RequestParam(required=true) Long id) {
		Iteration iteration=iterationService.findById(id);
		RewardPunishmentType rewardPunishmentType= rewardPunishmentTypeService.findById((long)51);
		List<Object[]> list=rewardPunishmentStasticsService.findTheHighesAlltPointUsers(iteration);
		RewardPunishmentDetail rewardPunishmentDetail=null;
		for(Object[] object:list){
			rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(iteration.getEndTime());
			rewardPunishmentDetail.setPointReason(rewardPunishmentType);
			rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
			rewardPunishmentDetail.setRemark("迭代总分最高分奖励!");
			rewardPunishmentDetail.setIteration(iteration);
			rewardPunishmentDetail.setUser(userService.findById(((BigInteger)object[4]).longValue()));
			rewardPunishmentDetail.setUserName(userService.findById(((BigInteger)object[4]).longValue()).getName());
			rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	}
	
	/**
	 * 扣分最多者名单
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "/confirmHighestLosePointAward", method = RequestMethod.POST)
	public @ResponseBody
	void confirmHighestLosePointAward(Model model,
			@RequestParam(required=true) Long id) {
		Iteration iteration=iterationService.findById(id);
		RewardPunishmentType rewardPunishmentType= rewardPunishmentTypeService.findById((long)50);
		List<Object[]> list=rewardPunishmentStasticsService.findTheHighestLowPointUsers(iteration);
		RewardPunishmentDetail rewardPunishmentDetail=null;
		for(Object[] object:list){
			rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(iteration.getEndTime());
			rewardPunishmentDetail.setPointReason(rewardPunishmentType);
			rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
			rewardPunishmentDetail.setRemark("扣分最多的惩罚!");
			rewardPunishmentDetail.setIteration(iteration);
			rewardPunishmentDetail.setUser(userService.findById(((BigInteger)object[4]).longValue()));
			rewardPunishmentDetail.setUserName(userService.findById(((BigInteger)object[4]).longValue()).getName());
			rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	}
	
	/**
	 * bug最多者名单
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "/confirmHighestBugPointAward", method = RequestMethod.POST)
	public @ResponseBody
	void confirmHighestBugPointAward(Model model,
			@RequestParam(required=true) Long id) {
		Iteration iteration=iterationService.findById(id);
		RewardPunishmentType rewardPunishmentType= rewardPunishmentTypeService.findById((long)39);
		List<Object[]> list=rewardPunishmentStasticsService.findTheHighestBugPointUsers(iteration);
		RewardPunishmentDetail rewardPunishmentDetail=null;
		for(Object[] object:list){
			rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(iteration.getEndTime());
			rewardPunishmentDetail.setPointReason(rewardPunishmentType);
			rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
			rewardPunishmentDetail.setRemark("最差质量奖!");
			rewardPunishmentDetail.setIteration(iteration);
			rewardPunishmentDetail.setUser(userService.findById(((BigInteger)object[0]).longValue()));
			rewardPunishmentDetail.setUserName(userService.findById(((BigInteger)object[0]).longValue()).getName());
			rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	}
	
	@RequestMapping(value = "/detailInfo", method = RequestMethod.GET)
	public String detailInfo(Model model,
			@RequestParam(required=true) Long id) {
		Iteration iteration=iterationService.findById(id);
		model.addAttribute("allPointDetailList", rewardPunishmentStasticsService.findTheAlltPointDetails(iteration));
		model.addAttribute("losePointDetailList", rewardPunishmentStasticsService.findTheLowPointDetails(iteration));
		model.addAttribute("bugPointDetailList", rewardPunishmentStasticsService.findTheBugPointDetails(iteration));
		return "/iteration/statisticDetail";
	}
	

}
