/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.ProjectType;

/**
 * ProjectType Service Interface.
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface IProjectTypeService extends
		IBaseDAOService<ProjectType, Long> {

	Page<ProjectType> findOnePage(String name, Integer pageIndex, Integer pageSize);

}