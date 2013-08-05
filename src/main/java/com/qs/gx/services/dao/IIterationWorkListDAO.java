/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWorkList;

/**
 * IterationWorkList DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2013-05-03
 */
public interface IIterationWorkListDAO extends
		IBaseDAO<IterationWorkList, Long> {

	@Query("from IterationWorkList iwl where iwl.iteration=:iteration ")
	Page<IterationWorkList> getOnePage(@Param("iteration") Iteration iteration,
			Pageable pageRequest);
	
	@Query("from IterationWorkList iwl where iwl.status=:status and iwl.iteration=:iteration")
	List<IterationWorkList> findByIterationAndStatus(@Param("status")Short status,
			@Param("iteration")Iteration iteration);

}
