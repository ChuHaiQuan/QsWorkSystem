/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.Iteration;

/**
 * Iteration DAO Interface.
 * @author  chuhaiquan
 * @since 2013-02-28
 */
public interface IIterationDAO extends IBaseDAO<Iteration, Long> {
	
	@Query("from Iteration i where 1=1")
	Page<Iteration> findOnePageInfo(Pageable pageRequest);
	
	@Query("from Iteration i where i.startTime<=:date and i.endTime>=:date")
	Iteration chooseIteratoinByToday(@Param("date")String date);

}
