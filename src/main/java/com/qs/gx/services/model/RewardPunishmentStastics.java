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
@Table(name="V_REWARDS_PUNISHMENT")
@Entity
public class RewardPunishmentStastics extends BaseEntityWithHiloId {
	private String userName;
	private User user;
	private Integer allPoint;
	private Integer losePoint;
	private Integer addPoint;
	private String date;
	
	public RewardPunishmentStastics() {
	}
	@Column(name="NAME")
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="POINT")
	public Integer getAllPoint() {
		return allPoint;
	}

	public void setAllPoint(Integer allPoint) {
		this.allPoint = allPoint;
	}
	@Column(name="LOSE")
	public Integer getLosePoint() {
		return losePoint;
	}

	public void setLosePoint(Integer losePoint) {
		this.losePoint = losePoint;
	}
	@Column(name="TADD")
	public Integer getAddPoint() {
		return addPoint;
	}

	public void setAddPoint(Integer addPoint) {
		this.addPoint = addPoint;
	}
	@Column(name="DATE")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}