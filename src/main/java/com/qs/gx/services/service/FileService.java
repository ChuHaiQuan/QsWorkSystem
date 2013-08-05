/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IFileDAO;
import com.qs.gx.services.model.File;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@Service
@Transactional
public class FileService extends BaseDAOService<File, Long> implements
		IFileService {

	private IFileDAO fileDAO;

	@Autowired
	public FileService(IFileDAO fileDAO) {
		super(fileDAO);
		this.fileDAO = fileDAO;
	}

}
