/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.ITrainingStatisticsAttachDAO;
import com.qs.gx.services.dao.ITrainingStatisticsAttachPlusDAO;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.TrainingStatisticsAttach;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-17
 */
@Service
@Transactional
public class TrainingStatisticsAttachService extends
		BaseDAOService<TrainingStatisticsAttach, Long> implements
		ITrainingStatisticsAttachService {

	private ITrainingStatisticsAttachDAO trainingStatisticsAttachDAO;
	@Autowired
	private ITrainingStatisticsAttachPlusDAO trainingStatisticsAttachPlusDAO;
	@Autowired
	private IUserService userService;
	@Autowired
	private IIterationService iterationService;
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;

	@Autowired
	public TrainingStatisticsAttachService(
			ITrainingStatisticsAttachDAO trainingStatisticsAttachDAO) {
		super(trainingStatisticsAttachDAO);
		this.trainingStatisticsAttachDAO = trainingStatisticsAttachDAO;
	}

	@Override
	public TrainingStatisticsAttach findByDateAndDoPointName(String today,String vistorName,
			Integer trainingType) {
		TrainingStatisticsAttach trainingStatisticsAttach = trainingStatisticsAttachDAO
				.findByDateAndDoPointName(today, vistorName,trainingType);
		return trainingStatisticsAttach;
	}

	@Override
	public Page<TrainingStatisticsAttach> findByDate(String date,Integer pageIndex,Integer pageSize) {
		Page<TrainingStatisticsAttach> page=trainingStatisticsAttachDAO.findByDate(date,new PageRequest(pageIndex,pageSize,new Sort(new Sort.Order(Sort.Direction.DESC,"userName"))));
		return page;
	}

	@Override
	public TrainingStatisticsAttach findOriginInfoByDateAndDoPointName(
			String date, String visitorName, Integer trainingType) {
		return trainingStatisticsAttachDAO.findOriginInfoByDateAndDoPointName(date, visitorName,trainingType);
	}
	
	@Scheduled(cron = "0 58 23 * * ?")
	@Override
	public void autoPunishUnMakePointUsers() {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String today=format.format(new Date());
		List<Object> userIdObjectList=trainingStatisticsAttachPlusDAO.getUnMakePointUsersByDate(today);
		for(Object id:userIdObjectList){
			User user=userService.findById(new BigDecimal((BigInteger)id).longValue());
			RewardPunishmentDetail rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(today);
			rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(today));
			 rewardPunishmentDetail.setPointReason(rewardPunishmentTypeService.findById(new Long(25)));
			 rewardPunishmentDetail.setPoint(rewardPunishmentTypeService.findById(new Long(25)).getPoint());
			 rewardPunishmentDetail.setUser(user);
			 rewardPunishmentDetail.setUserName(user.getName());
			 rewardPunishmentDetail.setRemark("下次培训记得打分~~今天管理员代扣咯!!");
			 rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	}

	@Override
	public List<TrainingStatisticsAttach> findByDateAndStatus(String date,
			Short status) {
		return trainingStatisticsAttachDAO.findByDateAndStatus(date,status);
	}

}
