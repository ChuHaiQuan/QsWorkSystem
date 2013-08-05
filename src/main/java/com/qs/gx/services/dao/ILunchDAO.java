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
import com.qs.gx.services.model.Lunch;

/**
 * Lunch DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-11-07
 */
public interface ILunchDAO extends IBaseDAO<Lunch, Long> {

	@Query("from Lunch l ")
	public Page<Lunch> findApply(Pageable paging);

	@Query("from Lunch l where l.userName=:userName and l.dateNow like:dateNow")
	public List<Lunch> checkUserHaveApplyOrNot(
			@Param("userName") String userName, @Param("dateNow") String dateNow);

	@Query("from Lunch l where l.userName=:userName and l.dateNow >:startTime and l.dateNow <:dateN")
	public List<Lunch> checkUserBetWeenTwoTime(
			@Param("userName") String userName,
			@Param("startTime") String startTime, @Param("dateN") String dateN);

	@Query("from Lunch l")
	public Page<Lunch> findPageOrderByFoodName(Pageable paging);

	@Query("from Lunch l where l.dateTom like:dateToday")
	public Page<Lunch> findTodayOrders(@Param("dateToday") String dateToday,
			Pageable pageable);

	@Query("from Lunch l where l.userName like:userName ")
	public Page<Lunch> doSearchForOrderByTimeOrUserName(
			@Param("userName") String userName, Pageable paging);

	@Query("from Lunch l where (l.dateTom >:startTime or l.dateTom=:startTime) and (l.dateTom <:endTime or l.dateTom=:endTime)")
	public Page<Lunch> doSearchForOrderBetWeenStartTimeAndEndTime(
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, Pageable paging);

}
