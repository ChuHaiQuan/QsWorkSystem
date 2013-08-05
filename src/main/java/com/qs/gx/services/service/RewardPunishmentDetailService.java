/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

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
import com.qs.gx.services.dao.IRewardPunishmentDetailDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.support.RewardPunishmentStatus;
import com.qs.permission.user.model.User;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
@Service
@Transactional
public class RewardPunishmentDetailService extends
		BaseDAOService<RewardPunishmentDetail, Long> implements
		IRewardPunishmentDetailService {

	private IRewardPunishmentDetailDAO rewardPunishmentDetailDAO;

	@Autowired
	public RewardPunishmentDetailService(
			IRewardPunishmentDetailDAO rewardPunishmentDetailDAO) {
		super(rewardPunishmentDetailDAO);
		this.rewardPunishmentDetailDAO = rewardPunishmentDetailDAO;
	}

	@Override
	public Page<RewardPunishmentDetail> findOnePage(String name,
			String startTime, String endTime, Integer pageIndex,
			Integer pageSize) {
		Page<RewardPunishmentDetail> page = null;
		if (name != null && name != "") {
			name = "%" + name + "%";
			page = rewardPunishmentDetailDAO.findOnePageByName(name, startTime,
					endTime, new PageRequest(pageIndex, pageSize, new Sort(
							new Sort.Order(Sort.Direction.ASC, "status"),
							new Sort.Order(Sort.Direction.DESC, "date"),
							new Sort.Order(Sort.Direction.DESC, "user"))));
		} else {
			page = rewardPunishmentDetailDAO.findOnePage(startTime, endTime,
					new PageRequest(pageIndex, pageSize, new Sort(
							new Sort.Order(Sort.Direction.ASC, "status"),
							new Sort.Order(Sort.Direction.DESC, "date"),
							new Sort.Order(Sort.Direction.DESC, "user"))));
		}
		return page;
	}

	@Override
	public Page<RewardPunishmentDetail> findOnePageToday(String today,
			Integer pageIndex, Integer pageSize) {
		Page<RewardPunishmentDetail> page = rewardPunishmentDetailDAO
				.findOnePageToday(today, new PageRequest(pageIndex, pageSize,
						new Sort(new Sort.Order(Sort.Direction.DESC, "user"))));
		return page;
	}

	@Override
	public Page<RewardPunishmentDetail> findOnePageForCommonUser(
			Long visitorId, String startTime, String endTime,
			Integer pageIndex, Integer pageSize) {
		Page<RewardPunishmentDetail> page = rewardPunishmentDetailDAO
				.findOnePageForCommonUser(visitorId, startTime, endTime,
						new PageRequest(pageIndex, pageSize, new Sort(
								new Sort.Order(Sort.Direction.DESC, "user"),
								new Sort.Order(Sort.Direction.DESC, "date"))));
		return page;
	}

	@Override
	public List<RewardPunishmentDetail> findByUserIdAndIteration(Long type,Long visitorId,Iteration iteration) {
		return rewardPunishmentDetailDAO.findByUserIdAndIteration(type,visitorId,iteration.getStartTime(),iteration.getEndTime());
	}
	
	@Scheduled(cron = "0 59 23 * * ?")
	@Override
	public void autoCheckConfirmRewardOrPublish() {
		 Date date = new Date(System.currentTimeMillis()-86400000);
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 String yesterday = format.format(date);
		 List<RewardPunishmentDetail> list=rewardPunishmentDetailDAO.findByStatusAndTime(RewardPunishmentStatus.待确认.getValue(),yesterday);
		 for(RewardPunishmentDetail rewardPunishmentDetail:list){
			 rewardPunishmentDetail.setStatus(RewardPunishmentStatus.已确认.getValue());
			 this.update(rewardPunishmentDetail);
		 }
	}

	@Override
	public List<RewardPunishmentDetail> findByStatus(Short status) {
		return rewardPunishmentDetailDAO.findByStatus(status);
	}

	@Override
	public List<RewardPunishmentDetail> findByIterationAndType(
			Iteration iteration,List<RewardPunishmentType> rewardPunishmentTypeList) {
		return rewardPunishmentDetailDAO.findByIterationAndType(iteration,rewardPunishmentTypeList);
	}

	@Override
	public List<RewardPunishmentDetail> findByUserIdAndIterationAndType(
			RewardPunishmentType rewardPunishmentType, User user,
			Iteration iteration) {
		return rewardPunishmentDetailDAO.findByUserIdAndIterationAndType(rewardPunishmentType, user,iteration);
	}

}
