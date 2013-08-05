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

@Entity
@Table(name = "LUNCH")
public class Lunch extends BaseEntityWithHiloId {
	private Short status;
	private String userName;
	private String lunchName;
	private String dateNow;
	private String dateTom;
	private String ipAttr;
	private String eatTime;
	private String beiZhu;
	private String peiTang;

	@Column(name = "PEI_TANG")
	public String getPeiTang() {
		return peiTang;
	}

	public void setPeiTang(String peiTang) {
		this.peiTang = peiTang;
	}

	@Column(name = "BEI_ZHU")
	public String getBeiZhu() {
		return beiZhu;
	}

	public void setBeiZhu(String beiZhu) {
		this.beiZhu = beiZhu;
	}

	@Column(name = "EAT_TIME")
	public String getEatTime() {
		return eatTime;
	}

	public void setEatTime(String eatTime) {
		this.eatTime = eatTime;
	}

	@Column(name = "STATUS")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "IP_ATTR")
	public String getIpAttr() {
		return ipAttr;
	}

	public void setIpAttr(String ipAttr) {
		this.ipAttr = ipAttr;
	}

	@Column(name = "DATE_NOW")
	public String getDateNow() {
		return dateNow;
	}

	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}

	@Column(name = "DATE_TOM")
	public String getDateTom() {
		return dateTom;
	}

	public void setDateTom(String dateTom) {
		this.dateTom = dateTom;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "LUNCH_NAME")
	public String getLunchName() {
		return lunchName;
	}

	public void setLunchName(String lunchName) {
		this.lunchName = lunchName;
	}

}