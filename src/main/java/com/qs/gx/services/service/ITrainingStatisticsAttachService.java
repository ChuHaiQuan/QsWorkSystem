/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.TrainingStatisticsAttach;

/**
 * TrainingStatisticsAttach Service Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-17
 */
public interface ITrainingStatisticsAttachService extends
		IBaseDAOService<TrainingStatisticsAttach, Long> {

	TrainingStatisticsAttach findByDateAndDoPointName(String today,String vistorName,Integer trainingType);

	Page<TrainingStatisticsAttach> findByDate(String date, Integer pageIndex, Integer pageSize);

	TrainingStatisticsAttach findOriginInfoByDateAndDoPointName(String date,
			String visitorName, Integer trainingType);
	
	public void autoPunishUnMakePointUsers();
	
	public List<TrainingStatisticsAttach> findByDateAndStatus(String date,Short status);

}