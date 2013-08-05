/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;
import com.qs.permission.user.model.User;

@Table(name = "User_INTERATION_WORK")
@Entity
public class UserIterationWork extends BaseEntityWithHiloId {

	private User user;
	private IterationWork iterationWork;

	public UserIterationWork() {
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ITERATION_WORK")
	public IterationWork getIterationWork() {
		return iterationWork;
	}

	public void setIterationWork(IterationWork iterationWork) {
		this.iterationWork = iterationWork;
	};

}