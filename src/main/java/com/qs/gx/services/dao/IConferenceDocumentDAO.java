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
import com.qs.gx.services.model.ConferenceDocument;

/**
 * ConferenceDocument DAO Interface.
 * @author  chuhaiquan
 * @since 2013-04-08
 */
public interface IConferenceDocumentDAO extends IBaseDAO<ConferenceDocument, Long> {
	
	@Query("from ConferenceDocument c where 1=1")
	Page<ConferenceDocument> findOnePage(Pageable pageRequest);
	
	@Query("from ConferenceDocument c where c.type=:type")
	Page<ConferenceDocument> findOnePageByType(@Param("type")Short type,
			Pageable pageRequest);

}
