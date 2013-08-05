/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IRewardPunishmentTypeDAO;
import com.qs.gx.services.model.RewardPunishmentType;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
@Service
@Transactional
public class RewardPunishmentTypeService extends
		BaseDAOService<RewardPunishmentType, Long> implements
		IRewardPunishmentTypeService {

	private IRewardPunishmentTypeDAO rewardPunishmentTypeDAO;

	@Autowired
	public RewardPunishmentTypeService(
			IRewardPunishmentTypeDAO rewardPunishmentTypeDAO) {
		super(rewardPunishmentTypeDAO);
		this.rewardPunishmentTypeDAO = rewardPunishmentTypeDAO;
	}

	@Override
	public List<RewardPunishmentType> findByContent(String pointReason) {
		pointReason="%"+pointReason+"%";
		List<RewardPunishmentType> rewardPunishmentType = rewardPunishmentTypeDAO
				.findByContent(pointReason);
		return rewardPunishmentType;
	}

	@Override
	public List<RewardPunishmentType> findAllNeedToShow() {
		List<RewardPunishmentType> list=rewardPunishmentTypeDAO.findAllNeedToShow();
		return list;
	}

	@Override
	public List<RewardPunishmentType> findByContentEqual(String pointReason) {
		return	rewardPunishmentTypeDAO.findByContentEqual(pointReason);
	}

}
