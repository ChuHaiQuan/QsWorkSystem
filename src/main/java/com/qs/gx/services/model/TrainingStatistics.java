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

@Table(name = "TRAINING_STATISTICS")
@Entity
public class TrainingStatistics extends BaseEntityWithHiloId {
	private String userName;
	private String trainingTheme;
	private String date;
	private Double point;
	private String statisticsName;
	private Integer trainingType;
	private String comment;
	private Integer pointNumber;
	private Integer joinNumber;
	private Integer deadLine;
	private User user;
	private File file;
	
	public TrainingStatistics() {
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FILE_ID")
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="DEADLINE")
	public Integer getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Integer deadLine) {
		this.deadLine = deadLine;
	}

	@Column(name = "JOIN_NUMBER")
	public Integer getJoinNumber() {
		return joinNumber;
	}

	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

	@Column(name = "POINT_NUMBER")
	public Integer getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(Integer pointNumber) {
		this.pointNumber = pointNumber;
	}

	@Column(name = "COMMENT")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "TRAINING_TYPE")
	public Integer getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(Integer trainingType) {
		this.trainingType = trainingType;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "THEME")
	public String getTrainingTheme() {
		return trainingTheme;
	}

	public void setTrainingTheme(String trainingTheme) {
		this.trainingTheme = trainingTheme;
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

	@Column(name = "STATISTICS_NAME")
	public String getStatisticsName() {
		return statisticsName;
	}

	public void setStatisticsName(String statisticsName) {
		this.statisticsName = statisticsName;
	}

	@Override
	public String toString() {
		return "TrainingStatistics [name=" + userName + ", content="
				+ trainingTheme + ", date=" + date + ", point=" + point
				+ ", statisticsName=" + statisticsName + "]";
	}

}