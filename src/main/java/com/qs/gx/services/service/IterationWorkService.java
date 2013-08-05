/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.core.exception.AppWebException;
import com.qs.core.service.BaseDAOService;
import com.qs.gx.services.dao.IIterationWorkDAO;
import com.qs.gx.services.model.Iteration;
import com.qs.gx.services.model.IterationWork;
import com.qs.gx.services.model.RewardPunishmentDetail;
import com.qs.gx.services.model.RewardPunishmentType;
import com.qs.gx.services.model.UserIterationWork;
import com.qs.gx.services.support.IterationWorkStatus;
import com.qs.permission.user.service.IUserService;

	
/**
 * User Service Implement class.
 * @author chuhaiquan
 * @since 2013-05-03
 */	
@Service
@Transactional
public class IterationWorkService extends BaseDAOService<IterationWork, Long> implements
		IIterationWorkService{

	private IIterationWorkDAO iterationWorkDAO;
	
	@Autowired
	private IUserIterationWorkService userIterationWorkService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IIterationService iterationService;
	
	@Autowired
	private IProjectTypeService projectTypeService;
	
	@Autowired
	private IRewardPunishmentDetailService rewardPunishmentDetailService;
	
	@Autowired
	private IRewardPunishmentTypeService rewardPunishmentTypeService;

	@Autowired
	public IterationWorkService(
			IIterationWorkDAO iterationWorkDAO) {
		super(iterationWorkDAO);
		this.iterationWorkDAO = iterationWorkDAO;
	}

	@Override
	public void saveOrUpdate(Long iterationId,Long iterationWorkid, IterationWork iterationWorkNew,
			Long projectId, Long[] relativePerson) {
		UserIterationWork userIterationWorkNew=null;
		Iteration iteration=iterationService.findById(iterationId);
		if(relativePerson==null){
			throw new AppWebException("请选择至少选择一个相关人员!");
		}
		if(iterationWorkid!=null){
			IterationWork iterationWork= this.findById(iterationWorkid);
			List<UserIterationWork> userIterationWorkList=userIterationWorkService.findByIterationWork(iterationWork);
			for(UserIterationWork userIterationWork:userIterationWorkList){
				userIterationWorkService.delete(userIterationWork.getId());
			}
			this.delete(iterationWorkid);
			iterationWorkNew.setProjectType(projectTypeService.findById(projectId));
			iterationWorkNew.setStatus(IterationWorkStatus.进行中.getValue());
			iterationWorkNew.setIteration(iteration);
			this.save(iterationWorkNew);
			for(Long userId:relativePerson){
				userIterationWorkNew=new UserIterationWork();
				userIterationWorkNew.setIterationWork(iterationWorkNew);
				userIterationWorkNew.setUser(userService.findById(userId));
				userIterationWorkService.save(userIterationWorkNew);
			}
		}else{
			iterationWorkNew.setProjectType(projectTypeService.findById(projectId));
			iterationWorkNew.setStatus(IterationWorkStatus.进行中.getValue());
			iterationWorkNew.setIteration(iteration);
			this.save(iterationWorkNew);
			for(Long userId:relativePerson){
				userIterationWorkNew=new UserIterationWork();
				userIterationWorkNew.setIterationWork(iterationWorkNew);
				userIterationWorkNew.setUser(userService.findById(userId));
				userIterationWorkService.save(userIterationWorkNew);
			}			
		}		
	}

	@Override
	public void delById(Long id) {
		IterationWork iterationWork= this.findById(id);
		List<UserIterationWork> userIterationWorkList=userIterationWorkService.findByIterationWork(iterationWork);
		for(UserIterationWork userIterationWork:userIterationWorkList){
			userIterationWorkService.delete(userIterationWork.getId());
		}
		this.delete(id);
	}

	@Override
	public void publishRelativePersonPunish(Long iterationWorkId) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		IterationWork iterationWork= this.findById(iterationWorkId);
		List<UserIterationWork> userIterationWorkList=userIterationWorkService.findByIterationWork(iterationWork);
		RewardPunishmentDetail rewardPunishmentDetail=null;
		RewardPunishmentType rewardPunishmentType=rewardPunishmentTypeService.findById(new Long(29));
		for(UserIterationWork userIterationWork:userIterationWorkList){
			List<RewardPunishmentDetail> rewardPunishmentDetailList=rewardPunishmentDetailService.findByUserIdAndIterationAndType(rewardPunishmentType, userIterationWork.getUser(), iterationWork.getIteration());
			if(rewardPunishmentDetailList.size()==0){
				rewardPunishmentDetail=new RewardPunishmentDetail();
				rewardPunishmentDetail.setDate(format.format(new Date()));
				rewardPunishmentDetail.setUserName(userIterationWork.getUser().getName());
				//获取迭代任务未完成的奖惩项
				rewardPunishmentDetail.setIteration(iterationWork.getIteration());
				rewardPunishmentDetail.setUser(userIterationWork.getUser());
				rewardPunishmentDetail.setPoint(rewardPunishmentType.getPoint());
				rewardPunishmentDetail.setRemark("迭代任务没完成,自动扣分!");
				rewardPunishmentDetail.setPointReason(rewardPunishmentType);
				rewardPunishmentDetailService.save(rewardPunishmentDetail);				
			}
		}
		
	}
	
}
