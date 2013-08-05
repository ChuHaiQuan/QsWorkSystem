package com.qs.gx.services.dao;

import java.util.List;




public interface IDailyWorkPlusDAO  {

	List<Object[]> findDailyWorkInfoByNameAndStartTimeAndEndTime(String name,
			String startTime, String endTime);

	List<Object> findUnPublishWorkUsersByDate(String format);

}
