/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.TrainingStatisticsAttach;

/**
 * TrainingStatisticsAttach DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-17
 */
public interface ITrainingStatisticsAttachDAO extends
		IBaseDAO<TrainingStatisticsAttach, Long> {
	@Query("from TrainingStatisticsAttach t where t.date =:date and t.userName =:name and t.type =:type and t.status=1")
	TrainingStatisticsAttach findByDateAndDoPointName(
			@Param("date") String today, @Param("name") String vistorName,
			@Param("type") Integer trainingType);

	@Query("from TrainingStatisticsAttach  ")
	Page<TrainingStatisticsAttach> findByDate(String date);

	@Query("from TrainingStatisticsAttach t where t.date =:date and t.status=1")
	Page<TrainingStatisticsAttach> findByDate(@Param("date") String date,
			Pageable Pageable);

	@Query("from TrainingStatisticsAttach t where t.date =:date and t.userName =:name and t.type =:type and t.status=0")
	TrainingStatisticsAttach findOriginInfoByDateAndDoPointName(
			@Param("date") String date, @Param("name") String vistorName,
			@Param("type") Integer trainingType);

	@Query("from TrainingStatisticsAttach t where t.date =:date and t.status=:status")
	List<TrainingStatisticsAttach> findByDateAndStatus(@Param("date")String date,@Param("status") Short status);

}
