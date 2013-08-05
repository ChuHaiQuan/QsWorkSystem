/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentStastics;
import com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto;

/**
 * RewardPunishmentStastics Service Interface.
 * @author chuhaiquan
 * @since 2012-12-28
 */
public interface IRewardPunishmentStasticsService extends
		IBaseDAOService<RewardPunishmentStastics, Long> {

	Page<RewardPunishmentStasticsGroupByUserNameDto> findForManager(String startTime,
			String endTime, String name, Integer pageIndex, Integer pageSize);

	Page<RewardPunishmentStastics> findForNormal(String startTime,
			String endTime, Integer pageIndex, Integer pageSize);

	List<Object[]> rewardPunishmentStasticsByCategoty(String startTime,
			String endTime, List<Long> IdList);

	List<Object[]> rewardPunishmentStasticsByCategotyDetail(String startTime,
			String endTime, List<Long> longList);
	/**
	 * 人员奖惩明细
	 * @param startTime
	 * @param endTime
	 * @param userName
	 * @return
	 */
	List<Object[]> rewardPunishmentStasticsUserDetailInfo(String startTime,
			String endTime, String userName);

	List<Object[]> findTheHighesAlltPointUsers(Iteration iteration);
	
	List<Object[]> findTheAlltPointDetails(Iteration iteration);

	List<Object[]> findTheHighestLowPointUsers(Iteration iteration);
	
	List<Object[]> findTheLowPointDetails(Iteration iteration);

	List<Object[]> findTheHighestBugPointUsers(Iteration iteration);
	
	List<Object[]> findTheBugPointDetails(Iteration iteration);

}