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
import com.qs.gx.services.model.TrainingStatistics;

/**
 * TrainingStatistics DAO Interface.
 * 
 * @author chuhaiquan
 * @since 2012-12-11
 */
public interface ITrainingStatisticsDAO extends
		IBaseDAO<TrainingStatistics, Long> {
	@Query("from TrainingStatistics t where t.user.name like :name or t.statisticsName like :name")
	Page<TrainingStatistics> findOnePageByName(@Param("name") String name,
			Pageable paging);

	@Query("from TrainingStatistics t where 1=1")
	Page<TrainingStatistics> findOnePage(Pageable paging);

	@Query("from TrainingStatistics t where t.date =:date and t.trainingType =:type")
	TrainingStatistics findByTimeAndType(@Param("date") String date,
			@Param("type") Integer type);

	@Query("from TrainingStatistics t where t.date=:date")
	List<TrainingStatistics> findByTime(@Param("date") String date);

}
