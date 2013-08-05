/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;
import com.qs.permission.user.model.User;

@Table(name = "REWARDS_PUNISHMENT_DETAIL")
@Entity
public class RewardPunishmentDetail extends BaseEntityWithHiloId {
	private String userName;
	private User user;
	private String date;
	private Double point;
	private RewardPunishmentType pointReason;
	private String remark;
	private String taskName;
	private Short status;
	private Iteration iteration;

	public RewardPunishmentDetail() {
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITERATION_ID")	
	public Iteration getIteration() {
		return iteration;
	}
	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	@Column(name="STATUS")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "DATE")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name = "POINT")
	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POINT_REASON")
	public RewardPunishmentType getPointReason() {
		return pointReason;
	}

	public void setPointReason(RewardPunishmentType pointReason) {
		this.pointReason = pointReason;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TASK_NAME")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}