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
@Table(name = "MENU")
public class Menu extends BaseEntityWithHiloId {
	private int day;
	private String menuName;
	private String peiTang;

	public Menu() {
	}

	@Column(name = "PEI_TANG")
	public String getPeiTang() {
		return peiTang;
	}

	public void setPeiTang(String peiTang) {
		this.peiTang = peiTang;
	}

	@Column(name = "DAY")
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Column(name = "MENU_NAME")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}