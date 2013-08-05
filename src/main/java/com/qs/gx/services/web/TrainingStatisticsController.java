/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qs.common.BeanUtilsEx;
import com.qs.core.exception.AppWebException;
import com.qs.core.web.BaseController;
import com.qs.gx.services.model.File;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.model.TrainingStatistics;
import com.qs.gx.services.model.TrainingStatisticsAttach;
import com.qs.gx.services.model.Turn;
import com.qs.gx.services.service.IFileService;
import com.qs.gx.services.service.IIterationService;
import com.qs.gx.services.service.IRewardPunishmentDetailService;
import com.qs.gx.services.service.IRewardPunishmentTypeService;
import com.qs.gx.services.service.ITrainingStatisticsAttachService;
import com.qs.gx.services.service.ITrainingStatisticsService;
import com.qs.gx.services.service.ITurnService;
import com.qs.gx.services.service.IUploadFile;
import com.qs.gx.services.service.TurnService;
import com.qs.gx.services.support.TrainingStatisticsAttachStatus;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * TrainingStatistics Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@RequestMapping(value = "/trainingStatistics/*")
@Controller
public class TrainingStatisticsController extends BaseController {

	@Autowired
	private ITrainingStatisticsService trainingStatisticsService;
	@Autowired
	private ITurnService turnService;
	@Autowired
	private ITrainingStatisticsAttachService trainingStatisticsAttachService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private IUploadFile uploadFileService;
	@Autowired
	private IIterationService iterationService;

