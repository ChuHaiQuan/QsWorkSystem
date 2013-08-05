/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IRewardPunishmentStasticsService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.permission.user.service.IUserService;



/**
 * RewardPunishmentStastics Controller.
 * @author chuhaiquan
 * @since 2012-12-28
 */
@RequestMapping(value = "/rewardPunishmentStastics/*")
@Controller
public class RewardPunishmentStasticsController extends BaseController {
	
	@Autowired
	private IRewardPunishmentStasticsService rewardPunishmentStasticsService;
	
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;
	
	@Autowired
	private IIterationService iterationService;
	
	
	@RequestMapping(value = "/getThisIterator", method = RequestMethod.GET)
	public @ResponseBody
	List<String> findByIteration(Model model) {
		List<String> list=null;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		Iteration iteration=iterationService.chooseIteratoinByToday(today);
		if(iteration!=null){
			list=new ArrayList<String>();
			list.add(iteration.getStartTime());
			list.add(iteration.getEndTime());
		}
		return list;
	}
	
	@RequestMapping(value = "/findByIteration", method = RequestMethod.GET)
	public @ResponseBody
	List<RewardPunishmentStasticsGroupByUserNameDto> findByIteration(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 30;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		Iteration iteration=iterationService.chooseIteratoinByToday(today);
		Page<RewardPunishmentStasticsGroupByUserNameDto> page=null;
		if(iteration!=null){
			page = rewardPunishmentStasticsService.findForManager(iteration.getStartTime(), iteration.getEndTime(), null, pageIndex, pageSize);
		}
		if(page!=null)
		return page.getContent();
		else
			return null;
	}
	/**
	 * 人员奖惩统计按时间和用户名
	 * @param model
	 * @param pageIndex
	 * @param pageSize
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/rewardPunishmentStasticsList", method = RequestMethod.GET)
	public String rewardPunishmentStasticsList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 20;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		if (StringUtils.isEmpty(startTime)) {
			startTime = today;
		}
		if (StringUtils.isEmpty(endTime)) {
			endTime = today;
		}
		Page<RewardPunishmentStasticsGroupByUserNameDto> page = rewardPunishmentStasticsService.findForManager(startTime, endTime, name, pageIndex, pageSize);
		model.addAttribute("isTurn", true);
		model.addAttribute("name", name);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("page", page);
		return "/rewardPunishmentStastics/rewardPunishmentStasticsList";
	}
	
		/**
		 * 
		 * 奖惩统计分类汇总查询
		 * @param model
		 * @param name
		 * @param startTime
		 * @param endTime
		 * @return
		 * @throws ParseException
		 */
	@RequestMapping(value = "/rewardPunishmentStasticsByCategoty", method = RequestMethod.GET)
	public String rewardPunishmentStasticsByCategoty(Model model,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) throws ParseException {
		  Calendar calendar  =   new  GregorianCalendar();
		  calendar.set( Calendar.DATE,  1 );
		  SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( "yyyy-MM-dd" );
		  String firstDayOfMonth= simpleFormate.format(calendar.getTime());
		  List<Long> longList=new ArrayList<Long>();
			if (StringUtils.isEmpty(startTime)) {
				startTime = firstDayOfMonth;
			}
			if (StringUtils.isEmpty(endTime)) {
				endTime = simpleFormate.format(new Date());
			}
			if(StringUtils.isNotEmpty(name)){
				name=name.trim();
				List<RewardPunishmentType> rewardPunishment=rewardPunishmentTypeService.findByContent(name);
				for(RewardPunishmentType rewardPunishmentTypeBean:rewardPunishment){
					longList.add(rewardPunishmentTypeBean.getId());
				}
			}
			List<Object[]> list=rewardPunishmentStasticsService.rewardPunishmentStasticsByCategoty(startTime,endTime,longList);
			model.addAttribute("list", list);
			model.addAttribute("name", name);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return "/rewardPunishmentStastics/rewardPunishmentStasticsCategoryList";
	}
	
	
	@RequestMapping(value = "/rewardPunishmentStasticsByCategotyDetail", method = RequestMethod.GET)
	public String rewardPunishmentStasticsByCategotyDetail(Model model,
			@RequestParam  String content,
			@RequestParam  String startTime,
			@RequestParam  String endTime) throws ParseException, UnsupportedEncodingException {
		  List<Long> longList=new ArrayList<Long>();
				content=URLDecoder.decode(URLDecoder.decode(content, "utf8"), "utf8");
				content=content.trim();
				List<RewardPunishmentType> rewardPunishment=rewardPunishmentTypeService.findByContentEqual(content);
				for(RewardPunishmentType rewardPunishmentTypeBean:rewardPunishment){
					longList.add(rewardPunishmentTypeBean.getId());
				}
			List<Object[]> list=rewardPunishmentStasticsService.rewardPunishmentStasticsByCategotyDetail(startTime,endTime,longList);
			model.addAttribute("list", list);
			return "/rewardPunishmentStastics/rewardPunishmentStasticsCategoryDetailList";
	}
	
	@RequestMapping(value = "/rewardPunishmentStasticsUserDetailInfo", method = RequestMethod.GET)
	public String rewardPunishmentStasticsUserDetailInfo(Model model,
			@RequestParam  String userName,
			@RequestParam  String startTime,
			@RequestParam  String endTime) throws ParseException, UnsupportedEncodingException {
			userName=URLDecoder.decode(URLDecoder.decode(userName, "utf8"), "utf8");
			userName=userName.trim();
			List<Object[]> list=rewardPunishmentStasticsService.rewardPunishmentStasticsUserDetailInfo(startTime, endTime, userName);
			model.addAttribute("list", list);
			return "/rewardPunishmentStastics/rewardPunishmentStasticsUserDetailInfo";
	}
	
}
