package com.qs.gx.services.support;

public enum TrainingStatisticsAttachStatus {

	未打分((short) 0), 已打分((short) 1);
	
	private final Short value;

	private TrainingStatisticsAttachStatus(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

}
