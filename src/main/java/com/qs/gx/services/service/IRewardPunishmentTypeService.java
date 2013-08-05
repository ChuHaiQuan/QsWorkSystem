/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.RewardPunishmentType;

/**
 * RewardPunishmentType Service Interface.
 * @author chuhaiquan
 * @since 2012-12-10
 */
public interface IRewardPunishmentTypeService extends
		IBaseDAOService<RewardPunishmentType, Long> {

	List<RewardPunishmentType> findByContent(String pointReason);
	List<RewardPunishmentType> findByContentEqual(String pointReason);

	List<RewardPunishmentType> findAllNeedToShow();

}