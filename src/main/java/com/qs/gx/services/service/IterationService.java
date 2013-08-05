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
import com.qs.gx.services.dao.IIterationDAO;
import com.qs.gx.services.dao.IIterationPlusDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.permission.user.model.User;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2013-02-28
 */	
@Service
@Transactional
public class IterationService extends BaseDAOService<Iteration, Long> implements
		IIterationService{

	private IIterationDAO iterationDAO;
	
	@Autowired
	private IIterationPlusDAO iterationPlusDAO;
	@Autowired
	public IterationService(
			IIterationDAO iterationDAO) {
		super(iterationDAO);
		this.iterationDAO = iterationDAO;
	}

	@Override
	public Page<Iteration> findOnePageInfo(Integer pageIndex, Integer pageSize) {
		return iterationDAO.findOnePageInfo(new PageRequest(pageIndex,pageSize,new Sort(new Sort.Order(Sort.Direction.DESC,"startTime"))));
	}

	@Override
	public Iteration chooseIteratoinByToday(String date) {
		return iterationDAO.chooseIteratoinByToday(date);
	}

	@Override
	public List<Object[]> findFullAttendenceAwardByIteration(Iteration iteration) {
		List<Object[]> list=iterationPlusDAO.findFullAttendenceAwardByIteration(iteration);
		return list;
	}
	
}
