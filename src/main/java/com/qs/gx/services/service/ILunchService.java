/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.Lunch;

/**
 * Lunch Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-11-07
 */
public interface ILunchService extends IBaseDAOService<Lunch, Long> {

	Page<Lunch> findAllApplyByPageSizeAndPageIndex(Integer pageIndex,
			Integer pageSize);

	List<Lunch> checkUserHaveApplyOrNot(String userName, String dateCheck);

	List<Lunch> checkUserBetWeenTwoTime(String userName, String startTime,
			String dateN);

	Page<Lunch> findPageOrderByFoodName(Integer pageIndex, Integer pageSize);

	Page<Lunch> findTodayOrders(String dateToday, Integer pageIndex,
			Integer pageSize);

	Page<Lunch> doSearchForOrderByTimeOrUserName(String userName,
			String startTime, String endTime, Integer pageIndex,
			Integer pageSize);

	// Page<E> findMenuByDayOfWeek(int dayOfWeek);

}