/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;

@Table(name = "FILES")
@Entity
public class File extends BaseEntityWithHiloId {
	private String name;
	private String punishTime;
	private String path;
	private Long userId;

	public File() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PUBLISH_TIME")
	public String getPunishTime() {
		return punishTime;
	}

	public void setPunishTime(String punishTime) {
		this.punishTime = punishTime;
	}

	@Column(name = "PATH")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}