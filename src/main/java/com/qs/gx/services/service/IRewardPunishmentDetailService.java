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
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.permission.user.model.User;

/**
 * RewardPunishmentDetail Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
public interface IRewardPunishmentDetailService extends
		IBaseDAOService<RewardPunishmentDetail, Long> {

	Page<RewardPunishmentDetail> findOnePage(String name, String startTime,
			String endTime, Integer pageIndex, Integer pageSize);

	Page<RewardPunishmentDetail> findOnePageToday(String today,
			Integer pageIndex, Integer pageSize);

	Page<RewardPunishmentDetail> findOnePageForCommonUser(Long visitorId,
			String startTime, String endTime, Integer pageIndex,
			Integer pageSize);

	List<RewardPunishmentDetail> findByUserIdAndIteration(Long type,
			Long visitorId, Iteration iteration);

	public void autoCheckConfirmRewardOrPublish();

	List<RewardPunishmentDetail> findByStatus(Short value);

	List<RewardPunishmentDetail> findByIterationAndType(Iteration iteration,
			List<RewardPunishmentType> rewardPunishmentTypeList);

	List<RewardPunishmentDetail> findByUserIdAndIterationAndType(
			RewardPunishmentType rewardPunishmentType, User user,
			Iteration iteration);

}