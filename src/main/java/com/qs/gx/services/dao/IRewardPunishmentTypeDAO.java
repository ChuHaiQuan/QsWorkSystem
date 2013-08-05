/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.RewardPunishmentType;

/**
 * RewardPunishmentType DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
public interface IRewardPunishmentTypeDAO extends
		IBaseDAO<RewardPunishmentType, Long> {
	@Query("from RewardPunishmentType r where r.content like :pointReason")
	List<RewardPunishmentType> findByContent(
			@Param("pointReason") String pointReason);

	@Query("from RewardPunishmentType r where r.status is null order by r.priorsRank desc")
	List<RewardPunishmentType> findAllNeedToShow();

	@Query("from RewardPunishmentType r where r.content=:pointReason")
	List<RewardPunishmentType> findByContentEqual(@Param("pointReason")String pointReason);

}
