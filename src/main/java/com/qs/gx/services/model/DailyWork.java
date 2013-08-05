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
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;
import com.qs.permission.user.model.User;

@Table(name = "DAILY_WORKS")
@Entity
public class DailyWork extends BaseEntityWithHiloId {
	private String taskType;
	private String taskName;
	private String taskDescribe;
	private User user;
	private String userName;
	private String publishTime;
	private String compeleteTime;
	private Double planCostTime;
	private Double realCostTime;
	private ProjectType dependencyProject;
	private Short completeStatus;

	public DailyWork() {
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "TASK_TYPE")
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "COMPELETE_TIME")
	public String getCompeleteTime() {
		return compeleteTime;
	}

	public void setCompeleteTime(String compeleteTime) {
		this.compeleteTime = compeleteTime;
	}

	@Column(name = "COMPLETESTATUS")
	public Short getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(Short completeStatus) {
		this.completeStatus = completeStatus;
	}

	@Column(name = "TASKNAME")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Column(name = "TASK_DESCRIBE")
	public String getTaskDescribe() {
		return taskDescribe;
	}

	public void setTaskDescribe(String taskDescribe) {
		this.taskDescribe = taskDescribe;
	}

	/*
	 * @ManyToOne(fetch=FetchType.EAGER)
	 * 
	 * @JoinColumn(name="USER_ID") public User getUser() { return user; } public
	 * void setUser(User user) { this.user = user; }
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "PUBLISH_TIME")
	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "PLAN_COST_TIME")
	public Double getPlanCostTime() {
		return planCostTime;
	}

	public void setPlanCostTime(Double planCostTime) {
		this.planCostTime = planCostTime;
	}

	@Column(name = "REAL_COST_TIME")
	public Double getRealCostTime() {
		return realCostTime;
	}

	public void setRealCostTime(Double realCostTime) {
		this.realCostTime = realCostTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPENDENCY_PROJECT_ID", nullable = false)
	public ProjectType getDependencyProject() {
		return dependencyProject;
	}

	public void setDependencyProject(ProjectType dependencyProject) {
		this.dependencyProject = dependencyProject;
	}

}