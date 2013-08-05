/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.IterationWork;
import com.qs.gx.services.model.UserIterationWork;

/**
 * UserIterationWork Service Interface.
 * @author chuhaiquan
 * @since 2013-05-03
 */
public interface IUserIterationWorkService extends
		IBaseDAOService<UserIterationWork, Long> {
	
	List<UserIterationWork> findByIterationWork(IterationWork iterationWork);

}