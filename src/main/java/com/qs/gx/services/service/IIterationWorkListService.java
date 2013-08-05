/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWorkList;

/**
 * IterationWorkList Service Interface.
 * @author chuhaiquan
 * @since 2013-05-03
 */
public interface IIterationWorkListService extends
		IBaseDAOService<IterationWorkList, Long> {
	
	Page<IterationWorkList> getOnePage(Iteration iteration, Integer pageIndex, Integer pageSize);

	List<IterationWorkList> findByIterationAndStatus(Short status,
			Iteration iteration);

}