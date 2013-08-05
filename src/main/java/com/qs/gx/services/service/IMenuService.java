/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.Menu;

/**
 * Menu Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-11-11
 */
public interface IMenuService extends IBaseDAOService<Menu, Long> {
	Page<Menu> findMenuByDayOfWeek(int dayOfWeek);
}