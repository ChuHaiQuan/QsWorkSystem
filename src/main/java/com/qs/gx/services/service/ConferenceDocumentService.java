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
import com.qs.gx.services.dao.IConferenceDocumentDAO;
import com.qs.gx.services.model.ConferenceDocument;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2013-04-08
 */	
@Service
@Transactional
public class ConferenceDocumentService extends BaseDAOService<ConferenceDocument, Long> implements
		IConferenceDocumentService{

	private IConferenceDocumentDAO conferenceDocumentDAO;

	@Autowired
	public ConferenceDocumentService(
			IConferenceDocumentDAO conferenceDocumentDAO) {
		super(conferenceDocumentDAO);
		this.conferenceDocumentDAO = conferenceDocumentDAO;
	}

	@Override
	public Page<ConferenceDocument> findOnePage(Short type, Integer pageIndex,
			Integer pageSize) {
		Page<ConferenceDocument> page=null;
		if(pageIndex==null)
			pageIndex=0;
		if(pageSize==null)
			pageSize=10;
		if(type==null){
			page=conferenceDocumentDAO.findOnePage(new PageRequest(pageIndex, pageSize,new Sort(new Sort.Order(Sort.Direction.DESC,"createTime"))));
		}else{
			page=conferenceDocumentDAO.findOnePageByType(type,new PageRequest(pageIndex, pageSize,new Sort(new Sort.Order(Sort.Direction.DESC,"createTime"))));
		}
		return page;
	}
	
}
