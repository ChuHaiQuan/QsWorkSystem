/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.ConferenceDocument;

/**
 * ConferenceDocument Service Interface.
 * @author chuhaiquan
 * @since 2013-04-08
 */
public interface IConferenceDocumentService extends
		IBaseDAOService<ConferenceDocument, Long> {

	Page<ConferenceDocument> findOnePage(Short type, Integer pageIndex,
			Integer pageSize);

}