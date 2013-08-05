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
import com.qs.gx.services.dao.ITrainingStatisticsDAO;
import com.qs.gx.services.model.TrainingStatistics;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
@Service
@Transactional
public class TrainingStatisticsService extends
		BaseDAOService<TrainingStatistics, Long> implements
		ITrainingStatisticsService {

	private ITrainingStatisticsDAO trainingStatisticsDAO;

	@Autowired
	public TrainingStatisticsService(
			ITrainingStatisticsDAO trainingStatisticsDAO) {
		super(trainingStatisticsDAO);
		this.trainingStatisticsDAO = trainingStatisticsDAO;
	}

	@Override
	public Page<TrainingStatistics> findOnePage(Integer pageIndex,
			Integer pageSize, String name) {
		Page<TrainingStatistics> page = null;
		if (name != null && name!="") {
			name = "%" + name + "%";
			page = trainingStatisticsDAO.findOnePageByName(name,
					new PageRequest(pageIndex, pageSize, new Sort(
							new Sort.Order(Sort.Direction.DESC, "id"))));
		} else {
			page = trainingStatisticsDAO.findOnePage(new PageRequest(pageIndex,
					pageSize, new Sort(
							new Sort.Order(Sort.Direction.DESC, "id"))));
		}
		return page;
	}

	@Override
	public TrainingStatistics findByTimeAndType(String date, Integer type) {
		TrainingStatistics trainingStatistics = trainingStatisticsDAO
				.findByTimeAndType(date, type);
		return trainingStatistics;
	}

	@Override
	public List<TrainingStatistics> findByTime(String date) {
		return trainingStatisticsDAO.findByTime(date);
	}


}
