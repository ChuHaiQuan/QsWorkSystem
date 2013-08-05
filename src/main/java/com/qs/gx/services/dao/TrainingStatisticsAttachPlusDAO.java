package com.qs.gx.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@SuppressWarnings("unchecked")
@Repository
public class TrainingStatisticsAttachPlusDAO implements
		ITrainingStatisticsAttachPlusDAO {

	@PersistenceContext
	private  EntityManager em;

	@Override
	public List<Object> getUnMakePointUsersByDate(String date) {
		String sql="SELECT u.ID FROM [USER] u WHERE u.status is null and u.NAME IN (SELECT t.USER_NAME FROM TRAINING_STATISTICS_ATTACH t WHERE t.status=0 AND t.date=:date)";
		Query query=em.createNativeQuery(sql);
		query.setParameter("date", date);
		return query.getResultList();
	}
	
}
