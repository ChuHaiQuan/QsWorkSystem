/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.ILunchDAO;
import com.qs.gx.services.model.Lunch;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-11-07
 */
@Service
@Transactional
public class LunchService extends BaseDAOService<Lunch, Long> implements
		ILunchService {

	private ILunchDAO lunchDAO;

	@Autowired
	public LunchService(ILunchDAO lunchDAO) {
		super(lunchDAO);
		this.lunchDAO = lunchDAO;
	}

	@Override
	public Page<Lunch> findAllApplyByPageSizeAndPageIndex(Integer pageIndex,
			Integer pageSize) {
		Page<Lunch> page = lunchDAO.findApply(new PageRequest(pageIndex,
				pageSize, new Sort(new Sort.Order(Sort.Direction.DESC,
						"dateNow"))));
		return page;
	}

	@Override
	public List<Lunch> checkUserHaveApplyOrNot(String userName, String dateCheck) {
		dateCheck = "%" + dateCheck + "%";
		List<Lunch> list = lunchDAO
				.checkUserHaveApplyOrNot(userName, dateCheck);
		return list;
	}

	@Override
	public List<Lunch> checkUserBetWeenTwoTime(String userName,
			String startTime, String dateN) {
		List<Lunch> list = lunchDAO.checkUserBetWeenTwoTime(userName,
				startTime, dateN);
		return list;
	}

	@Override
	public Page<Lunch> findPageOrderByFoodName(Integer pageIndex,
			Integer pageSize) {
		Page<Lunch> page = lunchDAO.findPageOrderByFoodName(new PageRequest(
				pageIndex, pageSize, new Sort(new Sort.Order(
						Sort.Direction.DESC, "lunchName"))));
		return page;
	}

	@Override
	public Page<Lunch> findTodayOrders(String dateToday, Integer pageIndex,
			Integer pageSize) {
		dateToday = "%" + dateToday + "%";
		Page<Lunch> page = lunchDAO.findTodayOrders(dateToday, new PageRequest(
				pageIndex, pageSize, new Sort(new Sort.Order(
						Sort.Direction.DESC, "lunchName"))));
		return page;
	}

	@Override
	public Page<Lunch> doSearchForOrderByTimeOrUserName(String userName,
			String startTime, String endTime, Integer pageIndex,
			Integer pageSize) {
		Page<Lunch> page = null;
		if (userName != null && userName != "") {
			userName = "%" + userName + "%";
			page = lunchDAO.doSearchForOrderByTimeOrUserName(userName,
					new PageRequest(pageIndex, pageSize, new Sort(
							new Sort.Order(Sort.Direction.DESC,
									"dateTom,lunchName"))));
		} else {
			page = lunchDAO.doSearchForOrderBetWeenStartTimeAndEndTime(
					startTime, endTime, new PageRequest(pageIndex, pageSize,
							new Sort(new Sort.Order(Sort.Direction.DESC,
									"dateTom,lunchName"))));
		}
		return page;
	}

}
