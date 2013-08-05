/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IRewardPunishmentStasticsDAO;
import com.qs.gx.services.dao.IRewardPunishmentStasticsPlusDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentStastics;
import com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto;
import com.qs.permission.core.support.UserRequestContextInfo;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2012-12-28
 */	
@Service
@Transactional
public class RewardPunishmentStasticsService extends BaseDAOService<RewardPunishmentStastics, Long> implements
		IRewardPunishmentStasticsService{

	@Autowired
	private IRewardPunishmentStasticsPlusDAO  rewardPunishmentStasticsPlusDAO;
	
	private IRewardPunishmentStasticsDAO rewardPunishmentStasticsDAO;

	@Autowired
	public RewardPunishmentStasticsService(
			IRewardPunishmentStasticsDAO rewardPunishmentStasticsDAO) {
		super(rewardPunishmentStasticsDAO);
		this.rewardPunishmentStasticsDAO = rewardPunishmentStasticsDAO;
	}

	@Override
	public Page<RewardPunishmentStasticsGroupByUserNameDto> findForManager(String startTime,
			String endTime, String name, Integer pageIndex, Integer pageSize) {
		Page<RewardPunishmentStasticsGroupByUserNameDto> page=null;
		if(StringUtils.isNotEmpty(name)){
			name="%"+name+"%";
			page=rewardPunishmentStasticsDAO.findForManagerByNameAndStartAndEndTime(startTime,endTime,name,
					new PageRequest(pageIndex,pageSize));
		}else{
			page=rewardPunishmentStasticsDAO.findForManagerByStartAndEndTime(startTime,endTime,
					new PageRequest(pageIndex,pageSize));
		}
		return page;
	}

	@Override
	public Page<RewardPunishmentStastics> findForNormal(String startTime,
			String endTime, Integer pageIndex, Integer pageSize) {
		Page<RewardPunishmentStastics> page=rewardPunishmentStasticsDAO.findForNormal(new Long(UserRequestContextInfo.getVisitorId()),startTime,endTime,new PageRequest(pageIndex,pageSize,
				new Sort(new Sort.Order(Sort.Direction.DESC,"date"),
						new Sort.Order(Sort.Direction.DESC,
								"user"))));
		return page;
	}

	@Override
	public List<Object[]> rewardPunishmentStasticsByCategoty(String startTime,
			String endTime, List<Long> IdList) {
		List<Object[]> list=rewardPunishmentStasticsPlusDAO.rewardPunishmentStasticsByCategoty( startTime, endTime,  IdList);
		return list;
	}

	@Override
	public List<Object[]> rewardPunishmentStasticsByCategotyDetail(
			String startTime, String endTime, List<Long> longList) {
		return rewardPunishmentStasticsPlusDAO.rewardPunishmentStasticsByCategotyDetail(startTime, endTime, longList);
	}

	@Override
	public List<Object[]> rewardPunishmentStasticsUserDetailInfo(
			String startTime, String endTime, String userName) {
		return rewardPunishmentStasticsPlusDAO.rewardPunishmentStasticsUserDetailInfo(startTime, endTime, userName);
	}

	@Override
	public List<Object[]> findTheHighesAlltPointUsers(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheHighesAlltPointUsers(iteration);
	}

	@Override
	public List<Object[]> findTheHighestLowPointUsers(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheHighestLowPointUsers(iteration);
	}

	@Override
	public List<Object[]> findTheHighestBugPointUsers(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheHighestBugPointUsers(iteration);
	}

	@Override
	public List<Object[]> findTheAlltPointDetails(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheAlltPointDetails(iteration);
	}

	@Override
	public List<Object[]> findTheLowPointDetails(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheLowPointDetails(iteration);
	}

	@Override
	public List<Object[]> findTheBugPointDetails(Iteration iteration) {
		return rewardPunishmentStasticsPlusDAO.findTheBugPointDetails(iteration);
	}
	
}