	@RequestMapping(value = "/addJoinNumberById", method = RequestMethod.POST)
	public @ResponseBody
	Integer addJoinNumberById(Model model,
			TrainingStatistics trainingStatistics) {
		TrainingStatistics trainingStatisticsOld= trainingStatisticsService.findById(trainingStatistics.getId());
		trainingStatisticsOld.setJoinNumber(trainingStatistics.getJoinNumber());
		trainingStatisticsService.save(trainingStatisticsOld);
		return trainingStatistics.getJoinNumber();
	}
	@RequestMapping(value = "/trainingStatisticsList", method = RequestMethod.GET)
	public String trainingStatisticsList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 10;
		Page<TrainingStatistics> page = trainingStatisticsService.findOnePage(
				pageIndex, pageSize, name);
		User user = userService.findByIsMaster(true);
		if (user != null) {
			if (UserRequestContextInfo.getVisitorId() == user.getId()
					.longValue()) {
				model.addAttribute("isTurn", true);
			}			
		}
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		return "/trainingStatistics/trainingStatisticsList";
	}

	@RequestMapping(value = "/publishTrainingStatistics", method = RequestMethod.GET)
	public String publishTrainingStatistics(Model model,
			@RequestParam(required = false) Integer type) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		TrainingStatistics trainingStatistics = trainingStatisticsService
				.findByTimeAndType(formate.format(date), type);
		model.addAttribute("trainingStatistics", trainingStatistics);
		List<Turn> list=turnService.findByDateAndCategoryForList(formate.format(new Date()));
		if(list.size()>0 && list.get(0).getUser().getId().longValue()==UserRequestContextInfo.getVisitorId()){
			model.addAttribute("status", "turn");  //轮值记录
			model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
			model.addAttribute("userList", userService.findAll());
		};
		return "/trainingStatistics/publishTrainingStatistics";
	}

	@RequestMapping(value = "/publishTheme", method = RequestMethod.GET)
	public String publishTheme(Model model) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("todayDate", formate.format(date));
		return "/trainingStatistics/publishTheme";
	}

	@RequestMapping(value = "/saveTrainingStatistics", method = RequestMethod.POST)
	public @ResponseBody
	String saveRewardPunishmentDetail(Model model,HttpServletResponse response,
			TrainingStatistics trainingStatistics,
			Long[] relativePerson,
			@RequestParam(required = false) Short status,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String doPointName,
			@RequestParam(required = false) MultipartFile files
			)
			throws SecurityException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		double point = 0.0;
		int pointnNumber = 0;
		String newComment = "";
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		// 发布主题
		if (status == null) {
			if (trainingStatistics.getDate() == ""
					|| trainingStatistics.getDate() == null) {
				trainingStatistics.setDate(formate.format(date));
			}
			//上传文件开始
			File fileObj=null;
			if(files!=null){
				String urlStr=null;
				if(trainingStatistics.getTrainingType().intValue()==0){
					String [] s = {"agilityTraining"};
					 urlStr=uploadFileService.uploadFile(files, s);
				}else{ 
					String [] s = {"routineTraining"};
					urlStr=uploadFileService.uploadFile(files, s);
				}
				fileObj=new File();
				fileObj.setPath(urlStr);
				fileObj.setName(files.getOriginalFilename());
				fileObj.setUserId(userService.findById(UserRequestContextInfo.getVisitorId()).getId());
				fileObj.setPunishTime(formate.format(date));
				fileService.save(fileObj);
			}
			//上传文件结束			
			//新增培训记录开始
			trainingStatistics.setUser(userService.findById(UserRequestContextInfo.getVisitorId()));
			trainingStatistics.setUserName(userService.findById(UserRequestContextInfo.getVisitorId()).getName());
			trainingStatistics.setPointNumber(0);
			trainingStatistics.setUserName(UserRequestContextInfo.getVisitorName());
			if(fileObj!=null){
				trainingStatistics.setFile(fileObj);
			}
			trainingStatisticsService.save(trainingStatistics);
			RewardPunishmentDetail rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setUser(userService.findById(UserRequestContextInfo.getVisitorId()));
			rewardPunishmentDetail.setUserName(userService.findById(UserRequestContextInfo.getVisitorId()).getName());
			rewardPunishmentDetail.setDate(trainingStatistics.getDate());
			rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(trainingStatistics.getDate()));
			//新增培新记录结束
			if(trainingStatistics.getTrainingType()==1){
				RewardPunishmentType rewardPunishmentTypeCG=rewardPunishmentTypeService.findById(new Long(48));
				rewardPunishmentDetail.setPointReason(rewardPunishmentTypeCG);
				rewardPunishmentDetail.setPoint(rewardPunishmentTypeCG.getPoint());
				rewardPunishmentDetail.setRemark(rewardPunishmentTypeCG.getContent());
				if(rewardPunishmentTypeCG.getPriorsRank()==null){
					rewardPunishmentTypeCG.setPriorsRank(1);
				}else{
					rewardPunishmentTypeCG.setPriorsRank(rewardPunishmentTypeCG.getPriorsRank().intValue()+1);
				}
				rewardPunishmentTypeService.save(rewardPunishmentTypeCG);
				
			}else if(trainingStatistics.getTrainingType()==0){
				RewardPunishmentType rewardPunishmentTypeMJ=rewardPunishmentTypeService.findById(new Long(49));
				rewardPunishmentDetail.setPointReason(rewardPunishmentTypeMJ);
				rewardPunishmentDetail.setPoint(rewardPunishmentTypeMJ.getPoint());
				rewardPunishmentDetail.setRemark(rewardPunishmentTypeMJ.getContent());
				if(rewardPunishmentTypeMJ.getPriorsRank()==null){
					rewardPunishmentTypeMJ.setPriorsRank(1);
				}else{
					rewardPunishmentTypeMJ.setPriorsRank(rewardPunishmentTypeMJ.getPriorsRank().intValue()+1);
				}
				rewardPunishmentTypeService.save(rewardPunishmentTypeMJ);
			}else{}
			rewardPunishmentDetailService.save(rewardPunishmentDetail);
		} else if (status == 0 || status == 1) {
			List<Turn> list=turnService.findByDateAndCategoryForList(formate.format(new Date()));
			TrainingStatistics trainingStatisticsOld = trainingStatisticsService
					.findById(id);
			if((list.size()>0 && list.get(0).getUser().getId().longValue()==UserRequestContextInfo.getVisitorId())){
				if(relativePerson==null){throw new AppWebException("请选择参与人数!!");}
				else{
					trainingStatisticsOld.setJoinNumber(relativePerson.length+1);
					trainingStatisticsService.update(trainingStatisticsOld);
				}
			}

			if( trainingStatisticsOld.getJoinNumber()==null|| trainingStatisticsOld.getJoinNumber().intValue()==0){
				return "1";    //表示轮值人员还未录入培训参与人数
			}
			TrainingStatisticsAttach trainingStatisticsAttachOld = trainingStatisticsAttachService
					.findByDateAndDoPointName(formate.format(date),UserRequestContextInfo.getVisitorName(),
							 trainingStatistics.getTrainingType());
			//发布人不能打分
			if(trainingStatisticsOld.getUserName().equals(userService.findById(UserRequestContextInfo.getVisitorId()).getName())){
				throw new AppWebException("fuck,培训人员是不能打分的!!!");
			}else if (trainingStatisticsAttachOld != null) {
				  return "2"; //表示该人应经打过分了不能重复打分
			} else {
				//打分人信息录入---start----
				if((list.size()>0 && list.get(0).getUser().getId().longValue()==UserRequestContextInfo.getVisitorId())){
					TrainingStatisticsAttach trainingStatisticsAttach = new TrainingStatisticsAttach();
					trainingStatisticsAttach.setDate(formate.format(date));
					trainingStatisticsAttach.setType(trainingStatistics
							.getTrainingType());
					trainingStatisticsAttach.setStatus(TrainingStatisticsAttachStatus.已打分.getValue());
					trainingStatisticsAttach.setUserName(UserRequestContextInfo.getVisitorName());
					trainingStatisticsAttach.setTrainingStatistics(trainingStatisticsOld);
					trainingStatisticsAttachService.save(trainingStatisticsAttach);					
					for(Long userId:relativePerson){
						TrainingStatisticsAttach userTrainingStatisticsAttach = new TrainingStatisticsAttach();
						userTrainingStatisticsAttach.setDate(formate.format(date));
						userTrainingStatisticsAttach.setType(trainingStatistics
								.getTrainingType());
						userTrainingStatisticsAttach.setStatus(TrainingStatisticsAttachStatus.未打分.getValue());
						userTrainingStatisticsAttach.setUserName(userService.findById(userId).getName());
						userTrainingStatisticsAttach.setTrainingStatistics(trainingStatisticsOld);
						trainingStatisticsAttachService.save(userTrainingStatisticsAttach);
					}		
				}else{
					TrainingStatisticsAttach trainingStatisticsAttachOrigin=trainingStatisticsAttachService.findOriginInfoByDateAndDoPointName(formate.format(date),UserRequestContextInfo.getVisitorName(),
							 trainingStatistics.getTrainingType());
					if(trainingStatisticsAttachOrigin==null){
						throw new AppWebException("你没有打分的资格!");
					}else{
						trainingStatisticsAttachOrigin.setStatus(TrainingStatisticsAttachStatus.已打分.getValue());
						trainingStatisticsAttachService.update(trainingStatisticsAttachOrigin);
					}
				}
		//打分人信息录入---end----
				//培训分数开始统计
				if (trainingStatistics.getPoint() > 100) {
					trainingStatistics.setPoint(100.0);
				} else if (trainingStatistics.getPoint() < 20) {
					trainingStatistics.setPoint(20.0);
				}
				
				if (trainingStatisticsOld.getPoint() != null) {
					point = trainingStatisticsOld.getPoint().doubleValue()
							* trainingStatisticsOld.getPointNumber().intValue()
							+ trainingStatistics.getPoint().doubleValue();
				} else {
					point = trainingStatistics.getPoint().doubleValue();
				}
				pointnNumber = trainingStatisticsOld.getPointNumber()
						.intValue();
				if (trainingStatisticsOld.getComment() == null
						&& !trainingStatistics.getComment().equals("") ) {
					newComment = trainingStatistics.getComment();
				} else if (trainingStatisticsOld.getComment() != null
						&& trainingStatistics.getComment().equals("")) {
					newComment = trainingStatisticsOld.getComment();
				} else if (trainingStatisticsOld.getComment() == null
						&& trainingStatistics.getComment().equals("")) {
					newComment = null;
				} else {
					newComment = trainingStatisticsOld.getComment() + ","
							+ trainingStatistics.getComment();
				}
				trainingStatistics.setPoint(new Double(point
						/ (pointnNumber + 1)));
				trainingStatistics
						.setPointNumber(new Integer(pointnNumber + 1));
				
				trainingStatistics.setComment(newComment);
				BeanUtilsEx.copyPropertiesWithoutNullValues(
						trainingStatisticsOld, trainingStatistics);
				trainingStatisticsService.save(trainingStatisticsOld);
			}
		}else{}
		return "";
	}
	
}
