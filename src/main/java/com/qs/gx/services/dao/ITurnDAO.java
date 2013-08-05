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
import com.qs.gx.services.model.Turn;

/**
 * Turn DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface ITurnDAO extends IBaseDAO<Turn, Long> {
	@Query("from Turn t where t.user.name like :name")
	Page<Turn> findOnePageByName(@Param("name") String name, Pageable paging);

	@Query("from Turn t where 1=1")
	Page<Turn> findOnePage(Pageable paging);

	@Query("from Turn t where t.workDate =:workDate and t.category =:category")
	Page<Turn> findByDateAndCategory(@Param("workDate") String workDate,
			@Param("category") String category, Pageable paging);
	
	@Query("from Turn t where t.workDate=:date")
	List<Turn> findByDateAndCategoryForList(@Param("date")String date);

}
