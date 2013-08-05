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
import com.qs.permission.user.model.User;

/**
 * Iteration Service Interface.
 * @author chuhaiquan
 * @since 2013-02-28
 */
public interface IIterationService extends
		IBaseDAOService<Iteration, Long> {

	Page<Iteration> findOnePageInfo(Integer pageIndex, Integer pageSize);

	Iteration chooseIteratoinByToday(String format);

	List<Object[]> findFullAttendenceAwardByIteration(Iteration iteration);

}