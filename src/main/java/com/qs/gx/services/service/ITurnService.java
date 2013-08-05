/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.Turn;

/**
 * Turn Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface ITurnService extends IBaseDAOService<Turn, Long> {

	Page<Turn> findOnePage(String name, Integer pageIndex, Integer pageSize);

	Page<Turn> findByDateAndCategory(String workDate, String category,
			Integer pageIndex, Integer pageSize);

	List<Turn> findByDateAndCategoryForList(String format);

}