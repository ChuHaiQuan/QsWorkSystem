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
import com.qs.gx.services.model.Menu;

/**
 * Menu DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-11-11
 */
public interface IMenuDAO extends IBaseDAO<Menu, Long> {
	@Query("from Menu m where m.day=:dayOfWeek")
	public Page<Menu> findMenuByDayOfWeek(@Param("dayOfWeek") int dayOfWeek,
			Pageable paging);
}
