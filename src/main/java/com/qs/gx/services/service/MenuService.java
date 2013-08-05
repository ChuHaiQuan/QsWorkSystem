/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IMenuDAO;
import com.qs.gx.services.model.Menu;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-11-11
 */
@Service
@Transactional
public class MenuService extends BaseDAOService<Menu, Long> implements
		IMenuService {

	private IMenuDAO menuDAO;

	@Autowired
	public MenuService(IMenuDAO menuDAO) {
		super(menuDAO);
		this.menuDAO = menuDAO;
	}

	@Override
	public Page<Menu> findMenuByDayOfWeek(int dayOfWeek) {
		Page<Menu> page = menuDAO.findMenuByDayOfWeek(dayOfWeek,
				new PageRequest(0, 10));
		return page;
	}
}
