/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IDailyWorkDAO;
import com.qs.gx.services.dao.IDailyWorkPlusDAO;
import com.qs.gx.services.model.DailyWork;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.support.DailyWorkStatus;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * User Service Implement class.
 * 
 * @author chuhaiquan
 * @since 2012-12-06
 */
@Service
@Transactional
public class DailyWorkService extends BaseDAOService<DailyWork, Long> implements
		IDailyWorkService {

	private IDailyWorkDAO dailyWorkDAO;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IIterationService iterationService;
	@Autowired
	private IDailyWorkPlusDAO dailyWorkPlusDAO;
	
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;
	
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;

	@Autowired
	public DailyWorkService(IDailyWorkDAO dailyWorkDAO) {
		super(dailyWorkDAO);
		this.dailyWorkDAO = dailyWorkDAO;
	}



	@Override
	public Page<DailyWork> findOnePage(Integer pageIndex, Integer pageSize,
			String name, String startTime, String endTime) {
		Page<DailyWork> page = null;
		if (name != null && name != "") {
			name = "%" + name + "%";
			page = dailyWorkDAO
					.findOnePageByName(name, startTime, endTime,
							new PageRequest(pageIndex, pageSize,
									new Sort(new Sort.Order(
											Sort.Direction.DESC, "user"),
											new Sort.Order(Sort.Direction.DESC,
													"publishTime"))));
		} else {
			page = dailyWorkDAO
					.findOnePage(startTime, endTime, new PageRequest(pageIndex,
							pageSize, new Sort(new Sort.Order(
									Sort.Direction.DESC, "user"),
									new Sort.Order(Sort.Direction.DESC,
											"publishTime"))));
		}
		return page;
	}

	@Override
	public Page<DailyWork> findByNameToday(String name, String date) {
		date = "%" + date + "%";
		Page<DailyWork> page = dailyWorkDAO.findByNameToday(name, date);
		return page;
	}

	@Override
	public Page<DailyWork> findOnePageTodayForCommonUser(Long userId,String name,
			Integer pageIndex, Integer pageSize, String startTime,
			String endTime) {
		Page<DailyWork> page=null;
		if(StringUtils.isNotEmpty(name)){
			name="%"+name+"%";
			page = dailyWorkDAO.findOnePageForCommonUserByName(userId,name,
					startTime, endTime, new PageRequest(pageIndex, pageSize,
							new Sort(new Sort.Order(Sort.Direction.DESC, "user"),
									new Sort.Order(Sort.Direction.DESC,
											"publishTime"))));				
		}else{
			page = dailyWorkDAO.findOnePageForCommonUser(userId,
					startTime, endTime, new PageRequest(pageIndex, pageSize,
							new Sort(new Sort.Order(Sort.Direction.DESC, "user"),
									new Sort.Order(Sort.Direction.DESC,
											"publishTime"))));			
		}

		return page;
	}

	@Scheduled(cron = "0 0 23 * * ?")
	@Override
	public void autoCheckCompleteTaskOrNot() {
		 Date date = new Date(System.currentTimeMillis());
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 String today = format.format(date);
		 List<Short> statusList=new ArrayList<Short>();
		 statusList.add(DailyWorkStatus.草稿.getValue());
		 statusList.add(DailyWorkStatus.进行中.getValue());
		 List<User> list = this.findByCompleteStatusAndDate(statusList,
		 today);
		 if (list != null) {
		 for (int i = 0; i < list.size(); i++) {
		 User user=list.get(i);
		 RewardPunishmentDetail rewardPunishmentDetail=new RewardPunishmentDetail();
		 rewardPunishmentDetail.setDate(today);
		 rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(format.format(new Date())));
		 rewardPunishmentDetail.setPointReason(rewardPunishmentTypeService.findById(new Long(17)));
		 rewardPunishmentDetail.setPoint(rewardPunishmentTypeService.findById(new Long(17)).getPoint());
		 rewardPunishmentDetail.setUser(user);
		 rewardPunishmentDetail.setUserName(user.getName());
		 rewardPunishmentDetail.setRemark("下次记得自己做当日任务小结~~今天管理员代扣咯");
		 rewardPunishmentDetailService.save(rewardPunishmentDetail);
		 }
		 }
	}
	
/*	@Scheduled(cron = "0 59 23 * * ?")
	@Override
	public void autoGetUnPublishWorkUsersAndDoPunish(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today=format.format(new Date());
		List<Object> userIds=dailyWorkPlusDAO.findUnPublishWorkUsersByDate(today);
		for(Object id : userIds){
			User user=userService.findById(new BigDecimal((BigInteger)id).longValue());
			RewardPunishmentDetail rewardPunishmentDetail=new RewardPunishmentDetail();
			rewardPunishmentDetail.setDate(today);
			rewardPunishmentDetail.setIteration(iterationService.chooseIteratoinByToday(today));
			 rewardPunishmentDetail.setPointReason(rewardPunishmentTypeService.findById(new Long(15)));
			 rewardPunishmentDetail.setPoint(rewardPunishmentTypeService.findById(new Long(15)).getPoint());
			 rewardPunishmentDetail.setUser(user);
			 rewardPunishmentDetail.setUserName(user.getName());
			 rewardPunishmentDetail.setRemark("下次记得自己做当日计划~~今天管理员代扣咯!!");
			 rewardPunishmentDetailService.save(rewardPunishmentDetail);
		}
	};*/
	
	@Override
	public List<User> findByCompleteStatusAndDate(List<Short> statusList,
			String date) {
		List<User> list = dailyWorkDAO.findByCompleteStatusAndDate(
				statusList, date);
		return list;
	}

	@Override
	public List<Object[]> findDailyWorkInfoByNameAndStartTimeAndEndTime(
			String name, String startTime, String endTime) {
		return dailyWorkPlusDAO.findDailyWorkInfoByNameAndStartTimeAndEndTime(name,startTime,endTime);
	}
}
