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
import com.qs.gx.services.model.ProjectType;

/**
 * ProjectType DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface IProjectTypeDAO extends IBaseDAO<ProjectType, Long> {
	@Query("from ProjectType p where 1=1 ")
	Page<ProjectType> findOnePage(Pageable paging);

	@Query("from ProjectType p where p.typeName like :name or p.administrtor like :name")
	Page<ProjectType> findOnePageByName(@Param("name") String name,
			Pageable paging);

}
