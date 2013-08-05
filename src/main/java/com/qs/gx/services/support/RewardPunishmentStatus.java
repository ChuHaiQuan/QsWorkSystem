package com.qs.gx.services.support;

public enum RewardPunishmentStatus {
	
	待确认((short) 0),已确认((short) 1),软删((short) 2);
	
	private final Short value;

	private RewardPunishmentStatus(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}
}
