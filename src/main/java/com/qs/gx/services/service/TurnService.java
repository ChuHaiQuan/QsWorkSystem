/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.ITurnDAO;
import com.qs.gx.services.model.Turn;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@Service
@Transactional
public class TurnService extends BaseDAOService<Turn, Long> implements
		ITurnService {

	private ITurnDAO turnDAO;

	@Autowired
	public TurnService(ITurnDAO turnDAO) {
		super(turnDAO);
		this.turnDAO = turnDAO;
	}

	@Override
	public Page<Turn> findOnePage(String name, Integer pageIndex,
			Integer pageSize) {
		Page<Turn> page = null;
		if (name != null && name!="") {
			name = "%" + name + "%";
			page = turnDAO.findOnePageByName(name, new PageRequest(pageIndex,
					pageSize, new Sort(new Sort.Order(Sort.Direction.DESC,
							"workDate"))));
		} else {
			page = turnDAO.findOnePage(new PageRequest(pageIndex, pageSize,
					new Sort(new Sort.Order(Sort.Direction.DESC, "workDate"))));
		}
		return page;
	}

	@Override
	public Page<Turn> findByDateAndCategory(String workDate, String category,
			Integer pageIndex, Integer pageSize) {
		Page<Turn> page = turnDAO.findByDateAndCategory(workDate, category,
				new PageRequest(pageIndex, pageSize));
		return page;
	}

	@Override
	public List<Turn> findByDateAndCategoryForList(String date) {
		return turnDAO.findByDateAndCategoryForList(date);
	}

}
