/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.TrainingStatistics;

/**
 * TrainingStatistics Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface ITrainingStatisticsService extends
		IBaseDAOService<TrainingStatistics, Long> {

	Page<TrainingStatistics> findOnePage(Integer pageIndex, Integer pageSize,
			String name);

	TrainingStatistics findByTimeAndType(String date, Integer type);

	List<TrainingStatistics> findByTime(String format);
	
	

}