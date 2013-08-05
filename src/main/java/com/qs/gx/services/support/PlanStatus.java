package com.qs.gx.services.support;

public enum PlanStatus {
	计划任务((short) 0), 计划外任务((short) 1), 备选任务((short) 2);
	private final Short value;

	private PlanStatus(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

}
