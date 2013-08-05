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


@Table(name = "REWARD_PUNISHMENT_TYPE")
@Entity
public class RewardPunishmentType extends BaseEntityWithHiloId {
	private String content;
	private String category;
	private Double point;
	private String remark;
	private Short status;
	private Integer priorsRank;

	public RewardPunishmentType() {
	}
	@Column(name="PRIORS_RANK",nullable=false,length=10)
	public Integer getPriorsRank() {
		return priorsRank;
	}

	public void setPriorsRank(Integer priorsRank) {
		this.priorsRank = priorsRank;
	}

	@Column(name="STATUS")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "POINT_VALUE")
	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}