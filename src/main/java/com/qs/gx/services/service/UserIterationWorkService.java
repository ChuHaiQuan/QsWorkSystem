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
import com.qs.gx.services.dao.IUserIterationWorkDAO;
import com.qs.gx.services.model.IterationWork;
import com.qs.gx.services.model.UserIterationWork;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2013-05-03
 */	
@Service
@Transactional
public class UserIterationWorkService extends BaseDAOService<UserIterationWork, Long> implements
		IUserIterationWorkService{

	private IUserIterationWorkDAO userIterationWorkDAO;

	@Autowired
	public UserIterationWorkService(
			IUserIterationWorkDAO userIterationWorkDAO) {
		super(userIterationWorkDAO);
		this.userIterationWorkDAO = userIterationWorkDAO;
	}

	@Override
	public List<UserIterationWork> findByIterationWork(
			IterationWork iterationWork) {
		return userIterationWorkDAO.findByIterationWork(iterationWork);
	}
	
}
