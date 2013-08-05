package com.qs.gx.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.qs.gx.services.model.Iteration;

@SuppressWarnings("unchecked")
@Repository
public class RewardPunishmentStasticsPlusDAO implements
		IRewardPunishmentStasticsPlusDAO {
	@PersistenceContext
	private  EntityManager em;

	@Override
	public List<Object[]> rewardPunishmentStasticsByCategoty(String startTime,
			String endTime, List<Long> idList) {
		endTime=endTime+" 23:59:59";
		String hql= "SELECT rt.ID,rt.CONTENT,a.num," +
					" (CASE WHEN a.point>0 THEN a.point ELSE 0 END) AS Tadd,(CASE WHEN a.point<0 THEN a.point ELSE 0 END) AS Tlose" +
					"  FROM REWARD_PUNISHMENT_TYPE rt,"+
					"  (SELECT  r.POINT_REASON,COUNT(r.id) AS num,SUM(r.POINT) AS point FROM REWARDS_PUNISHMENT_DETAIL r " +
					"  WHERE (r.STATUS is null or r.status=1) and r.[DATE]>=:startTime AND r.[DATE]<=:endTime";
		if(idList.size()>0){
			hql=hql+" and r.POINT_REASON in :idList ";
		}
		hql=hql+" GROUP BY  r.POINT_REASON) AS a  WHERE rt.ID=a.POINT_REASON ORDER BY a.point";
		Query query=em.createNativeQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		if(idList.size()>0){
			query.setParameter("idList", idList);
		}
		List<Object[]> list=query.getResultList();
		return list;
	}


	@Override
	public List<Object[]> rewardPunishmentStasticsByCategotyDetail(
			String startTime, String endTime, List<Long> longList) {
		endTime=endTime+" 23:59:59";
	    String hql="SELECT rt.CONTENT,a.USER_NAME,a.num, " +
	    		" (CASE WHEN a.point>0 THEN a.point ELSE 0 END) AS Tadd," +
	    		" (CASE WHEN a.point<0 THEN a.point ELSE 0 END) AS Tlose " +
	    		" FROM REWARD_PUNISHMENT_TYPE rt,(SELECT  r.USER_NAME,r.POINT_REASON,COUNT(r.id) AS num,SUM(r.POINT) AS point " +
	    		" FROM REWARDS_PUNISHMENT_DETAIL r  " +
	    		" WHERE (r.STATUS is null or r.status=1) and r.[DATE]>=:startTime AND r.[DATE]<=:endTime AND r.POINT_REASON in :idList  GROUP BY  r.POINT_REASON,r.USER_NAME  )  AS a  WHERE rt.ID=a.POINT_REASON ORDER BY a.point";
	    Query query=em.createNativeQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("idList", longList);
	    return query.getResultList();
	}

	@Override
	public List<Object[]> rewardPunishmentStasticsUserDetailInfo(
			String startTime, String endTime, String userName) {
		endTime=endTime+" 23:59:59";
	    String hql="SELECT a.USER_NAME,rt.CONTENT,a.num, " +
	    		" (CASE WHEN a.point>0 THEN a.point ELSE 0 END) AS Tadd," +
	    		" (CASE WHEN a.point<0 THEN a.point ELSE 0 END) AS Tlose " +
	    		" FROM REWARD_PUNISHMENT_TYPE rt,(SELECT  r.USER_NAME,r.POINT_REASON,COUNT(r.id) AS num,SUM(r.POINT) AS point " +
	    		" FROM REWARDS_PUNISHMENT_DETAIL r  " +
	    		" WHERE (r.STATUS is null or r.status=1) and  r.[DATE]>=:startTime AND r.[DATE]<=:endTime AND r.USER_NAME=:userName  GROUP BY  r.POINT_REASON,r.USER_NAME  )  AS a  WHERE rt.ID=a.POINT_REASON ORDER BY a.point";
	    Query query=em.createNativeQuery(hql);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		query.setParameter("userName", userName);
	    return query.getResultList();
	}


	@Override
	public List<Object[]> findTheHighesAlltPointUsers(
			Iteration iteration) {
//		String sql="SELECT * FROM (SELECT V.NAME,SUM(LOSE) AS LOSE,SUM(TADD) AS TADD,SUM(POINT) AS POINT,u.ID FROM V_REWARDS_PUNISHMENT V,[USER] u " +
//					" WHERE u.NAME=v.NAME AND v.ITERATION_ID=:ITERATION_ID AND u.STATUS IS NULL AND POINT!=0 GROUP BY V.NAME,u.ID ) AS aa " +
//					" WHERE aa.POINT>=(SELECT TOP 1 SUM(POINT) FROM V_REWARDS_PUNISHMENT  " +
//					" WHERE ITERATION_ID=:ITERATION_ID AND (USERID IN (SELECT ID FROM [USER] WHERE STATUS IS NULL )) " +
//					" GROUP BY NAME ORDER BY SUM(POINT) DESC)";
		String sql="SELECT * FROM (SELECT V.NAME,SUM(LOSE) AS LOSE,SUM(TADD) AS TADD,SUM(POINT) AS POINT,u.ID FROM ITERATOIN_HIGHEST_POINTS_VIEW V,[USER] u  " +
					"WHERE u.NAME=v.NAME AND v.ITERATION_ID=:ITERATION_ID AND u.STATUS IS NULL AND POINT!=0 GROUP BY V.NAME,u.ID ) AS aa  " +
					"WHERE aa.POINT>=(SELECT TOP 1 SUM(POINT) FROM ITERATOIN_HIGHEST_POINTS_VIEW  " +
					"WHERE ITERATION_ID=:ITERATION_ID AND (USERID IN (SELECT ID FROM [USER] WHERE STATUS IS NULL ))  " +
					"GROUP BY NAME ORDER BY SUM(POINT) DESC)";
		Query query=em.createNativeQuery(sql);
		query.setParameter("ITERATION_ID", iteration);
		return query.getResultList();
	}


	@Override
	public List<Object[]> findTheHighestLowPointUsers(
			Iteration iteration) {
		String sql="SELECT * FROM (SELECT V.NAME,SUM(LOSE) AS LOSE,SUM(TADD) AS TADD,SUM(POINT) AS POINT,u.ID FROM V_REWARDS_PUNISHMENT V,[USER] u " +
				" WHERE u.NAME=v.NAME AND v.ITERATION_ID=:ITERATION_ID AND u.STATUS IS NULL AND LOSE!=0 GROUP BY V.NAME,u.ID ) AS aa " +
				" WHERE aa.LOSE<=(SELECT TOP 1 SUM(LOSE) FROM V_REWARDS_PUNISHMENT  " +
				" WHERE ITERATION_ID=:ITERATION_ID AND (USERID IN (SELECT ID FROM [USER] WHERE STATUS IS NULL )) " +
				" GROUP BY NAME ORDER BY SUM(LOSE))";
	Query query=em.createNativeQuery(sql);
	query.setParameter("ITERATION_ID", iteration);
	return query.getResultList();
	}


	@Override
	public List<Object[]> findTheHighestBugPointUsers(Iteration iteration) {
		String sql="SELECT * FROM(SELECT  r.USER_ID  AS USER_ID ,u.NAME AS NAME ,SUM(r.POINT) AS POINT " +
				" FROM REWARDS_PUNISHMENT_DETAIL r,[USER] u " +
				" WHERE u.ID=r.USER_ID and r.POINT_REASON IN (31,52,39) AND r.ITERATION_ID=:iterationId AND u.STATUS IS NULL " +
				" GROUP BY r.USER_ID,u.NAME) AS aa " +
				" WHERE aa.POINT<=(SELECT  TOP 1 SUM(POINT) AS POINT FROM REWARDS_PUNISHMENT_DETAIL " +
				" WHERE POINT_REASON IN (31,52,39) AND ITERATION_ID=:iterationId " +
				" GROUP BY USER_ID ORDER BY POINT )";
	Query query=em.createNativeQuery(sql);
	query.setParameter("iterationId", iteration.getId());
	return query.getResultList();
	}


	@Override
	public List<Object[]> findTheAlltPointDetails(Iteration iteration) {
		String sql="SELECT * FROM (SELECT v.NAME,SUM(LOSE) AS LOSE,SUM(TADD) AS TADD,SUM(POINT) AS POINT,u.ID " +
				" FROM V_REWARDS_PUNISHMENT v ,[USER] u " +
				" WHERE u.NAME=v.NAME AND v.ITERATION_ID=:ITERATION_ID AND u.STATUS IS NULL AND v.POINT!=0 " +
				" GROUP BY v.NAME,u.ID) AS aa " +
				" ORDER BY aa.POINT DESC";
	Query query=em.createNativeQuery(sql);
	query.setParameter("ITERATION_ID", iteration);
	return query.getResultList();
	}


	@Override
	public List<Object[]> findTheLowPointDetails(Iteration iteration) {
		String sql="SELECT * FROM (SELECT v.NAME,SUM(LOSE) AS LOSE,SUM(TADD) AS TADD,SUM(POINT) AS POINT,u.ID " +
				" FROM V_REWARDS_PUNISHMENT v ,[USER] u " +
				" WHERE u.NAME=v.NAME AND v.ITERATION_ID=:ITERATION_ID AND u.STATUS IS NULL AND v.LOSE!=0 " +
				" GROUP BY v.NAME,u.ID) AS aa " +
				" ORDER BY  aa.LOSE";
	Query query=em.createNativeQuery(sql);
	query.setParameter("ITERATION_ID", iteration);
	return query.getResultList();
	}


	@Override
	public List<Object[]> findTheBugPointDetails(Iteration iteration) {
		String sql="SELECT * FROM(SELECT  r.USER_ID  AS USER_ID ,u.NAME AS NAME ,SUM(r.POINT) AS POINT " +
				" FROM REWARDS_PUNISHMENT_DETAIL r,[USER] u " +
				" WHERE u.ID=r.USER_ID and r.POINT_REASON IN (31,52,39) AND r.ITERATION_ID=:iterationId AND u.STATUS IS NULL " +
				" GROUP BY r.USER_ID,u.NAME) AS aa  " +
				" ORDER BY aa.POINT ";
	Query query=em.createNativeQuery(sql);
	query.setParameter("iterationId", iteration.getId());
	return query.getResultList();
	}

}
