/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.qs.core.exception.AppWebException;
import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IRewardPunishmentDetailService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.gx.services.support.RewardPunishmentStatus;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * RewardPunishmentDetail Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
@RequestMapping(value = "/rewardPunishmentDetail/*")
@Controller
public class RewardPunishmentDetailController extends BaseController {

	
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;

	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IIterationService iterationService;

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public @ResponseBody
	void confirm(Model model,@RequestParam(required = false) Long id) {
		RewardPunishmentDetail rewardPunishmentDetail=rewardPunishmentDetailService.findById(id);
		rewardPunishmentDetail.setStatus(RewardPunishmentStatus.已确认.getValue());
		rewardPunishmentDetailService.update(rewardPunishmentDetail);
	}
	
	@RequestMapping(value = "/addRewardPunishmentDetail", method = RequestMethod.GET)
	public String dailyWorkList(Model model) {
		List<RewardPunishmentType> list = rewardPunishmentTypeService.findAllNeedToShow();
		model.addAttribute("list", list);
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("todayDate", formate.format(date));
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
		return "/rewardAndPunishment/publishRewardPunishment";
	}

	@RequestMapping(value = "/saveRewardPunishmentDetail",method = RequestMethod.POST)
	public @ResponseBody 
	void saveRewardPunishmentDetail(Model model,
			RewardPunishmentDetail rewardPunishmentDetail,
			@RequestParam(required = false) Short number,
			@RequestParam(required=false) Long pointTypeId,
			@RequestParam(required=false) Long[] relativePerson) {
		DateFormat formate1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (rewardPunishmentDetail.getDate() == ""
				|| rewardPunishmentDetail.getDate() == null) {
			rewardPunishmentDetail.setDate(formate.format(date));
		}
		RewardPunishmentType rewardPunishmentType = rewardPunishmentTypeService.findById(pointTypeId);
		rewardPunishmentDetail.setPointReason(rewardPunishmentType);
		Double basePoint=0.0;
		//扣分基数获取开始
		if(pointTypeId==10){  //代表迟到扣分项 需要特殊处理
			Iteration iteration=iterationService.chooseIteratoinByToday(rewardPunishmentDetail.getDate());
			if(iteration==null){
				throw new AppWebException("您选择的日期不属于任何的迭代或者管理员未录入当前迭代!");
			}
			List<RewardPunishmentDetail> rewardPunishmentDetailsList=rewardPunishmentDetailService.findByUserIdAndIteration(new Long(10),
					UserRequestContextInfo.getVisitorId(),iteration);			
			if(rewardPunishmentDetailsList.size()>=0 && rewardPunishmentDetailsList.size()<=2){    //针对迟到前三次扣一分
				basePoint=rewardPunishmentType.getPoint();
			}else if(rewardPunishmentDetailsList.size()>=3 &&  rewardPunishmentDetailsList.size()<=4){    //针对迟到4-5次扣两分
				basePoint=rewardPunishmentType.getPoint()*2;
			}else{
				basePoint=rewardPunishmentType.getPoint()*(rewardPunishmentDetailsList.size()+1)+3;
			}
		}else{
			basePoint=rewardPunishmentType.getPoint();
		}
		//扣分基数获取结束
		if (number != null) {
			rewardPunishmentDetail.setPoint(basePoint * number);
		} else {
			rewardPunishmentDetail.setPoint(basePoint);
		}
		rewardPunishmentDetail.setUser(userService
				.findById(UserRequestContextInfo.getVisitorId()));
		rewardPunishmentDetail.setUserName(userService
				.findById(UserRequestContextInfo.getVisitorId()).getName());
		rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(rewardPunishmentDetail.getDate()));
		rewardPunishmentDetailService.save(rewardPunishmentDetail);
		this.setPriorsRank(rewardPunishmentType);
		if(relativePerson!=null){
			for(Long userId:relativePerson){
				rewardPunishmentDetail.setId(rewardPunishmentDetail.getId()+1);
				rewardPunishmentDetail.setUser(userService.findById(userId));
				rewardPunishmentDetail.setUserName(userService.findById(userId).getName());
				rewardPunishmentDetail.setStatus(RewardPunishmentStatus.待确认.getValue());
				rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(rewardPunishmentDetail.getDate()));
				rewardPunishmentDetailService.save(rewardPunishmentDetail);
				this.setPriorsRank(rewardPunishmentType);
			}
		}
	}
	
	public void setPriorsRank(RewardPunishmentType rewardPunishmentType){
		if(rewardPunishmentType.getPriorsRank()==null){
			rewardPunishmentType.setPriorsRank(1);
		}else{
			rewardPunishmentType.setPriorsRank(rewardPunishmentType.getPriorsRank().intValue()+1);
		}
		rewardPunishmentTypeService.save(rewardPunishmentType);		
	}
	

	@RequestMapping(value = "/rewardPunishmentDetailList", method = RequestMethod.GET)
	public String rewardPunishmentDetailList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 50;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		if (startTime == null || startTime == "") {
			startTime = today;
		}
		if (endTime == null || endTime == "") {
			endTime = today;
		}
		Page<RewardPunishmentDetail> page = rewardPunishmentDetailService.findOnePage(name,
						startTime, endTime, pageIndex, pageSize);
		model.addAttribute("isTurn", true);
		model.addAttribute("name", name);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("page", page);
		model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
		return "/rewardAndPunishment/publishRewardPunishmentList";
	}

	@RequestMapping(value = "/rewardPunishmentDetailListToday", method = RequestMethod.GET)
	public String rewardPunishmentDetailListToday(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 100;

		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		if (startTime == null || startTime == "") {
			startTime = today;
		}
		if (endTime == null || endTime == "") {
			endTime = today;
		}
		Page<RewardPunishmentDetail> page = rewardPunishmentDetailService.findOnePage(name,
						startTime, endTime, pageIndex, pageSize);
		model.addAttribute("isTurn", true);
		model.addAttribute("name", name);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("page", page);
		model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
		return "/rewardAndPunishment/publishRewardPunishmentList";
	}
	
	@RequestMapping(value = "/checkRewardPunishmentStatistics",method = RequestMethod.POST)
	public @ResponseBody 
	boolean checkRewardPunishmentStatistics(Model model) {
		User user=userService.findByIsMaster(true);
		if(UserRequestContextInfo.getVisitorId()==user.getId().longValue()){
			return true;
		}else{
		return false;
		}
	}
	
	@RequestMapping(value = "/finalAutoComfirm",method = RequestMethod.POST)
	public @ResponseBody 
	void finalAutoComfirm(Model model) {
		List<RewardPunishmentDetail> list=rewardPunishmentDetailService.findByStatus(RewardPunishmentStatus.待确认.getValue());
		for(RewardPunishmentDetail rewardPunishmentDetail:list){
			rewardPunishmentDetail.setStatus(RewardPunishmentStatus.已确认.getValue());
			rewardPunishmentDetailService.update(rewardPunishmentDetail);
		}
	}

}
