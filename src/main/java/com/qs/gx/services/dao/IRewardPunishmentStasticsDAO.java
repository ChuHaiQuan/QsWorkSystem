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
import com.qs.gx.services.model.RewardPunishmentStastics;
import com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto;

/**
 * RewardPunishmentStastics DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-28
 */
public interface IRewardPunishmentStasticsDAO extends
		IBaseDAO<RewardPunishmentStastics, Long> {
	@Query("from RewardPunishmentStastics r where r.user.id= :userId and r.date>= :startTime and r.date<= :endTime")
	Page<RewardPunishmentStastics> findForNormal(@Param("userId") Long userId,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);
	

	@Query("select new com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto(r.userName,sum(r.losePoint),sum(r.addPoint),sum(r.allPoint))  "
			+ " from  RewardPunishmentStastics r where r.date>= :startTime "
			+ " and r.date<= :endTime and r.userName like :name and (r.user.id in (select id from User where status is null ))  "
			+ " group by r.userName order by sum(r.allPoint) desc")
	Page<RewardPunishmentStasticsGroupByUserNameDto> findForManagerByNameAndStartAndEndTime(
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("name") String name,
			Pageable paging);

	@Query("select new com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto(r.userName,sum(r.losePoint),sum(r.addPoint),sum(r.allPoint))  "
			+ " from  RewardPunishmentStastics r where r.date>= :startTime"
			+ " and r.date<= :endTime and (r.user.id in (select id from User where status is null )) " 
			+ " group by r.userName order by sum(r.allPoint) desc")
	Page<RewardPunishmentStasticsGroupByUserNameDto> findForManagerByStartAndEndTime(
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);
	
	
	@Query("select new com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto(r.userName,sum(r.losePoint),sum(r.addPoint),sum(r.allPoint))  "
			+ " from  RewardPunishmentStastics r where r.date>= :startTime"
			+ " and r.date<= :endTime and sum(r.allPoint)=:allPoint)" 
			+ " group by r.userName  order by sum(r.allPoint)")
	List<RewardPunishmentStasticsGroupByUserNameDto> findTheHighesAlltPointUsers(
			@Param("startTime") String startTime,@Param("allPoint") Long allPoint,
			@Param("endTime") String endTime);
	
	@Query("select new com.qs.gx.services.model.RewardPunishmentStasticsGroupByUserNameDto(r.userName,sum(r.losePoint),sum(r.addPoint),sum(r.allPoint))  "
			+ " from  RewardPunishmentStastics r where r.date>= :startTime"
			+ " and r.date<= :endTime and sum(r.losePoint)=:losePoint" 
			+ " group by r.userName  order by sum(r.allPoint)")
	List<RewardPunishmentStasticsGroupByUserNameDto> findTheHighestLowPointUsers(
			@Param("startTime") String startTime,@Param("losePoint") Long losePoint,
			@Param("endTime") String endTime);

}
