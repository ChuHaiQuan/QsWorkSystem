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

@Table(name = "TRAINING_STATISTICS_ATTACH")
@Entity
public class TrainingStatisticsAttach extends BaseEntityWithHiloId {
	private String userName;
	private Integer type;
	private String date;
	private TrainingStatistics trainingStatistics;
	private Short status;

	public TrainingStatisticsAttach() {
	}
	
	@Column(name="STATUS")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRAINING_STATISTICS_ID")	
	public TrainingStatistics getTrainingStatistics() {
		return trainingStatistics;
	}


	public void setTrainingStatistics(TrainingStatistics trainingStatistics) {
		this.trainingStatistics = trainingStatistics;
	}


	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "DATE")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}