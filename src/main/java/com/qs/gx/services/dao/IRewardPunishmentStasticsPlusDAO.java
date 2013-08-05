package com.qs.gx.services.dao;

import java.util.List;

import com.qs.gx.services.model.Iteration;


public interface IRewardPunishmentStasticsPlusDAO {

	List<Object[]> rewardPunishmentStasticsByCategoty(String startTime,
			String endTime, List<Long> idList);

	List<Object[]> rewardPunishmentStasticsByCategotyDetail(String startTime,
			String endTime, List<Long> longList);

	List<Object[]> rewardPunishmentStasticsUserDetailInfo(String startTime,
			String endTime, String userName);

	List<Object[]> findTheHighesAlltPointUsers(Iteration iteration);
	
	List<Object[]> findTheAlltPointDetails(Iteration iteration);

	List<Object[]> findTheHighestLowPointUsers(Iteration iteration);
	
	List<Object[]> findTheLowPointDetails(Iteration iteration);

	List<Object[]> findTheHighestBugPointUsers(Iteration iteration);
	
	List<Object[]> findTheBugPointDetails(Iteration iteration);

}
