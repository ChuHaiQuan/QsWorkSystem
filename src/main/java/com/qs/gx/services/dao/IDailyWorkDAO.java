/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.DailyWork;
import com.qs.permission.user.model.User;

/**
 * DailyWork DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-06
 */
public interface IDailyWorkDAO extends IBaseDAO<DailyWork, Long> {
	@Query("from DailyWork d where d.user.id =:userId and d.publishTime >=:startTime and d.publishTime <=:endTime and d.completeStatus !=5")
	Page<DailyWork> findOnePageForCommonUser(@Param("userId") Long userId,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("from DailyWork d where (d.user.name like :name or d.dependencyProject.typeName like :name) and d.publishTime >=:startTime and d.publishTime <=:endTime and d.completeStatus !=5")
	Page<DailyWork> findOnePageByName(@Param("name") String name,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("from DailyWork d where d.user.name =:name and d.publishTime like :date")
	Page<DailyWork> findByNameToday(@Param("name") String name,
			@Param("date") String date);

	@Query("from DailyWork d where d.publishTime >=:startTime and d.publishTime <=:endTime and d.completeStatus !=5")
	Page<DailyWork> findOnePage(@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

	@Query("select distinct d.user from DailyWork d where d.completeStatus in :completeStatus  and  d.publishTime =:date  ")
	List<User> findByCompleteStatusAndDate(
			@Param("completeStatus") List<Short> statusList,
			@Param("date") String date);

	@Query("from DailyWork d where d.user.id =:userId and (d.user.name like :name or d.dependencyProject.typeName like :name) and d.publishTime >=:startTime and d.publishTime <=:endTime and d.completeStatus !=5")
	Page<DailyWork> findOnePageForCommonUserByName(
			@Param("userId") Long userId, @Param("name") String name,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable pageRequest);

}
