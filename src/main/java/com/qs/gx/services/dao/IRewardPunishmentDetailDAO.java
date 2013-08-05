/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.permission.user.model.User;

/**
 * RewardPunishmentDetail DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-10
 */
public interface IRewardPunishmentDetailDAO extends
		IBaseDAO<RewardPunishmentDetail, Long> {
	@Query("from RewardPunishmentDetail r where (r.date >=:startTime and r.date <=:endTime) or (r.status=0)")
	Page<RewardPunishmentDetail> findOnePage(
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("from RewardPunishmentDetail r where (r.user.name like:name and r.date >=:startTime and r.date <=:endTime) or (r.status=0)")
	Page<RewardPunishmentDetail> findOnePageByName(@Param("name") String name,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("from RewardPunishmentDetail r where r.date =:date")
	Page<RewardPunishmentDetail> findOnePageToday(@Param("date") String today,
			Pageable paging);

	@Query("from RewardPunishmentDetail r where r.user.id =:userId and r.date >=:startTime and r.date <=:endTime")
	Page<RewardPunishmentDetail> findOnePageForCommonUser(
			@Param("userId") Long vistorId,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("from RewardPunishmentDetail r where r.pointReason.id=:type and r.user.id =:userId and r.date>=:startTime and r.date<=:endTime")
	List<RewardPunishmentDetail> findByUserIdAndIteration(
			@Param("type") Long type, @Param("userId") Long visitorId,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime);

	List<RewardPunishmentDetail> findByStatus(Short status);

	@Query("from RewardPunishmentDetail r where r.status=:status and r.date<=:yesterday ")
	List<RewardPunishmentDetail> findByStatusAndTime(
			@Param("status") Short status, @Param("yesterday") String yesterday);

	@Query("from RewardPunishmentDetail r where r.pointReason in :rewardPunishmentTypeList and r.iteration=:iteration ")
	List<RewardPunishmentDetail> findByIterationAndType(
			@Param("iteration") Iteration iteration,
			@Param("rewardPunishmentTypeList") List<RewardPunishmentType> rewardPunishmentTypeList);
	
	
	@Query("from RewardPunishmentDetail r where r.user=:user and  r.pointReason=:rewardPunishmentType and r.iteration=:iteration ")
	List<RewardPunishmentDetail> findByUserIdAndIterationAndType(
			@Param("rewardPunishmentType")RewardPunishmentType rewardPunishmentType, @Param("user")User user,
			@Param("iteration")Iteration iteration);

}
