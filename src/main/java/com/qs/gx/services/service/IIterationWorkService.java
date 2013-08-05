/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.IterationWork;

/**
 * IterationWork Service Interface.
 * @author chuhaiquan
 * @since 2013-05-03
 */
public interface IIterationWorkService extends
		IBaseDAOService<IterationWork, Long> {

	void saveOrUpdate(Long iterationId, Long iterationWorkid, IterationWork iterationWorkNew, Long projectId,
			Long[] relativePerson);

	void delById(Long id);
	
	void publishRelativePersonPunish(Long iterationWorkId);

}