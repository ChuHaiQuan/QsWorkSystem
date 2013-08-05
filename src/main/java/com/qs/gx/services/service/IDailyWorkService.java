/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qs.core.service.IBaseDAOService;
import com.qs.gx.services.model.DailyWork;
import com.qs.permission.user.model.User;

/**
 * DailyWork Service Interface.
 * @author chuhaiquan
 * @since 2012-12-06
 */
public interface IDailyWorkService extends
		IBaseDAOService<DailyWork, Long> {

	public Page<DailyWork> findOnePage(Integer pageIndex, Integer pageSize, String name, String startTime, String endTime);

	public Page<DailyWork> findByNameToday(String name,String date);

	public Page<DailyWork> findOnePageTodayForCommonUser(Long userId,String name, Integer pageIndex,
			Integer pageSize, String startTime, String endTime);
	
	//该接口方法表示每天定时去确认没有确认的任务（进行中）并确认为未完成 并且扣分
	public void autoCheckCompleteTaskOrNot();
	
	//查询当前没发布任务的人并自动扣分
	/*public void autoGetUnPublishWorkUsersAndDoPunish();*/
	public List<User> findByCompleteStatusAndDate(List<Short> statusList,String date);
	
	/**
	 * 周报人员任务数据
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Object[]> findDailyWorkInfoByNameAndStartTimeAndEndTime(
			String name, String startTime, String endTime);


}