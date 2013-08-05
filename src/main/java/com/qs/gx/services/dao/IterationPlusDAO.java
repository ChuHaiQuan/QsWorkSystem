package com.qs.gx.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.qs.gx.services.model.Iteration;

@SuppressWarnings("unchecked")
@Repository
public class IterationPlusDAO implements IIterationPlusDAO {
	
	@PersistenceContext
	private  EntityManager em;
	
	@Override
	public List<Object[]> findFullAttendenceAwardByIteration(Iteration iteration) {
		String sql="SELECT u.ID,u.NAME FROM [USER] u" +
				" WHERE (u.STATUS IS NULL) " +
				"AND (u.ID not in (SELECT USER_ID FROM REWARDS_PUNISHMENT_DETAIL WHERE (POINT_REASON IN (10,54)) AND ([DATE]>=:startTime and [DATE]<=:endTime)))";
		Query query=em.createNativeQuery(sql);
		query.setParameter("startTime", iteration.getStartTime());
		query.setParameter("endTime", iteration.getEndTime());
		return query.getResultList();
	}

}
