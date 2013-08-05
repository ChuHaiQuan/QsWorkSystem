/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IProjectTypeDAO;
import com.qs.gx.services.model.ProjectType;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@Service
@Transactional
public class ProjectTypeService extends BaseDAOService<ProjectType, Long>
		implements IProjectTypeService {

	private IProjectTypeDAO projectTypeDAO;

	@Autowired
	public ProjectTypeService(IProjectTypeDAO projectTypeDAO) {
		super(projectTypeDAO);
		this.projectTypeDAO = projectTypeDAO;
	}

	@Override
	public Page<ProjectType> findOnePage(String name, Integer pageIndex,
			Integer pageSize) {
		Page<ProjectType> page = null;
		if (name != null) {
			name = "%" + name + "%";
			page = projectTypeDAO.findOnePageByName(name, new PageRequest(
					pageIndex, pageSize, new Sort(new Sort.Order(
							Sort.Direction.DESC, "id"))));
		} else {
			page = projectTypeDAO.findOnePage(new PageRequest(pageIndex,
					pageSize, new Sort(
							new Sort.Order(Sort.Direction.DESC, "id"))));
		}
		return page;
	}

}
