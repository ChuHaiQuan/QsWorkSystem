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
import com.qs.gx.services.dao.IIterationWorkListDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWorkList;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2013-05-03
 */	
@Service
@Transactional
public class IterationWorkListService extends BaseDAOService<IterationWorkList, Long> implements
		IIterationWorkListService{

	private IIterationWorkListDAO iterationWorkListDAO;

	@Autowired
	public IterationWorkListService(
			IIterationWorkListDAO iterationWorkListDAO) {
		super(iterationWorkListDAO);
		this.iterationWorkListDAO = iterationWorkListDAO;
	}

	@Override
	public Page<IterationWorkList> getOnePage(Iteration iteration,Integer pageIndex,
			Integer pageSize) {
		if(pageIndex==null)
			pageIndex=0;
		if(pageSize==null)
			pageSize=10;
		Page<IterationWorkList> page=iterationWorkListDAO.getOnePage(iteration,new PageRequest(pageIndex, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));
		return page;
	}

	@Override
	public List<IterationWorkList> findByIterationAndStatus(Short status,
			Iteration iteration) {
		return iterationWorkListDAO.findByIterationAndStatus(status,iteration);
	}
	
}
