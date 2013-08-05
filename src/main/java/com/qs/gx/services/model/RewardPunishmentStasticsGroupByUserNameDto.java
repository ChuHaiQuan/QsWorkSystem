package com.qs.gx.services.model;

public class RewardPunishmentStasticsGroupByUserNameDto {
	private String userName;
	private Long addPoint;
	private Long losePoint;
	private Long allPoint;

	public RewardPunishmentStasticsGroupByUserNameDto(String userName,
			Long losePoint, Long addPoint, Long allPoint) {
		this.addPoint = addPoint;
		this.losePoint = losePoint;
		this.allPoint = allPoint;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getAddPoint() {
		return addPoint;
	}

	public void setAddPoint(Long addPoint) {
		this.addPoint = addPoint;
	}

	public Long getLosePoint() {
		return losePoint;
	}

	public void setLosePoint(Long losePoint) {
		this.losePoint = losePoint;
	}

	public Long getAllPoint() {
		return allPoint;
	}

	public void setAllPoint(Long allPoint) {
		this.allPoint = allPoint;
	}


}
