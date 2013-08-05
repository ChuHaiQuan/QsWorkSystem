package com.qs.gx.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class DailyWorkPlusDAO implements IDailyWorkPlusDAO {

	@PersistenceContext
	private  EntityManager em;

	@Override
	public List<Object[]> findDailyWorkInfoByNameAndStartTimeAndEndTime(
			String name, String startTime, String endTime) {
		endTime=endTime+" 23:59:59";
		String sql="SELECT " +
				" s.USER_NAME,s.PUBLISH_TIME," +
				" p.NAME,s.TASKNAME," +
				" (CASE WHEN  s.PLAN_COST_TIME IS NULL THEN 0 ELSE s.PLAN_COST_TIME END) AS COST_TIME" +
				" FROM  DAILY_WORKS s,PROJECT_TYPE p " +
				" WHERE s.USER_NAME is NOT NULL " +
				" and p.ID=s.DEPENDENCY_PROJECT_ID  " +
				" AND s.USER_NAME=:name " +
				" AND s.COMPLETESTATUS !=4 " +
				" AND s.COMPLETESTATUS !=5 " +
				" AND s.COMPLETESTATUS !=0 "+
				" AND s.PUBLISH_TIME>=:startTime " +
				" AND s.PUBLISH_TIME<=:endTime " +
				" ORDER BY s.PUBLISH_TIME";
		Query query=em.createNativeQuery(sql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<Object> findUnPublishWorkUsersByDate(String date) {
		String sql="SELECT u.id FROM [USER] u WHERE u.status is null and u.ID NOT IN  " +
				" (SELECT d.USER_ID FROM DAILY_WORKS d WHERE d.PUBLISH_TIME=:date)";
		Query query=em.createNativeQuery(sql);
		query.setParameter("date", date);
		return query.getResultList();
	}
	
	
}
